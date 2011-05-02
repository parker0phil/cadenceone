package com.cadenceone.groovysoap


class GSOAPTest extends spock.lang.Specification {
    def holidayService =  new GSOAP("http://www.holidaywebservice.com/HolidayService_v2/HolidayService2.asmx?wsdl").withNamespace("hol")

    def "Method call without matching operation complains"() {
        when: holidayService.ThisOperationDoesntExist()
        then:
            IllegalArgumentException ex = thrown()
            ex.message == "Service hasn't got the method 'ThisOperationDoesntExist'. Did you mean one of [GetHolidayDate, GetHolidaysForYear, GetHolidaysForMonth, GetCountriesAvailable, GetHolidaysAvailable, GetHolidaysForDateRange]?"
    }

    def "Method call invokes operation with same name"() {
        expect:
        GSOAPResponse.class == holidayService.GetHolidaysForMonth(countryCode: "GreatBritain", year: "2011", month: "5").getClass()
    }

    def "Method call (using camel case) invokes operation with same name"() {
        expect:
        GSOAPResponse.class == holidayService.getHolidaysForMonth(countryCode: "GreatBritain", year: "2011", month: "5").getClass()
    }
}