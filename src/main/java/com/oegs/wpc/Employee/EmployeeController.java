package com.oegs.wpc.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    private List<Employee> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("{employeeId}")
    private Optional<Employee> getEmployeeById(UUID employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping(value = "/", produces = "application/json")
    public Employee createNewEmployee(@RequestBody Employee newEmployee) {
        return employeeService.createNewEmployee(newEmployee);
    }

    @PatchMapping(path = "{employeeId}")
    public Employee patchEmployee(@PathVariable("employeeId") @RequestBody UUID employeeId, @RequestBody Map<String,
            Object> fields) {
        return employeeService.patchEmployee(employeeId, fields);
    }

}
