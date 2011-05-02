package com.cadenceone.groovysoap

import com.cadenceone.simplesoap.SoapUIBackedSOAPService;

 public class GSOAP extends SoapUIBackedSOAPService {

     private boolean parseCDATA = false;

     GSOAP(String wsdl) {
         super(wsdl)
     }

     public andAlsoParseCDATA(){
         parseCDATA = true
         return this
     }

//     def methodMissing(String name, args) {
//       if(hasOperation(name)) {
//          return new GSOAPResponse(invokeOperation(name, args[0]))
//       }
//       String firstLetter = name.substring(0,1)
//       String capitalisedOperationName = name.replaceFirst(firstLetter, firstLetter.toUpperCase())
//       if(hasOperation(capitalisedOperationName)) {
//          return new GSOAPResponse(invokeOperation(capitalisedOperationName, args[0]))
//       }
//       else throw new MissingMethodException(name, getClass(), args)
//     }

     def methodMissing(String name, args) {
       String firstLetter = name.substring(0,1)
       String capitalisedOperationName = name.replaceFirst(firstLetter, firstLetter.toUpperCase())
         if (args){
            return new GSOAPResponse(invokeOperation(capitalisedOperationName, args[0]), parseCDATA)
         }
         else{
            return new GSOAPResponse(invokeOperation(capitalisedOperationName), parseCDATA)
         }
     }

 }