package com.project.test.parameta.empleadosoap.repository;

import com.project.test.parameta.commons.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import static com.project.test.parameta.empleadosoap.utils.contants.Constantes.SOAP;

/**
 * Repositorio para la entidad {@link EmpleadoEntity}.
 * <p>
 * Este repositorio proporciona las operaciones CRUD y consultas personalizadas
 * para la tabla de empleados en la base de datos.
 * </p>
 */
@Repository(SOAP)
public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, String> {
    // Este repositorio hereda todas las operaciones básicas de JPA
}
