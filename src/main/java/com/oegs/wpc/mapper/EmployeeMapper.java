package com.oegs.wpc.mapper;

import com.oegs.wpc.dto.EmployeeDTO;
import com.oegs.wpc.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = UUID.class)
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDTO modelToDto(Employee employee);

    List<EmployeeDTO> modelsToDtos(List<Employee> employee);

    Employee dtoToModel(EmployeeDTO employeeDTO);

}
