package com.oegs.wpc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class EmployeeDTO {
    UUID employeeId;
    String firstName;
    String lastName;
    String address;
    int postcode;
    String city;
    String gender;
    String nationality;
    boolean employmentActive;
    LocalDate employedSince;
    int remainingVacation;

    @JsonIgnore
    public boolean employmentActive() {
        return employmentActive;
    }

    @JsonIgnore
    public LocalDate getEmployedSince() {
        return employedSince;
    }

}
