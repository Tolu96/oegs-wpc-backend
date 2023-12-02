package com.oegs.wpc.mapper;

import com.oegs.wpc.dto.WorkingHoursDTO;
import com.oegs.wpc.model.WorkingHours;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = UUID.class)
public interface WorkingHoursMapper {
    WorkingHoursMapper INSTANCE = Mappers.getMapper(WorkingHoursMapper.class);

    WorkingHoursDTO modelToDto(WorkingHours workingHours);

    List<WorkingHoursDTO> modelsToDtos(List<WorkingHours> workingHours);

    WorkingHours dtoToModel(WorkingHoursDTO workingHoursDTO);
}
