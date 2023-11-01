package com.oegs.wpc.Employee;


import com.oegs.wpc.enums.GenderEnum;
import com.oegs.wpc.enums.NationalityEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String address;
    @NotNull
    private int postCode;
    @NotNull
    private String town;
    @NotNull
    private GenderEnum gender;
    @NotNull
    private NationalityEnum nationality;
    @NotNull
    private LocalDate employedSince;
    @NotNull
    private final LocalDateTime createdAt = LocalDateTime.now();
    @NotNull
    private LocalDateTime updatedAt;

    private boolean isEmployed;

    public Employee(UUID employeeId, String firstName, String lastName, String address, int postCode, String town,
                    GenderEnum gender, NationalityEnum nationality, LocalDate employedSince, boolean isEmployed,
                    LocalDateTime updatedAt) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postCode = postCode;
        this.town = town;
        this.gender = gender;
        this.nationality = nationality;
        this.employedSince = employedSince;
        this.isEmployed = isEmployed;
        this.updatedAt = updatedAt;
    }
}
