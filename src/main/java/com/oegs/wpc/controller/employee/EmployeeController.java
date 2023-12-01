package com.oegs.wpc.controller.employee;

import com.oegs.wpc.dto.employee.EmployeeDTO;
import com.oegs.wpc.service.employee.EmployeeService;
import com.oegs.wpc.model.employee.Employee;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping
    private ResponseEntity<List<EmployeeDTO>> getAllEmployee() {
        return ResponseEntity.ok().body(employeeService.getAllEmployee());
    }

    @GetMapping("{employeeId}")
    private ResponseEntity<Optional<EmployeeDTO>> getEmployeeById(@PathVariable("employeeId") UUID employeeId) {
        return ResponseEntity.ok().body(employeeService.getEmployeeById(employeeId));
    }

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody EmployeeDTO newEmployeeDTO) {
        return new ResponseEntity<>(employeeService.createNewEmployee(newEmployeeDTO), HttpStatus.CREATED);
    }

    @PutMapping(path = "{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("employeeId") UUID employeeId,
                                                      @RequestBody Employee employee) {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(employeeId, employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("employeeId") UUID employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>("Employee with the Id" + employeeId + " successfully deleted", HttpStatus.OK);
    }


}
