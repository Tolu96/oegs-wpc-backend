package com.oegs.wpc.mapper;

import com.oegs.wpc.dto.BreakTimeDTO;
import com.oegs.wpc.model.BreakTime;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = UUID.class)
public interface BreakTimeMapper {

    BreakTimeMapper INSTANCE = Mappers.getMapper(BreakTimeMapper.class);

    BreakTimeDTO modelToDto(BreakTime breakTime);

    List<BreakTimeDTO> modelsToDtos(List<BreakTime> breakTime);

    BreakTime dtoToModel(BreakTimeDTO breakTimeDTO);

}
