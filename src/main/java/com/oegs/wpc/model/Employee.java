package com.oegs.wpc.model;

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
@Builder(toBuilder = true)
public class Employee extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "employee_id")
    private UUID employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String address;
    private int postcode;
    private String city;
    private GenderEnum gender;
    private NationalityEnum nationality;

    @Column(name = "employed_since")
    private LocalDate employedSince;

    @Column(name = "is_employed")
    private boolean isEmployed;

    @Column(name = "remaining_vacation")
    private int remainingVacation;
}
