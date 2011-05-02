package com.cadenceone.simplesoap;

import java.util.Map;

public interface SOAPService {
	
	public int countOperations();
	public SOAPResponse invokeOperation(String operationName);
	public SOAPResponse invokeOperation(String operationName, Map params);

}
