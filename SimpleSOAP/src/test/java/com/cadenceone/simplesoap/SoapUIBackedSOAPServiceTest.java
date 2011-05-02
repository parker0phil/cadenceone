package com.cadenceone.simplesoap;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class SoapUIBackedSOAPServiceTest {

	SOAPService validService = new SoapUIBackedSOAPService("http://www.holidaywebservice.com/HolidayService_v2/HolidayService2.asmx?wsdl").withNamespace("hol");
	String expectedResponse = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><soap:Body><GetHolidaysForMonthResponse xmlns=\"http://www.holidaywebservice.com/HolidayService_v2/\"><GetHolidaysForMonthResult><Holiday><Country>GreatBritain</Country><HolidayCode>MAY-DAY</HolidayCode><Descriptor>May Day (Early May Bank Holiday)</Descriptor><HolidayType>Other</HolidayType><DateType>ObservedActual</DateType><BankHoliday>Recognized</BankHoliday><Date>2011-05-02T00:00:00</Date></Holiday><Holiday><Country>GreatBritain</Country><HolidayCode>SPRING-BANK</HolidayCode><Descriptor>Spring Bank Holiday</Descriptor><HolidayType>Other</HolidayType><DateType>ObservedActual</DateType><BankHoliday>Recognized</BankHoliday><Date>2011-05-30T00:00:00</Date></Holiday></GetHolidaysForMonthResult></GetHolidaysForMonthResponse></soap:Body></soap:Envelope>";
    @Test
    public void givenAValidWsdl_whenNumberOfOperationsIsQueried_thenReturnsCorrectNumber() throws MalformedURLException {
        assertThat(validService.countOperations(), is(6));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void givenAValidWsdl_whenInvokingAMissingOperation_thenComplains() throws MalformedURLException {
        validService.invokeOperation("getFoo");
    }
    
    @Test
    public void givenAValidWsdl_whenInvokingAnExistingOperation_thenReturnsAResponse() throws MalformedURLException {
        SOAPResponse response = validService.invokeOperation("GetHolidaysForMonth");
        assertThat(response, notNullValue());
    }
    
    @Test
    public void givenAValidWsdl_whenInvokingAnExistingOperation_thenResponseContainsValidResponseXML() throws MalformedURLException {
        Map params = new HashMap();
        params.put("countryCode", "GreatBritain");
        params.put("year", "2011");
        params.put("month", "5");
    	SOAPResponse response = validService.invokeOperation("GetHolidaysForMonth", params);
        assertThat(response.getXmlAsString(), is(expectedResponse));
    }
}
