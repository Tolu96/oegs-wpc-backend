package com.oegs.wpc.Employee;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
@AllArgsConstructor
public class EmployeeValidator {

    public boolean creationPreCondition(Employee employee) {
        employee.setUpdatedAt(LocalDateTime.now());
        employee.setEmployeeId(UUID.randomUUID());
        List<Employee> newEmployee = Collections.singletonList(employee);
        return newEmployee.stream().anyMatch(Objects::nonNull);
    }

    public void patchPreCondition(Employee employee) {
        employee.setUpdatedAt(LocalDateTime.now());

    }

}
