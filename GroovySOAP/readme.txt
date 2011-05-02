
SOAPService validService = new SoapUIBackedSOAPService("http://www.holidaywebservice.com/HolidayService_v2/HolidayService2.asmx", "hol");
Map params = new HashMap();
params.put("countryCode", "GreatBritain");
params.put("year", "2011");
params.put("month", "5");
SOAPResponse response = validService.invokeOperation("GetHolidaysForMonth", params);
response.getXmlAsText(); //Gives the soap response
