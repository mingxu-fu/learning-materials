package com.lesson.fmx.service.impl;

import com.lesson.fmx.service.HelloWebService;
import org.springframework.stereotype.Service;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@Service
@WebService(
        endpointInterface ="com.lesson.fmx.service.HelloWebService",
        serviceName ="HelloWebService",
        targetNamespace = "http://fmx.lesson.com"
)
public class HelloWebServiceImpl  implements HelloWebService {
    @Override
    public String hello(String arg0, String arg1) {
        return "发布接口成功";
    }
}
