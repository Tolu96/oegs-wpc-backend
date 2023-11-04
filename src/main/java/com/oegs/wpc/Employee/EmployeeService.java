package com.oegs.wpc.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeValidator employeeValidator;

    public List<Employee> getAllEmployee() {
        return new ArrayList<>(employeeRepository.findAll().stream().toList());
    }

    public Optional<Employee> getEmployeeById(UUID employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Employee with ID + " + employeeId + "does not " + "exist");
        } else {
            return employeeRepository.findById(employeeId);
        }
    }

    public Employee createNewEmployee(Employee newEmployee) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (employeeRepository.existsById(newEmployee.getEmployeeId())) {
            newEmployee.getCreatedAt().format(dtf);
            newEmployee.getUpdatedAt().format(dtf);
            return employeeRepository.save(newEmployee);

        }
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Please fill all fields");
    }

    @Transactional
    public void updateEmployee(UUID employeeId, Employee updatedEmployee) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (!employeeValidator.updatedPreCondition(updatedEmployee)) {
            Employee oldEmployee =
                    (employeeRepository.findById(employeeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee does not exist")));
            oldEmployee.setUpdatedAt(LocalDateTime.now());
            oldEmployee.getUpdatedAt().format(dtf);

        }
    }
}