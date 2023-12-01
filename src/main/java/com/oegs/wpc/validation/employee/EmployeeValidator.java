package com.oegs.wpc.validation.employee;

import com.oegs.wpc.repository.employee.EmployeeRepository;
import com.oegs.wpc.model.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;


@Component
public class EmployeeValidator {

    public final String EMPLOYEE_NOT_FOUND_MESSAGE = "Employee with this ID does not exist and therefore could not be" +
            " found";
    public final String WRONG_PERMISSION = "You do not have the right permissions to execute this action";
    public final String UPDATE_NOT_POSSIBLE = "Update was not possible. Please try it again later";
    public final String FILL_ALL_FIELDS = "Please fill all fields";
    public final String NAME_TOO_SHORT = "First- or Lastname too short";
    public final String POSTCODE_INVALID = "Length of postcode is not valid";

    @Autowired
    private EmployeeRepository employeeRepository;

    public boolean creationPreCondition(Employee employee) {
        fieldLengthChecker(employee);
        isEmployedDateChecker(employee);
        return true;
    }

    public boolean updatePreCondition(Employee employee, UUID employeeId) {
        if (employeeExistenceChecker(employeeId)) {
            fieldLengthChecker(employee);
            isEmployedDateChecker(employee);
            permissionChecker();
            return true;
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT, UPDATE_NOT_POSSIBLE);
    }

    private void isEmployedDateChecker(Employee employee) {
        LocalDate employedSinceDate = LocalDate.now();
        if (employee.getIsEmployed() && employee.getEmployedSince() == null) {
            employee.setEmployedSince(employedSinceDate);
        } else if (employee.getIsEmployed()) {
            employee.setEmployedSince(employee.getEmployedSince());
        } else {
            employee.setEmployedSince(null);
        }
    }


    private void fieldLengthChecker(Employee employee) {
        if (employee.getFirstName().length() <= 3 || employee.getLastName().length() <= 3) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, NAME_TOO_SHORT);
        }
        if (String.valueOf(employee.getPostcode()).length() != 5) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, POSTCODE_INVALID);
        }
    }


    private void permissionChecker() {
        System.out.println("Hallo");
    }


    public boolean employeeExistenceChecker(UUID employeeId) {
        boolean exists = employeeRepository.existsById(employeeId);
        if (exists) {
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, EMPLOYEE_NOT_FOUND_MESSAGE);
        }
    }

}
