package com.oegs.wpc.model.employee;

import com.oegs.wpc.audit.AuditableEntity;
import com.oegs.wpc.enums.GenderEnum;
import com.oegs.wpc.enums.NationalityEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "employee_id")
    private UUID employeeId;
    private String firstName;
    private String lastName;
    private String address;
    private int postcode;
    private String city;
    private GenderEnum gender;
    private NationalityEnum nationality;
    private LocalDate employedSince;
    private Boolean isEmployed = true;

}
