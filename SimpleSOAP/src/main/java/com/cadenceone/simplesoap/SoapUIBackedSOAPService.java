package com.cadenceone.simplesoap;

import com.eviware.soapui.impl.WsdlInterfaceFactory;
import com.eviware.soapui.impl.wsdl.WsdlInterface;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.support.wsdl.WsdlLoader;
import com.eviware.soapui.model.iface.Operation;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SoapUIBackedSOAPService implements SOAPService {

    private Map<String, SOAPOperationRequest> operationsByName = new HashMap<String, SOAPOperationRequest>();
    private String endpoint;
    private String namespace;



    public SoapUIBackedSOAPService(final String wsdl) {
        this.endpoint = wsdl.substring(0, wsdl.length() - 5);

        operationsByName = extractOperationsFromInterfaces(extractWsdlInterfaces(wsdl));

    }

    public SoapUIBackedSOAPService withEndpoint(String endpoint){
        this.endpoint = endpoint;
        return this;
    }

    public SoapUIBackedSOAPService withNamespace(String namespace){
        this.namespace = namespace;
        return this;
    }

    private Map<String, SOAPOperationRequest> extractOperationsFromInterfaces(WsdlInterface[] importedWsdlInterfaces) {
        Map<String, SOAPOperationRequest> foundOperations = new HashMap<String, SOAPOperationRequest>();
        for (WsdlInterface wsdlInterface : importedWsdlInterfaces) {
            List<Operation> operations = wsdlInterface.getOperationList();
            for (Operation operation : operations) {
                foundOperations.put(operation.getName(), new SOAPOperationRequest(wsdlInterface.getOperationByName(operation.getName())));
            }
        }
        return foundOperations;
    }

    private WsdlInterface[] extractWsdlInterfaces(String wsdl) {
        WsdlInterface[] interfaces;
        try {
            WsdlProject wsdlProject = new WsdlProject();
            interfaces = WsdlInterfaceFactory.importWsdl(wsdlProject, wsdl, true, new HttpClientWsdlLoader(wsdl, new HttpClient()));
        } catch (Exception e) {
            throw new RuntimeException("Could not build a service from " + wsdl, e);
        }
        return interfaces;
    }

    public int countOperations() {
        return operationsByName.size();
    }

    public boolean hasOperation(String name) {
        return operationsByName.containsKey(name);
    }

    public SOAPResponse invokeOperation(String operationName, Map params) {

        SOAPOperationRequest request = operationsByName.get(operationName);
        if (request == null) {
            throw new IllegalArgumentException(String.format("Service hasn't got the method '%s'. Did you mean one of %s?",
                    operationName,
                    operationsByName.keySet()));
        }
        String message = request.buildRequest(params, namespace);

        return new SOAPResponse(sendRequest(endpoint, message, request.getSOAPAction()));
    }


    public SOAPResponse invokeOperation(String operationName) {
        return invokeOperation(operationName, Collections.emptyMap());
    }


    public String sendRequest(String address, String message, String action) {
        String responseBodyAsString = null;
        PostMethod postMethod = new PostMethod(address);
        try {
            HttpClient client = new HttpClient();
            postMethod.setRequestHeader("SOAPAction", action);
            try {
                postMethod.setRequestEntity(new InputStreamRequestEntity(
                        new ByteArrayInputStream(message.getBytes("UTF-8")),
                        "text/xml")

                );
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            try {
                client.executeMethod(postMethod);
                responseBodyAsString = postMethod.getResponseBodyAsString();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } finally {
            postMethod.releaseConnection();
        }

        return responseBodyAsString;
    }


    class HttpClientWsdlLoader extends WsdlLoader {


        private boolean isAborted = false;
        private HttpClient httpClient;



        public HttpClientWsdlLoader(String url, HttpClient httpClient) {
            super(url);
            this.httpClient = httpClient;
        }

        public InputStream load(String url) throws Exception {
            GetMethod httpGetMethod;

            if (url.startsWith("file")) {
                return new URL(url).openStream();
            }

            // Authentication is not being overridden on the method.  It needs
            // to be present on the supplied HttpClient instance!
            httpGetMethod = new GetMethod(url);
            httpGetMethod.setDoAuthentication(true);

            try {
                int result = httpClient.executeMethod(httpGetMethod);

                if (result != HttpStatus.SC_OK) {
                    if (result < 200 || result > 299) {
                        throw new HttpException("Received status code '" + result + "' on WSDL HTTP (GET) request: '" + url + "'.");
                    } else {
                        //logger.warn("Received status code '" + result + "' on WSDL HTTP (GET) request: '" + url + "'.");
                    }
                }

                return new ByteArrayInputStream(httpGetMethod.getResponseBody());
            } finally {
                httpGetMethod.releaseConnection();
            }
        }

        public boolean abort() {
            isAborted = true;
            return true;
        }

        public boolean isAborted() {
            return isAborted;
        }

        public void close() {

        }

    }


}