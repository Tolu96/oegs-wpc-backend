package com.oegs.wpc.model;

import com.oegs.wpc.audit.AuditableEntity;
import com.oegs.wpc.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class WorkingHours extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "working_hours_id")
    private UUID workingHoursId;

    @Column(name = "work_hour_start")
    private LocalDateTime workHourStart;

    @Column(name = "work_hour_end")
    private LocalDateTime workHourEnd;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employee employee;

    @Column(name = "status")
    private StatusEnum status;

}
