package com.project.test.parameta.empleadosoap.service;

import com.project.test.parameta.empleadosoap.gen.EmpleadoElement;
import com.project.test.parameta.empleadosoap.gen.GuardarEmpleadoResponse;

/**
 * Interfaz para el servicio de gestión de empleados en el contexto de SOAP.
 * <p>
 * Proporciona la definición del método para guardar información de un empleado
 * utilizando un servicio web SOAP.
 * </p>
 */
public interface IGuardarEmpleadoService {

    /**
     * Guarda un empleado en el sistema.
     * <p>
     * Este método recibe un objeto {@link EmpleadoElement} con la información
     * del empleado a guardar y devuelve una respuesta con el resultado de la operación.
     * </p>
     *
     * @param empleadoElement el objeto {@link EmpleadoElement} que contiene la información
     *                        del empleado que se va a guardar.
     * @return un objeto {@link GuardarEmpleadoResponse} que contiene el resultado de la
     *         operación, incluyendo el estado y los datos asociados.
     */
    GuardarEmpleadoResponse guardarEmpleado(EmpleadoElement empleadoElement);

}
