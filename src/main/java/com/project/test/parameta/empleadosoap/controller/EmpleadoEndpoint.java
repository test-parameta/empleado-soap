package com.project.test.parameta.empleadosoap.controller;

import com.project.test.parameta.empleadosoap.gen.GuardarEmpleadoRequest;
import com.project.test.parameta.empleadosoap.gen.GuardarEmpleadoResponse;
import com.project.test.parameta.empleadosoap.service.IGuardarEmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import static com.project.test.parameta.empleadosoap.utils.contants.Constantes.LOCAL_PART;
import static com.project.test.parameta.empleadosoap.utils.contants.Constantes.NAMESPACE_URI;

/**
 * Endpoint para gestionar operaciones relacionadas con empleados en el servicio SOAP.
 * <p>
 * Este endpoint maneja las solicitudes SOAP relacionadas con la operación de guardar empleados.
 * Utiliza las anotaciones de Spring Web Services para procesar las solicitudes y devolver respuestas
 * en formato SOAP.
 * </p>
 */
@Endpoint
@RequiredArgsConstructor
public class EmpleadoEndpoint {

    /**
     * Servicio para manejar la lógica de guardar empleados.
     */
    private final IGuardarEmpleadoService iGuardarEmpleadoService;

    /**
     * Maneja la solicitud SOAP para guardar un empleado.
     * <p>
     * Este método se asocia a un espacio de nombres y a una operación específica
     * definida en el archivo WSDL del servicio SOAP. Recibe una solicitud en formato SOAP,
     * delega la lógica al servicio correspondiente, y devuelve una respuesta en formato SOAP.
     * </p>
     *
     * @param request el objeto {@link GuardarEmpleadoRequest} recibido en la solicitud SOAP,
     *                que contiene la información del empleado a guardar.
     * @return un objeto {@link GuardarEmpleadoResponse} con el resultado de la operación,
     *         incluyendo el estado y los datos relacionados.
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = LOCAL_PART)
    @ResponsePayload
    public GuardarEmpleadoResponse guardarEmpleado(@RequestPayload GuardarEmpleadoRequest request) {
        return iGuardarEmpleadoService.guardarEmpleado(request.getEmpleado());
    }
}
