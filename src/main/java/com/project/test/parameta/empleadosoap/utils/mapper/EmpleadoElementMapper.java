package com.project.test.parameta.empleadosoap.utils.mapper;

import com.project.test.parameta.commons.dto.CargoDTO;
import com.project.test.parameta.commons.dto.EmpleadoDTO;
import com.project.test.parameta.commons.dto.TipoDocumentoDTO;
import com.project.test.parameta.commons.util.enums.CargoEnum;
import com.project.test.parameta.commons.util.enums.TipoDocumentoEnum;
import com.project.test.parameta.commons.util.helper.Utilidades;
import com.project.test.parameta.empleadosoap.gen.EmpleadoElement;
import org.mapstruct.Mapper;

/**
 * Mapper para convertir entre {@link EmpleadoElement} y {@link EmpleadoDTO}.
 * <p>
 * Proporciona métodos para mapear datos desde objetos generados por el servicio SOAP
 * hacia objetos de transferencia de datos (DTO) utilizados en el sistema.
 * </p>
 */
@Mapper(componentModel = "spring")
public interface EmpleadoElementMapper {

    /**
     * Convierte un objeto {@link EmpleadoElement} en un objeto {@link EmpleadoDTO}.
     * <p>
     * Este método realiza la conversión de los datos recibidos desde una solicitud SOAP
     * hacia el formato utilizado internamente en el sistema, incluyendo la conversión de
     * fechas de tipo {@link javax.xml.datatype.XMLGregorianCalendar} a {@link java.util.Date},
     * y la asociación de los enums {@link CargoEnum} y {@link TipoDocumentoEnum}.
     * </p>
     *
     * @param empleadoElement el objeto {@link EmpleadoElement} recibido en la solicitud SOAP.
     * @return un objeto {@link EmpleadoDTO} con los datos mapeados desde el objeto recibido.
     */
    default EmpleadoDTO elementToDto(EmpleadoElement empleadoElement) {
        return EmpleadoDTO.builder()
                .codigoEmpleado(empleadoElement.getCodigoEmpleado())
                .nombreEmpleado(empleadoElement.getNombreEmpleado())
                .apellidosEmpleado(empleadoElement.getApellidosEmpleado())
                .fechaNacimientoEmpleado(Utilidades.gregorianToDate(empleadoElement.getFechaNacimientoEmpleado()))
                .fechaVinculacionCompaniaEmpleado(Utilidades.gregorianToDate(empleadoElement.getFechaVinculacionEmpleado()))
                .cargoFk(CargoDTO.builder()
                        .idCargo(empleadoElement.getCargoFk().getIdCargo().intValue())
                        .nombreCargo(CargoEnum.valueOf(empleadoElement.getCargoFk().getNombreCargo()))
                        .build())
                .tipoDocumentoFk(TipoDocumentoDTO.builder()
                        .idTipoDocumento(empleadoElement.getTipoDocumentoFk().getIdTipoDocumento().intValue())
                        .nombreTipoDocumento(TipoDocumentoEnum.valueOf(empleadoElement.getTipoDocumentoFk().getNombreTipoDocumento()))
                        .build())
                .correoEmpleado(empleadoElement.getCorreoEmpleado())
                .salarioEmpleado(empleadoElement.getSalarioEmpleado())
                .numeroDocumentoEmpleado(empleadoElement.getNumeroDocumentoEmpleado())
                .hashPassword(empleadoElement.getHashPassword())
                .build();
    }
}
