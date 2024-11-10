package com.project.test.parameta.empleadosoap.controller;

import com.project.test.parameta.empleadosoap.gen.EmpleadoElement;
import com.project.test.parameta.empleadosoap.gen.GuardarEmpleadoRequest;
import com.project.test.parameta.empleadosoap.gen.GuardarEmpleadoResponse;
import com.project.test.parameta.empleadosoap.service.IGuardarEmpleadoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmpleadoEndpointTest {

    @InjectMocks
    private EmpleadoEndpoint empleadoEndpoint;

    @Mock
    private IGuardarEmpleadoService iGuardarEmpleadoService;

    @Test
    void guardarEmpleado_exitoso() {
        // Datos de prueba
        GuardarEmpleadoRequest request = new GuardarEmpleadoRequest();
        EmpleadoElement empleadoElement = new EmpleadoElement();
        empleadoElement.setCodigoEmpleado("EMP123");
        empleadoElement.setNombreEmpleado("Juan");
        empleadoElement.setApellidosEmpleado("Pérez");
        request.setEmpleado(empleadoElement);

        GuardarEmpleadoResponse expectedResponse = new GuardarEmpleadoResponse();
        when(iGuardarEmpleadoService.guardarEmpleado(empleadoElement)).thenReturn(expectedResponse);

        // Llamada al método
        GuardarEmpleadoResponse response = empleadoEndpoint.guardarEmpleado(request);

        // Verificaciones
        assertNotNull(response, "La respuesta no debería ser nula");
        verify(iGuardarEmpleadoService, times(1)).guardarEmpleado(empleadoElement);
    }

}
