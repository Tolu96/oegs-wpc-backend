package com.oegs.wpc.model;

import com.oegs.wpc.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkingHours extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "working_hours_id")
    private UUID workingHoursId;

    @Column(name = "worked_hours_today")
    private double workedHoursToday;

    @Column(name = "worked_hours_sum")
    private double workedHoursSum;

    @Column(name = "work_hour_start")
    private LocalDateTime workHourStart;

    @Column(name = "work_hour_end")
    private LocalDateTime workHourEnd;

    @Column(name = "break_time")
    private double breakTime;

}
