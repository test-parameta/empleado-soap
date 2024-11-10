package com.project.test.parameta.empleadosoap.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.test.parameta.commons.dto.EmpleadoDTO;
import com.project.test.parameta.commons.service.impl.JwtService;
import com.project.test.parameta.commons.util.helper.Utilidades;
import com.project.test.parameta.commons.util.mappers.EmpleadoMapper;
import com.project.test.parameta.empleadosoap.gen.EmpleadoElement;
import com.project.test.parameta.empleadosoap.gen.GuardarEmpleadoResponse;
import com.project.test.parameta.empleadosoap.gen.HttpStatusElement;
import com.project.test.parameta.empleadosoap.gen.RespuestaGeneralElement;
import com.project.test.parameta.empleadosoap.repository.EmpleadoRepository;
import com.project.test.parameta.empleadosoap.service.IGuardarEmpleadoService;
import com.project.test.parameta.empleadosoap.utils.mapper.EmpleadoElementMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Random;

import static com.project.test.parameta.empleadosoap.utils.contants.Constantes.*;

/**
 * Servicio para gestionar el guardado de empleados en el sistema.
 * <p>
 * Implementa la interfaz {@link IGuardarEmpleadoService} y proporciona
 * la lógica necesaria para recibir, procesar y almacenar información de empleados
 * utilizando un servicio web SOAP.
 * </p>
 */
@Service
@Log4j2
public class GuardarEmpleadoService implements IGuardarEmpleadoService {

    /**
     * Repositorio para la entidad {@link com.project.test.parameta.commons.entity.EmpleadoEntity}.
     */
    private final EmpleadoRepository empleadoRepository;

    /**
     * Mapper para convertir entre {@link EmpleadoElement} y {@link EmpleadoDTO}.
     */
    private final EmpleadoElementMapper empleadoElementMapper;

    /**
     * Codificador para encriptar contraseñas de empleados.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Mapper para convertir entre {@link EmpleadoDTO} y {@link com.project.test.parameta.commons.entity.EmpleadoEntity}.
     */
    private final EmpleadoMapper empleadoMapper;

    /**
     * Constructor del servicio.
     *
     * @param empleadoRepository      el repositorio para manejar las operaciones de la entidad Empleado.
     * @param empleadoElementMapper   el mapper para transformar elementos entre modelos SOAP y DTO.
     * @param passwordEncoder         el codificador para encriptar contraseñas.
     * @param empleadoMapper          el mapper para transformar entre DTO y entidad.
     */
    public GuardarEmpleadoService(
            @Qualifier(SOAP) EmpleadoRepository empleadoRepository,
            EmpleadoElementMapper empleadoElementMapper,
            PasswordEncoder passwordEncoder,
            EmpleadoMapper empleadoMapper) {
        this.empleadoRepository = empleadoRepository;
        this.empleadoElementMapper = empleadoElementMapper;
        this.passwordEncoder = passwordEncoder;
        this.empleadoMapper = empleadoMapper;
    }
    @Override
    @Transactional
    public GuardarEmpleadoResponse guardarEmpleado(EmpleadoElement empleadoElement) {
        GuardarEmpleadoResponse guardarEmpleadoResponse = new GuardarEmpleadoResponse();
        RespuestaGeneralElement respuesta = new RespuestaGeneralElement();
        try {
            EmpleadoDTO empleadoDTO = empleadoElementMapper.elementToDto(empleadoElement);
            ObjectMapper objectMapper = new ObjectMapper();
            String password = generarContrasena(LENGTH_MAX);
            empleadoDTO.setCodigoEmpleado(Utilidades.generarCodigo());
            empleadoDTO.setHashPassword(passwordEncoder.encode(password));
            empleadoDTO.setCorreoEmpleado(generarCorreo());
            empleadoDTO = empleadoMapper.entityToDto(empleadoRepository.save(empleadoMapper.dtoToEntity(empleadoDTO)));
            empleadoDTO.setHashPassword(password);
            respuesta.setData(objectMapper.writeValueAsString(empleadoDTO));
            respuesta.setMessage(CREACION_EMPLEADO_OK);
            respuesta.setStatus(HttpStatusElement.OK);
        }catch (Exception e){
            log.error(CREACION_EMPLEADO_ERROR, e.getMessage());
            respuesta.setMessage(ERROR_GUARDADO_EMPLEADO);
            respuesta.setStatus(HttpStatusElement.INTERNAL_SERVER_ERROR);
        }
        guardarEmpleadoResponse.setRespuesta(respuesta);
        return guardarEmpleadoResponse;
    }

    /**
     * Genera una contraseña aleatoria con una longitud específica.
     *
     * @param longitud la longitud deseada para la contraseña.
     * @return una contraseña aleatoria como cadena de texto.
     */
    private String generarContrasena(int longitud) {
        String caracteres = CARACTERES_PASSWORD;
        SecureRandom random = new SecureRandom();
        StringBuilder contrasena = new StringBuilder();

        for (int i = 0; i < longitud; i++) {
            int index = random.nextInt(caracteres.length());
            contrasena.append(caracteres.charAt(index));
        }

        return contrasena.toString();
    }

    /**
     * Genera un correo electrónico aleatorio para el empleado.
     * <p>
     * Combina un nombre de usuario aleatorio con un dominio seleccionado aleatoriamente
     * de una lista predefinida.
     * </p>
     *
     * @return una dirección de correo electrónico generada aleatoriamente.
     */
    private String generarCorreo() {
        String caracteres = CARACTERES_CORREO;
        String[] dominios = DOMINIOS;
        Random random = new Random();

        // Generar nombre de usuario (entre 5 y 10 caracteres)
        int longitudUsuario = 5 + random.nextInt(10); // Longitud aleatoria entre 5 y 10
        StringBuilder usuario = new StringBuilder();
        for (int i = 0; i < longitudUsuario; i++) {
            int index = random.nextInt(caracteres.length());
            usuario.append(caracteres.charAt(index));
        }

        // Seleccionar un dominio aleatorio
        String dominio = dominios[random.nextInt(dominios.length)];

        // Combinar nombre de usuario y dominio
        return usuario.append(ARROBA).append(dominio).toString();
    }


}
