package com.oegs.wpc.dto;

import com.oegs.wpc.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class WorkingHoursDTO {
    UUID workingHoursId;
    LocalDateTime workHourStart;
    LocalDateTime workHourEnd;
    EmployeeDTO employee;
    StatusEnum status;
}
