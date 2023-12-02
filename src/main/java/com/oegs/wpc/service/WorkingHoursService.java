package com.oegs.wpc.service;

import com.oegs.wpc.dto.WorkingHoursDTO;
import com.oegs.wpc.mapper.WorkingHoursMapper;
import com.oegs.wpc.model.WorkingHours;
import com.oegs.wpc.repository.WorkingHoursRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class WorkingHoursService {

    private WorkingHoursRepository workingHoursRepository;
    private WorkingHoursMapper workingHoursMapper;

    public List<WorkingHoursDTO> getAllWorkingHours() {
        return workingHoursMapper.modelsToDtos(workingHoursRepository.findAll());
    }

    public Optional<WorkingHoursDTO> getWorkingHoursById(UUID workingHoursId) {
        return Optional.ofNullable(workingHoursMapper.modelToDto(workingHoursRepository.findById(workingHoursId).orElseThrow()));
    }

    @Transactional
    public WorkingHoursDTO createNewWorkingHourEntry(WorkingHoursDTO workingHoursDTO) {
        WorkingHours workingHours = workingHoursMapper.dtoToModel(workingHoursDTO);
        return workingHoursMapper.modelToDto(workingHoursRepository.save(workingHours));
    }

    @Transactional
    public WorkingHoursDTO updateWorkingHourEntry(UUID workingHoursId, WorkingHours workingHoursToUpdate) {
        workingHoursToUpdate.setWorkingHoursId(workingHoursId);
        return workingHoursMapper.modelToDto(workingHoursRepository.save(workingHoursToUpdate));
    }

    public void deleteWorkingHourEntry(UUID workingHoursId) {
        workingHoursRepository.deleteById(workingHoursId);
    }

}
