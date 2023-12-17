package com.oegs.wpc.dto;

import com.oegs.wpc.enums.AbsenceStatusEnum;
import com.oegs.wpc.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class AbsenceDTO {
    UUID absenceId;
    AbsenceStatusEnum absenceStatus;
    String description;
    LocalDate absenceStart;
    LocalDate absenceEnd;
    EmployeeDTO employee;
    StatusEnum status;
}
