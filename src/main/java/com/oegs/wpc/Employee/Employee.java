package com.oegs.wpc.Employee;


import com.oegs.wpc.enums.GenderEnum;
import com.oegs.wpc.enums.NationalityEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID employeeId;
    private String firstName;
    private String lastName;
    private String address;
    private int postCode;
    private String town;
    private GenderEnum genderEnum;
    private NationalityEnum nationality;
    private LocalDate employedSince;
    private boolean isEmployed;
    private final LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    public Employee(UUID employeeId, String firstName, String lastName, String address, int postCode, String town,
                    GenderEnum genderEnum, NationalityEnum nationality, LocalDate employedSince, boolean isEmployed,
                    LocalDateTime updatedAt) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postCode = postCode;
        this.town = town;
        this.genderEnum = genderEnum;
        this.nationality = nationality;
        this.employedSince = employedSince;
        this.isEmployed = isEmployed;
        this.updatedAt = updatedAt;
    }
}
