package com.project.test.parameta.empleadosoap.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.test.parameta.commons.dto.EmpleadoDTO;
import com.project.test.parameta.commons.entity.CargoEntity;
import com.project.test.parameta.commons.entity.EmpleadoEntity;
import com.project.test.parameta.commons.entity.TipoDocumentoEntity;
import com.project.test.parameta.commons.util.enums.CargoEnum;
import com.project.test.parameta.commons.util.enums.TipoDocumentoEnum;
import com.project.test.parameta.commons.util.mappers.EmpleadoMapper;
import com.project.test.parameta.empleadosoap.gen.*;
import com.project.test.parameta.empleadosoap.repository.EmpleadoRepository;
import com.project.test.parameta.empleadosoap.service.impl.GuardarEmpleadoService;
import com.project.test.parameta.empleadosoap.utils.mapper.EmpleadoElementMapper;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import java.math.BigInteger;
import java.util.Calendar;

import static com.project.test.parameta.empleadosoap.utils.contants.Constantes.CREACION_EMPLEADO_OK;
import static com.project.test.parameta.empleadosoap.utils.contants.Constantes.ERROR_GUARDADO_EMPLEADO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GuaradarEmpleadoServiceTest {

    @InjectMocks private GuardarEmpleadoService guardarEmpleadoService;

    @Mock private EmpleadoRepository empleadoRepository;

    @Spy private PasswordEncoder passwordEncoder;

    @Spy private EmpleadoElementMapper empleadoElementMapper;

    @Mock private EmpleadoMapper empleadoMapper;

    @Test
    void guardarEmpleadoExitoso() throws  DatatypeConfigurationException {
        // Datos de prueba
        TipoDocumentoElement tipoDocumento = new TipoDocumentoElement();
        tipoDocumento.setIdTipoDocumento(BigInteger.valueOf(1));
        tipoDocumento.setNombreTipoDocumento(TipoDocumentoEnum.CC.name());
        CargoElement cargo = new CargoElement();
        cargo.setIdCargo(BigInteger.valueOf(2));
        cargo.setNombreCargo(CargoEnum.AS.name());
        EmpleadoElement empleado = new EmpleadoElement();
        empleado.setCodigoEmpleado("EMP12345");
        empleado.setNombreEmpleado("Juan");
        empleado.setApellidosEmpleado("Pérez");
        empleado.setNumeroDocumentoEmpleado("123456789");
        XMLGregorianCalendar fechaNacimiento = DatatypeFactory.newInstance()
                .newXMLGregorianCalendar("1990-05-15");
        empleado.setFechaNacimientoEmpleado(fechaNacimiento);
        XMLGregorianCalendar fechaVinculacion = DatatypeFactory.newInstance()
                .newXMLGregorianCalendar("2020-01-01");
        empleado.setFechaVinculacionEmpleado(fechaVinculacion);
        empleado.setCargoFk(cargo);
        empleado.setTipoDocumentoFk(tipoDocumento);
        empleado.setCorreoEmpleado("juan.perez@example.com");
        empleado.setSalarioEmpleado(5000.00);
        empleado.setHashPassword("hashedPassword123");
        EmpleadoDTO empleadoGuardadoDTO = new EmpleadoDTO();
        empleadoGuardadoDTO.setHashPassword("plainPassword");
        TipoDocumentoEntity tipoDocumentoEntity = new TipoDocumentoEntity();
        tipoDocumentoEntity.setIdTipoDocumento(1);
        tipoDocumentoEntity.setNombreTipoDocumento(TipoDocumentoEnum.CC);
        CargoEntity cargoEntity = new CargoEntity();
        cargoEntity.setIdCargo(2);
        cargoEntity.setNombreCargo(CargoEnum.DB);
        EmpleadoEntity empleadoEntity = new EmpleadoEntity();
        empleadoEntity.setCodigoEmpleado("EMP01");
        empleadoEntity.setNombreEmpleado("Juan");
        empleadoEntity.setApellidosEmpleado("Pérez");
        empleadoEntity.setTipoDocumentoFk(tipoDocumentoEntity);
        empleadoEntity.setNumeroDocumentoEmpleado("123456789");

        // Establecer fechas
        Calendar fechaNacimientoEntity = Calendar.getInstance();
        fechaNacimientoEntity.set(1990, Calendar.MAY, 15); // 15 de mayo de 1990
        empleadoEntity.setFechaNacimientoEmpleado(fechaNacimientoEntity.getTime());

        Calendar fechaVinculacionEntity = Calendar.getInstance();
        fechaVinculacionEntity.set(2020, Calendar.JANUARY, 1); // 1 de enero de 2020
        empleadoEntity.setFechaVinculacionCompaniaEmpleado(fechaVinculacionEntity.getTime());

        empleadoEntity.setCargoFk(cargoEntity);
        empleadoEntity.setSalarioEmpleado(5000.00);
        empleadoEntity.setCorreoEmpleado("juan.perez@example.com");
        empleadoEntity.setHashPassword("hashedPassword123");

        String jsonEmpleado = "{\"nombreEmpleado\":\"Test\"}";
        when(empleadoRepository.save(empleadoEntity)).thenReturn(empleadoEntity);
        when(empleadoMapper.dtoToEntity(any(EmpleadoDTO.class))).thenReturn(empleadoEntity);
        when(empleadoMapper.entityToDto(any(EmpleadoEntity.class))).thenReturn(new EmpleadoDTO());
        // Llamar al método
        GuardarEmpleadoResponse response = guardarEmpleadoService.guardarEmpleado(empleado);

        // Verificaciones
        assertNotNull(response);
        RespuestaGeneralElement respuesta = response.getRespuesta();
        assertEquals(HttpStatusElement.OK, respuesta.getStatus());
        assertEquals(CREACION_EMPLEADO_OK, respuesta.getMessage());

        // Verificar interacciones
        verify(empleadoElementMapper, times(1)).elementToDto(empleado);
        verify(passwordEncoder, times(1)).encode(anyString());
        verify(empleadoRepository, times(1)).save(empleadoEntity);
    }

    @Test
    void guardarEmpleadoFallido() throws  DatatypeConfigurationException {
        // Datos de prueba
        TipoDocumentoElement tipoDocumento = new TipoDocumentoElement();
        tipoDocumento.setIdTipoDocumento(BigInteger.valueOf(1));
        tipoDocumento.setNombreTipoDocumento(TipoDocumentoEnum.CC.name());
        CargoElement cargo = new CargoElement();
        cargo.setIdCargo(BigInteger.valueOf(2));
        cargo.setNombreCargo(CargoEnum.AS.name());
        EmpleadoElement empleado = new EmpleadoElement();
        empleado.setCodigoEmpleado("EMP12345");
        empleado.setNombreEmpleado("Juan");
        empleado.setApellidosEmpleado("Pérez");
        empleado.setNumeroDocumentoEmpleado("123456789");
        XMLGregorianCalendar fechaNacimiento = DatatypeFactory.newInstance()
                .newXMLGregorianCalendar("1990-05-15");
        empleado.setFechaNacimientoEmpleado(fechaNacimiento);
        XMLGregorianCalendar fechaVinculacion = DatatypeFactory.newInstance()
                .newXMLGregorianCalendar("2020-01-01");
        empleado.setFechaVinculacionEmpleado(fechaVinculacion);
        empleado.setCargoFk(cargo);
        empleado.setTipoDocumentoFk(tipoDocumento);
        empleado.setCorreoEmpleado("juan.perez@example.com");
        empleado.setSalarioEmpleado(5000.00);
        empleado.setHashPassword("hashedPassword123");

        when(passwordEncoder.encode(anyString())).thenThrow(new RuntimeException("Error inesperado"));

        // Llamar al método
        GuardarEmpleadoResponse response = guardarEmpleadoService.guardarEmpleado(empleado);

        // Verificaciones
        assertNotNull(response);
        RespuestaGeneralElement respuesta = response.getRespuesta();
        assertEquals(HttpStatusElement.INTERNAL_SERVER_ERROR, respuesta.getStatus());
        assertEquals(ERROR_GUARDADO_EMPLEADO, respuesta.getMessage());
        assertNull(respuesta.getData());

        // Verificar interacciones
        verify(empleadoElementMapper, times(1)).elementToDto(empleado);
        verify(passwordEncoder, times(1)).encode(anyString());
        verifyNoInteractions(empleadoRepository);
    }

}
