package com.cadenceone.simplesoap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.net.MalformedURLException;

import org.junit.Test;

public class SOAPResponseTest {
	
	String responseString = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"> <soap:Body><GetHolidaysForMonthResponse xmlns=\"http://www.holidaywebservice.com/HolidayService_v2/\"> <GetHolidaysForMonthResult><Holiday> <Country>GreatBritain</Country> <HolidayCode>MAY-DAY</HolidayCode> <Descriptor>May Day (Early May Bank Holiday)</Descriptor> <HolidayType>Other</HolidayType> <DateType>ObservedActual</DateType> <BankHoliday>Recognized</BankHoliday> <Date>2011-05-02T00:00:00</Date></Holiday><Holiday> <Country>GreatBritain</Country> <HolidayCode>SPRING-BANK</HolidayCode> <Descriptor>Spring Bank Holiday</Descriptor> <HolidayType>Other</HolidayType> <DateType>ObservedActual</DateType> <BankHoliday>Recognized</BankHoliday> <Date>2011-05-30T00:00:00</Date></Holiday> </GetHolidaysForMonthResult></GetHolidaysForMonthResponse> </soap:Body></soap:Envelope>";

	@Test
    public void whenConstructedWithAString_thenReturnsTheSameString() throws MalformedURLException {
		SOAPResponse response = new SOAPResponse(responseString);
		assertThat(response.getXmlAsString(), is(responseString));
    }
}
