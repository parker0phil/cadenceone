package com.cadenceone.groovysoap

import com.cadenceone.simplesoap.SOAPResponse
import groovy.util.slurpersupport.NodeChild

class GSOAPResponse {

    private String responseXml
    private Node response

    GSOAPResponse(SOAPResponse response){
       this(response, false)
    }

    GSOAPResponse(SOAPResponse response, boolean parseCDATA){
       this.responseXml = removeCDATA(response.xmlAsString)
       this.response = new XmlParser().parseText(responseXml)
    }

    private String removeCDATA(String xmlString){
        xmlString = xmlString.replaceAll("<!\\[CDATA\\[","")
        xmlString = xmlString.replaceAll("&lt;","<")
        xmlString = xmlString.replaceAll("&gt;",">")
        return xmlString.replaceAll("\\]\\]","")

    }
    def propertyMissing(String name) {

        def nodes = []
        response.depthFirst().grep{it.name().getLocalPart() == name}.each{
            if (it.value().size() == 1){
                nodes.add(it.value()[0])
            }else{
                nodes.add(it.value())
            }
        }
        if(nodes.size() > 0) {
            if (nodes.size()==1){
                return nodes[0]
            }
            return nodes;
        }
        else throw new IllegalArgumentException("The response doesn't have an element '$name'. Maybe one of ${response.depthFirst().collect{it.name().getLocalPart()}}?")
    }

    def getXml(){
        return responseXml
    }
}
