package com.project.test.parameta.empleadosoap.utils.contants;

/**
 * Clase de constantes para el servicio SOAP de empleados.
 * <p>
 * Contiene valores constantes utilizados en diferentes partes del sistema,
 * como configuraciones de namespace, cadenas de caracteres, configuraciones de correo electrónico,
 * y otros valores relevantes.
 * </p>
 */
public class Constantes {

    /** URI del espacio de nombres para el servicio SOAP. */
    public static final String NAMESPACE_URI = "http://example.com/soap";

    /** Mensaje de éxito al crear un empleado. */
    public static final String CREACION_EMPLEADO_OK = "Se creó correctamente el empleado";

    /** Mensaje de error para el guardado de un empleado con detalles. */
    public static final String CREACION_EMPLEADO_ERROR = "Error en el guardado del empleado {}";

    /** Mensaje de error genérico para el guardado de un empleado. */
    public static final String ERROR_GUARDADO_EMPLEADO = "Error en el guardado del empleado";

    /** Caracteres permitidos para generar contraseñas aleatorias. */
    public static final String CARACTERES_PASSWORD = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_+=<>?";

    /** Caracteres permitidos para generar nombres de usuario en correos electrónicos. */
    public static final String CARACTERES_CORREO = "abcdefghijklmnopqrstuvwxyz0123456789";

    /** Lista de dominios disponibles para generar correos electrónicos. */
    public static final String[] DOMINIOS = {"gmail.com", "yahoo.com", "outlook.com", "hotmail.com", "example.com"};

    /** Carácter de arroba utilizado en correos electrónicos. */
    public static final char ARROBA = '@';

    /** Longitud máxima para contraseñas generadas automáticamente. */
    public static final int LENGTH_MAX = 10;

    /** Nombre del localPart utilizado en las solicitudes SOAP. */
    public static final String LOCAL_PART = "guardarEmpleadoRequest";

    /** Context path para las clases generadas del servicio SOAP. */
    public static final String CONTEXT_PATH = "com.project.test.parameta.empleadosoap.gen";

    /** Mapeo de URL para las solicitudes SOAP. */
    public static final String URL_MAPPINGS = "/ws/*";

    /** Nombre del qualifier utilizado para identificar el bean del servicio SOAP. */
    public static final String SOAP = "soap";

    /** Nombre del portType definido en el WSDL. */
    public static final String PORT_TYPE_NAME = "EmpleadoPort";

    /** URI de ubicación para el servicio SOAP. */
    public static final String LOCATION_URI = "/ws";

    /** Nombre del bean definido para el servicio SOAP. */
    public static final String NAME_BEAN_SOAP = "empleado-soap";

    /** Dirección del archivo XSD utilizado para definir el esquema del servicio SOAP. */
    public static final String DIRECCION_XSD = "empleado.xsd";

}
