package com.project.test.parameta.empleadosoap.repository;

import com.project.test.parameta.commons.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import static com.project.test.parameta.empleadosoap.utils.contants.Constantes.SOAP;

@Repository(SOAP)
public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, String> {
}
