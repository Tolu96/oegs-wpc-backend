package com.oegs.wpc.service;

import com.oegs.wpc.dto.AbsenceDTO;
import com.oegs.wpc.dto.EmployeeDTO;
import com.oegs.wpc.mapper.AbsenceMapper;
import com.oegs.wpc.mapper.EmployeeMapper;
import com.oegs.wpc.model.Absence;
import com.oegs.wpc.model.Employee;
import com.oegs.wpc.repository.AbsenceRepository;
import com.oegs.wpc.repository.EmployeeRepository;
import com.oegs.wpc.validation.AbsenceValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AbsenceService extends AbsenceValidator {

    private AbsenceRepository absenceRepository;
    private EmployeeRepository employeeRepository;
    private AbsenceMapper absenceMapper;
    private EmployeeMapper employeeMapper;

    public List<AbsenceDTO> getAllAbsenceEntries() {
        return absenceMapper.modelsToDtos(absenceRepository.findAll());
    }

    public List<AbsenceDTO> getAllAbsenceEntriesForEmployee(UUID employeeId) {
        return absenceMapper.modelsToDtos(absenceRepository.findAll().stream().filter(t -> t.getEmployee().getEmployeeId().equals(employeeId)).collect(Collectors.toList()));
    }

    @Transactional
    public AbsenceDTO createNewAbsenceEntry(AbsenceDTO absenceDTO) {
        Absence absence = absenceMapper.dtoToModel(absenceDTO);
        EmployeeDTO employeeDto =
                employeeMapper.modelToDto(employeeRepository.findById(absence.getEmployee().getEmployeeId()).get());
        Employee employee = employeeMapper.dtoToModel(employeeDto);
        absence.setEmployee(employee);
        return absenceMapper.modelToDto(absenceRepository.save(absence));
    }

    @Transactional
    public AbsenceDTO updateAbsenceEntry(UUID absenceId, Absence absenceEntryToUpdate) {
        if (updatePreCondition(absenceId)) {
            absenceEntryToUpdate.setAbsenceId(absenceId);
            return absenceMapper.modelToDto(absenceRepository.save(absenceEntryToUpdate));
        }
        return null;
    }

    public void deleteAbsenceEntry(UUID absenceId) {
        if (absenceExistenceChecker(absenceId)) {
            absenceRepository.deleteById(absenceId);
        }
    }

}
