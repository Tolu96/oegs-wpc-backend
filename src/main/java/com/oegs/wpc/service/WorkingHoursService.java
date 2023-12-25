package com.oegs.wpc.service;

import com.oegs.wpc.dto.ClientDTO;
import com.oegs.wpc.dto.EmployeeDTO;
import com.oegs.wpc.dto.WorkingHoursDTO;
import com.oegs.wpc.mapper.ClientMapper;
import com.oegs.wpc.mapper.EmployeeMapper;
import com.oegs.wpc.mapper.WorkingHoursMapper;
import com.oegs.wpc.model.Client;
import com.oegs.wpc.model.Employee;
import com.oegs.wpc.model.WorkingHours;
import com.oegs.wpc.repository.ClientRepository;
import com.oegs.wpc.repository.EmployeeRepository;
import com.oegs.wpc.repository.WorkingHoursRepository;
import com.oegs.wpc.validation.ClientValidator;
import com.oegs.wpc.validation.EmployeeValidator;
import com.oegs.wpc.validation.WorkingHoursValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WorkingHoursService extends WorkingHoursValidator {

    private WorkingHoursRepository workingHoursRepository;
    private EmployeeRepository employeeRepository;
    private ClientRepository clientRepository;

    private EmployeeValidator employeeValidator;
    private ClientValidator clientValidator;

    private WorkingHoursMapper workingHoursMapper;
    private EmployeeMapper employeeMapper;
    private ClientMapper clientMapper;

    public List<WorkingHoursDTO> getAllWorkingHours() {
        return workingHoursMapper.modelsToDtos(workingHoursRepository.findAll());
    }

    public List<WorkingHoursDTO> getWorkingHoursByEmployeeId(UUID employeeId) {
        return workingHoursMapper.modelsToDtos(workingHoursRepository
                .findAll()
                .stream()
                .filter(t -> t.getEmployee().getEmployeeId().equals(employeeId))
                .collect(Collectors.toList()));
    }

    @Transactional
    public WorkingHoursDTO createNewWorkingHourEntry(WorkingHoursDTO workingHoursDTO) {
        WorkingHours workingHours = workingHoursMapper.dtoToModel(workingHoursDTO);

        employeeValidator.employeeExistenceChecker(workingHours.getEmployee().getEmployeeId());
        EmployeeDTO employeeDTO =
                employeeMapper.modelToDto(employeeRepository.findById(workingHours.getEmployee().getEmployeeId()).get());
        Employee employee = employeeMapper.dtoToModel(employeeDTO);
        workingHours.setEmployee(employee);


        clientValidator.clientExistenceChecker(workingHours.getClient().getClientId());
        ClientDTO clientDTO =
                clientMapper.modelToDto(clientRepository.findById(workingHours.getClient().getClientId()).get());
        Client client = clientMapper.dtoToModel(clientDTO);
        workingHours.setClient(client);

        creationPreCondition(workingHours);
        return workingHoursMapper.modelToDto(workingHoursRepository.save(workingHours));
    }

    @Transactional
    public WorkingHoursDTO updateWorkingHourEntry(UUID workingHoursId, WorkingHours workingHoursToUpdate) {
        updatePreCondition(workingHoursToUpdate, workingHoursId);
        workingHoursToUpdate.setWorkingHoursId(workingHoursId);
        workingHoursToUpdate.setEmployee(workingHoursRepository.findById(workingHoursId).get().getEmployee());
        workingHoursToUpdate.setClient(workingHoursRepository.findById(workingHoursId).get().getClient());
        return workingHoursMapper.modelToDto(workingHoursRepository.save(workingHoursToUpdate));
    }

    public void deleteWorkingHourEntry(UUID workingHoursId) {
        if (workingHourExistenceChecker(workingHoursId)) {
            workingHoursRepository.deleteById(workingHoursId);
        }
    }

}
