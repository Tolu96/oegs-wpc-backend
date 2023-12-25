package com.oegs.wpc.service;


import com.oegs.wpc.dto.BreakTimeDTO;
import com.oegs.wpc.dto.EmployeeDTO;
import com.oegs.wpc.mapper.BreakTimeMapper;
import com.oegs.wpc.mapper.EmployeeMapper;
import com.oegs.wpc.model.BreakTime;
import com.oegs.wpc.model.Employee;
import com.oegs.wpc.repository.BreakTimeRepository;
import com.oegs.wpc.repository.EmployeeRepository;
import com.oegs.wpc.validation.BreakTimeValidator;
import com.oegs.wpc.validation.EmployeeValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BreakTimeService extends BreakTimeValidator {

    private BreakTimeRepository breakTimeRepository;
    private BreakTimeMapper breakTimeMapper;
    private EmployeeMapper employeeMapper;
    private EmployeeRepository employeeRepository;
    private EmployeeValidator employeeValidator;

    public List<BreakTimeDTO> getAllBreakTimes() {
        return breakTimeMapper.modelsToDtos(breakTimeRepository.findAll());
    }

    public List<BreakTimeDTO> getAllBreakTimesForEmployee(UUID employeeId) {
        return breakTimeMapper.modelsToDtos(breakTimeRepository
                .findAll()
                .stream()
                .filter(t -> t.getEmployee().getEmployeeId().equals(employeeId))
                .collect(Collectors.toList()));
    }

    @Transactional
    public BreakTimeDTO createNewBreakTime(BreakTimeDTO breakTimeDTO) {
        BreakTime breakTime = breakTimeMapper.dtoToModel(breakTimeDTO);

        employeeValidator.employeeExistenceChecker(breakTime.getEmployee().getEmployeeId());
        EmployeeDTO employeeDto =
                employeeMapper.modelToDto(employeeRepository.findById(breakTime.getEmployee().getEmployeeId()).get());
        Employee employee = employeeMapper.dtoToModel(employeeDto);
        breakTime.setEmployee(employee);

        creationPreCondition(breakTime);

        return breakTimeMapper.modelToDto(breakTimeRepository.save(breakTime));
    }

    @Transactional
    public BreakTimeDTO updateBreakTime(UUID breakTimeId, BreakTime breakTimeToUpdate) {
        breakTimeToUpdate.setBreakTimeId(breakTimeId);
        breakTimeToUpdate.setEmployee(breakTimeRepository.findById(breakTimeId).get().getEmployee());
        return breakTimeMapper.modelToDto(breakTimeRepository.save(breakTimeToUpdate));
    }

    public void deleteBreakTime(UUID breakTimeId) {
        if (breakTimeExistenceChecker(breakTimeId)) {
            breakTimeRepository.deleteById(breakTimeId);
        }
    }


}
