package com.oegs.wpc.dto;

import com.oegs.wpc.enums.StatusEnum;
import com.oegs.wpc.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class BreakTimeDTO {

    UUID breakTimeId;
    LocalDateTime breakStart;
    LocalDateTime breakEnd;
    StatusEnum status;
    Employee employee;

}
