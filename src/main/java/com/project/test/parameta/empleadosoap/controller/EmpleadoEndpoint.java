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

@Endpoint
@RequiredArgsConstructor
public class EmpleadoEndpoint {

    private final IGuardarEmpleadoService iGuardarEmpleadoService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = LOCAL_PART)
    @ResponsePayload
    public GuardarEmpleadoResponse guardarEmpleado(@RequestPayload GuardarEmpleadoRequest request) {
               return iGuardarEmpleadoService.guardarEmpleado(request.getEmpleado());
    }


}
