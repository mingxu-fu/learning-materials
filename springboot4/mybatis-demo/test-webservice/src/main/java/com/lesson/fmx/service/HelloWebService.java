package com.lesson.fmx.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(
//        name = "HelloWebService",
        targetNamespace = "http://fmx.lesson.com"
)

public interface HelloWebService {
    @WebMethod
    @WebResult(name = "String",targetNamespace = "")
    String hello(@WebParam(name = "arg0" ,targetNamespace = "") String arg0,@WebParam(name = "arg1",targetNamespace = "") String arg1 );
}
