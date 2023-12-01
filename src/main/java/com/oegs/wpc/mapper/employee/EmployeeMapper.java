package com.oegs.wpc.mapper.employee;

import com.oegs.wpc.dto.employee.EmployeeDTO;
import com.oegs.wpc.model.employee.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = UUID.class)
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDTO modelToDto(Employee employee);

    List<EmployeeDTO> modelsToDtos(List<Employee> employee);

    Employee dtoToModel(EmployeeDTO employeeDTO);

}
