package com.oegs.wpc.model;

import com.oegs.wpc.audit.AuditableEntity;
import com.oegs.wpc.enums.AbsenceStatusEnum;
import com.oegs.wpc.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Absence extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "absence_id")
    private UUID absenceId;

    @Column(name = "absence_status")
    private AbsenceStatusEnum absenceStatus;

    private String description;

    @Column(name = "absence_start")
    private LocalDate absenceStart;

    @Column(name = "absence_end")
    private LocalDate absenceEnd;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employee employee;

    @Column(name = "status")
    private StatusEnum status;
}
