package com.oegs.wpc.mapper;

import com.oegs.wpc.dto.AbsenceDTO;
import com.oegs.wpc.model.Absence;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = UUID.class)
public interface AbsenceMapper {

    AbsenceMapper INSTANCE = Mappers.getMapper(AbsenceMapper.class);

    AbsenceDTO modelToDto(Absence absence);

    List<AbsenceDTO> modelsToDtos(List<Absence> absence);

    Absence dtoToModel(AbsenceDTO absenceDTO);
}
