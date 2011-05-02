package com.cadenceone.groovysoap.acceptance

import com.cadenceone.groovysoap.GSOAP

class AcceptanceTests extends spock.lang.Specification {

    def "Calling a webservice where the endpoint is same url as wsdl, when response element is simple string"(){
        when:
            def response = new GSOAP("http://www.webservicex.net/stockquote.asmx?wsdl").andAlsoParseCDATA().GetQuote(symbol:"GOOG")

        then:
            response.Name == "Google Inc."
    }


    def "When the SOAP method you call doesn't exist"(){
        when:
            new GSOAP("http://www.webservicex.net/stockquote.asmx?wsdl").ThisMethodDoesntExist()

        then:
            IllegalArgumentException ex = thrown()
            ex.message == "Service hasn't got the method 'ThisMethodDoesntExist'. Did you mean one of [GetQuote]?"
    }


    def "When the response element doesn't exist"(){
        when:
            def response = new GSOAP("http://www.webservicex.net/stockquote.asmx?wsdl").andAlsoParseCDATA().GetQuote(symbol:"GOOG")
            response.Foobar == "Google Inc."

        then:
           IllegalArgumentException ex = thrown()
           ex.message == "The response doesn't have an element 'Foobar'. Maybe one of [Envelope, Body, GetQuoteResponse, GetQuoteResult, StockQuotes, Stock, Symbol, Last, Date, Time, Change, Open, High, Low, Volume, MktCap, PreviousClose, PercentageChange, AnnRange, Earns, P-E, Name]?"
    }


}
