package com.oegs.wpc.service;

import com.oegs.wpc.dto.EmployeeDTO;
import com.oegs.wpc.mapper.EmployeeMapper;
import com.oegs.wpc.model.Employee;
import com.oegs.wpc.repository.EmployeeRepository;
import com.oegs.wpc.validation.EmployeeValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        creationPreCondition(employee);
        return employeeMapper.modelToDto(employeeRepository.save(employee));
    }

    @Transactional
    public EmployeeDTO updateEmployee(UUID employeeId, Employee employeeToUpdate) {
        updatePreCondition(employeeToUpdate, employeeId);
        employeeToUpdate.setEmployeeId(employeeId);
        return employeeMapper.modelToDto(employeeRepository.save(employeeToUpdate));
    }

    public void deleteEmployee(UUID employeeId) {
        if (employeeExistenceChecker(employeeId)) {
            employeeRepository.deleteById(employeeId);
        }
    }
}