package com.project.test.parameta.empleadosoap.utils.mapper;

import com.project.test.parameta.commons.dto.CargoDTO;
import com.project.test.parameta.commons.dto.EmpleadoDTO;
import com.project.test.parameta.commons.dto.TipoDocumentoDTO;
import com.project.test.parameta.commons.util.enums.CargoEnum;
import com.project.test.parameta.commons.util.enums.TipoDocumentoEnum;
import com.project.test.parameta.commons.util.helper.Utilidades;
import com.project.test.parameta.empleadosoap.gen.EmpleadoElement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmpleadoElementMapper  {

    default EmpleadoDTO elementToDto(EmpleadoElement empleadoElement){

        return EmpleadoDTO.builder()
                .codigoEmpleado(empleadoElement.getCodigoEmpleado())
                .nombreEmpleado(empleadoElement.getNombreEmpleado())
                .apellidosEmpleado(empleadoElement.getApellidosEmpleado())
                .fechaNacimientoEmpleado(Utilidades.gregorianToDate(empleadoElement.getFechaNacimientoEmpleado()))
                .fechaVinculacionCompaniaEmpleado(Utilidades.gregorianToDate(empleadoElement.getFechaVinculacionEmpleado()))
                .cargoFk(CargoDTO.builder().idCargo(empleadoElement.getCargoFk().getIdCargo().intValue()).nombreCargo(CargoEnum.valueOf(empleadoElement.getCargoFk().getNombreCargo())).build())
                .tipoDocumentoFk(TipoDocumentoDTO.builder().idTipoDocumento(empleadoElement.getTipoDocumentoFk().getIdTipoDocumento().intValue()).nombreTipoDocumento(TipoDocumentoEnum.valueOf(empleadoElement.getTipoDocumentoFk().getNombreTipoDocumento())).build())
                .correoEmpleado(empleadoElement.getCorreoEmpleado())
                .salarioEmpleado(empleadoElement.getSalarioEmpleado())
                .numeroDocumentoEmpleado(empleadoElement.getNumeroDocumentoEmpleado())
                .hashPassword(empleadoElement.getHashPassword())
                .build();

    }

}
