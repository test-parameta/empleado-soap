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

/**
 * Configuración para el servicio web SOAP.
 * <p>
 * Esta clase configura los beans necesarios para habilitar y exponer servicios SOAP
 * en la aplicación, incluyendo la configuración del esquema WSDL y los mapeos de URL.
 * </p>
 */
@EnableWs
@Configuration
public class SoapWebServiceConfig {

    /**
     * Configura un bean de Jaxb2Marshaller para manejar la conversión entre objetos Java
     * y XML en el contexto de los servicios web.
     *
     * @return una instancia de {@link Jaxb2Marshaller}.
     */
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(CONTEXT_PATH);
        return marshaller;
    }

    /**
     * Registra un servlet para manejar solicitudes SOAP.
     *
     * @param applicationContext el contexto de la aplicación para asociarlo al servlet.
     * @return una instancia de {@link ServletRegistrationBean} configurada con el servlet de MessageDispatcher.
     */
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setTransformWsdlLocations(true);
        servlet.setApplicationContext(applicationContext);
        return new ServletRegistrationBean<>(servlet, URL_MAPPINGS);
    }

    /**
     * Configura el WSDL para el servicio SOAP, incluyendo el esquema XSD asociado.
     *
     * @param schema el esquema XSD para validar las solicitudes y respuestas.
     * @return una instancia de {@link DefaultWsdl11Definition}.
     */
    @Bean(name = NAME_BEAN_SOAP)
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema) {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName(PORT_TYPE_NAME);
        definition.setLocationUri(LOCATION_URI);
        definition.setTargetNamespace(Constantes.NAMESPACE_URI);
        definition.setSchema(schema);
        return definition;
    }

    /**
     * Carga el esquema XSD para el servicio web SOAP.
     *
     * @return una instancia de {@link XsdSchema} basada en el archivo XSD especificado.
     */
    @Bean
    public XsdSchema empleadoSchema() {
        return new SimpleXsdSchema(new org.springframework.core.io.ClassPathResource(DIRECCION_XSD));
    }

}
