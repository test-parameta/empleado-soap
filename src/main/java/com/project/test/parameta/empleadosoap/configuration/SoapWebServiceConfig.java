package com.project.test.parameta.empleadosoap.configuration;

import com.project.test.parameta.empleadosoap.utils.contants.Constantes;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import static com.project.test.parameta.empleadosoap.utils.contants.Constantes.*;

@EnableWs
@Configuration
public class SoapWebServiceConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(CONTEXT_PATH);
        return marshaller;
    }

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setTransformWsdlLocations(true);
        servlet.setApplicationContext(applicationContext);
        return new ServletRegistrationBean<>(servlet, URL_MAPPINGS);
    }

    @Bean(name = NAME_BEAN_SOAP)
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema) {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName(PORT_TYPE_NAME);
        definition.setLocationUri(LOCATION_URI);
        definition.setTargetNamespace(Constantes.NAMESPACE_URI);
        definition.setSchema(schema);
        return definition;
    }

    @Bean
    public XsdSchema empleadoSchema() {
        return new SimpleXsdSchema(new org.springframework.core.io.ClassPathResource(DIRECCION_XSD));
    }

}
