package com.cadenceone.simplesoap;

public class SOAPResponse {
	
	private String soapResponse;

	public SOAPResponse(String soapResponse) {
		super();
		this.soapResponse = soapResponse;
	}

	public String getXmlAsString() {
		return soapResponse;
	}
	
	

}
