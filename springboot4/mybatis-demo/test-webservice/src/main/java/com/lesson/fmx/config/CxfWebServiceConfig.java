package com.lesson.fmx.config;




import com.lesson.fmx.service.HelloWebService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.ws.Endpoint;



@Configuration
public class CxfWebServiceConfig {

    @Autowired
    private Bus bus;

    @Autowired
    private HelloWebService helloWebService;

//    @Bean("cxfServletRegistration")
//    public ServletRegistrationBean dispatcherServlet(){
//    }

    @Bean
    public Endpoint endpoint(){
        EndpointImpl endpoint = new EndpointImpl(bus,helloWebService);
        endpoint.publish("/helloWebService");
        return  endpoint;
    }
}
