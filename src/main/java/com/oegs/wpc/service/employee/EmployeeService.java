package com.oegs.wpc.service.employee;

import com.oegs.wpc.mapper.employee.EmployeeMapper;
import com.oegs.wpc.repository.employee.EmployeeRepository;
import com.oegs.wpc.dto.employee.EmployeeDTO;
import com.oegs.wpc.model.employee.Employee;
import com.oegs.wpc.validation.employee.EmployeeValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
public class EmployeeService extends EmployeeValidator {

    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;

    public List<EmployeeDTO> getAllEmployee() {
        return employeeMapper.modelsToDtos(employeeRepository.findAll());

    }

    public Optional<EmployeeDTO> getEmployeeById(UUID employeeId) {
        if (employeeExistenceChecker(employeeId)) {
            return Optional.ofNullable(employeeMapper.modelToDto(employeeRepository.findById(employeeId).orElseThrow()));
        }
        return Optional.empty();
    }

    @Transactional
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.dtoToModel(employeeDTO);
        if (creationPreCondition(employee)) {
            return employeeMapper.modelToDto(employeeRepository.save(employee));
        }
        return null;
    }

    @Transactional
    public EmployeeDTO updateEmployee(UUID employeeId, Employee employeeToUpdate) {
        if (updatePreCondition(employeeToUpdate, employeeId)) {
            employeeToUpdate.setEmployeeId(employeeId);
            return employeeMapper.modelToDto(employeeRepository.save(employeeToUpdate));
        }
        return null;
    }

    public void deleteEmployee(UUID employeeId) {
        if (employeeExistenceChecker(employeeId)) {
            employeeRepository.deleteById(employeeId);
        }
    }
}