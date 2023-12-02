package com.oegs.wpc.dto;

import com.oegs.wpc.enums.AbsenceStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class AbsenceDTO {
    UUID absenceId;
    AbsenceStatusEnum absenceStatus;
    String description;
    LocalDateTime absenceStart;
    LocalDateTime absenceEnd;
    int totalVacation;
    int remainingVacation;
    EmployeeDTO employee;
}
