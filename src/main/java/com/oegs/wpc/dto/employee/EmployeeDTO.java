package com.oegs.wpc.dto.employee;

import com.oegs.wpc.audit.AuditableEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class EmployeeDTO {
    String firstName;
    String lastName;
    String address;
    Integer postcode;
    String city;
    String gender;
    String nationality;
}

