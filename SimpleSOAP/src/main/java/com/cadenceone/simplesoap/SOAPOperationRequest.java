package com.cadenceone.simplesoap;

import com.cadenceone.simplesoap.tmp.OGNLUtils;
import com.eviware.soapui.impl.wsdl.WsdlOperation;
import org.milyn.xml.XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class SOAPOperationRequest {

    private WsdlOperation operation;
    private DocumentBuilderFactory docBuilderFactory;
    private Document soapRequestDocument;

    public SOAPOperationRequest(WsdlOperation operation) {
        this.operation = operation;
        docBuilderFactory = DocumentBuilderFactory.newInstance();
        docBuilderFactory.setNamespaceAware(true);
        soapRequestDocument = buildDocRoot();
    }

    public String getSOAPAction() {
        return operation.getAction();
    }

    public String buildRequest(Map params, String soapNs) {

        injectParameters(soapRequestDocument.getDocumentElement(), params, soapNs, operation.getName());
        return XmlUtil.serialize(soapRequestDocument.getChildNodes());
    }



    private Document buildDocRoot() {
        String requestTemplate = operation.getRequestAt(0).getRequestContent();
        Document messageDoc;
        try {
            messageDoc = getDocBuilder().parse(new InputSource(new StringReader(requestTemplate)));
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return messageDoc;
    }

    private void injectParameters(Element element, Map params, String soapNs, String operationName) {
        NodeList children = element.getChildNodes();
        int childCount = children.getLength();

        for (int i = 0; i < childCount; i++) {
            Node node = children.item(i);

            if (childCount == 1 && node.getNodeType() == Node.TEXT_NODE) {
                if (node.getNodeValue().equals("?")) {
                    String ognl = OGNLUtils.getOGNLExpression(element, soapNs);
                    Object param;

                    param = OGNLUtils.getParameter(ognl, params, operationName);

                    element.removeChild(node);
                    element.appendChild(element.getOwnerDocument().createTextNode(param.toString()));
                }
            } else if (node.getNodeType() == Node.ELEMENT_NODE) {
                injectParameters((Element) node, params, soapNs, operationName);
            }
        }

    }

    synchronized private DocumentBuilder getDocBuilder() {
        try {
            return docBuilderFactory.newDocumentBuilder();
        } catch (final ParserConfigurationException pce) {
            throw new RuntimeException(pce);
        }
    }
}
