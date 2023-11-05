package com.oegs.wpc.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
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
        if (employeeValidator.creationPreCondition(newEmployee)) {
            return employeeRepository.save(newEmployee);
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Please fill all fields");
    }

    public void deleteEmployee(UUID employeeId) {
        boolean exists = employeeRepository.existsById(employeeId);
        if (!exists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Employee with id " + employeeId + " does not " + "exist");
        } else {
            employeeRepository.deleteById(employeeId);
        }
    }

    @Transactional
    public Employee patchEmployee(UUID employeeId, Map<String, Object> fields) {
        Optional<Employee> oldEmployee =
                Optional.ofNullable((employeeRepository.findById(employeeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee does not exist"))));

        if (oldEmployee.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Employee.class, key);
                Objects.requireNonNull(field).setAccessible(true);
                ReflectionUtils.setField(field, oldEmployee.get(), value);
            });

            employeeValidator.patchPreCondition(Objects.requireNonNull(oldEmployee.stream().findFirst().orElse(null)));
            return employeeRepository.save(oldEmployee.get());
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Update was not possible. Please try it again later");
    }
}