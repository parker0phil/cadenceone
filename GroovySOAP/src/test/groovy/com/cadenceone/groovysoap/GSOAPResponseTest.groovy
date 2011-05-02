package com.cadenceone.groovysoap

import com.cadenceone.simplesoap.SOAPResponse

class GSOAPResponseTest extends spock.lang.Specification {

    def soapResponse = Mock(SOAPResponse)
    def soapResponse2 = Mock(SOAPResponse)

    def setup() {
        soapResponse.getXmlAsString() >> response
        soapResponse2.getXmlAsString() >> response2
    }




    def "Property access searches response xml by node name"(){
        when:
        def response = new GSOAPResponse(soapResponse2)

        then:
        response.Country == "GreatBritain"
    }

     def "Response search matching multiple nodes returns an array"(){
        when:
        def response = new GSOAPResponse(soapResponse)

        then:
        response.Country.size() == 2
        response.Country[0] == "GreatBritain"
    }

//    def "Response search matching parent nodes returns a node"(){
//        when:
//        def response = new GSOAPResponse(soapResponse2)
//
//        then:
//        response.GetHolidaysForMonthResult.getClass() == groovy.util.Node.class
//        response.GetHolidaysForMonthResult."{http://www.holidaywebservice.com/HolidayService_v2/}Holiday".HolidayCode.text() == "MAY-DAY"
//    }



def response = """<?xml version="1.0" encoding="utf-8"?>
        <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
        <soap:Body>
        <GetHolidaysForMonthResponse xmlns="http://www.holidaywebservice.com/HolidayService_v2/">
        <GetHolidaysForMonthResult>
        <Holiday>
        <Country>GreatBritain</Country><HolidayCode>MAY-DAY</HolidayCode>
        <Descriptor>May Day (Early May Bank Holiday)</Descriptor><HolidayType>Other</HolidayType>
        <DateType>ObservedActual</DateType><BankHoliday>Recognized</BankHoliday><Date>2011-05-02T00:00:00</Date>
        </Holiday>
        <Holiday><Country>GreatBritain</Country><HolidayCode>SPRING-BANK</HolidayCode>
        <Descriptor>Spring Bank Holiday</Descriptor><HolidayType>Other</HolidayType><DateType>ObservedActual</DateType>
        <BankHoliday>Recognized</BankHoliday><Date>2011-05-30T00:00:00</Date></Holiday></GetHolidaysForMonthResult>
        </GetHolidaysForMonthResponse>
        </soap:Body>
        </soap:Envelope>
    """

 def response2 = """<?xml version="1.0" encoding="utf-8"?>
        <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
        <soap:Body>
        <GetHolidaysForMonthResponse xmlns="http://www.holidaywebservice.com/HolidayService_v2/">
        <GetHolidaysForMonthResult>
        <Holiday>
        <Country>GreatBritain</Country><HolidayCode>MAY-DAY</HolidayCode>
        <Descriptor>May Day (Early May Bank Holiday)</Descriptor><HolidayType>Other</HolidayType>
        <DateType>ObservedActual</DateType><BankHoliday>Recognized</BankHoliday><Date>2011-05-02T00:00:00</Date>
        </Holiday>
        </GetHolidaysForMonthResult>
        </GetHolidaysForMonthResponse>
        </soap:Body>
        </soap:Envelope>
    """
}

