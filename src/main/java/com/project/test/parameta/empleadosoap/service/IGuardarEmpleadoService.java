package com.project.test.parameta.empleadosoap.service;

import com.project.test.parameta.empleadosoap.gen.EmpleadoElement;
import com.project.test.parameta.empleadosoap.gen.GuardarEmpleadoResponse;

public interface IGuardarEmpleadoService {

    GuardarEmpleadoResponse guardarEmpleado(EmpleadoElement empleadoElement);

}
