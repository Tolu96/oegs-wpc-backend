package com.oegs.wpc.Employee;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
@AllArgsConstructor
public class EmployeeValidator {

    private EmployeeRepository employeeRepository;

    public boolean creationPreCondition(Employee employee) {

        employee.setEmployeeId(UUID.randomUUID());
        List<Employee> newEmployee = Collections.singletonList(employee);
        return newEmployee.stream().anyMatch(Objects::nonNull);
    }

    public boolean updatedPreCondition(Employee employee) {
        List<Employee> newEmployee = Collections.singletonList(employee);
        return newEmployee.stream().anyMatch(Objects::nonNull);
    }

}
