package com.oegs.wpc.dto;

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
    private double workedHoursToday;
    private double workedHoursSum;
    private LocalDateTime workHourStart;
    private LocalDateTime workHourEnd;
    private double breakTime;
}
