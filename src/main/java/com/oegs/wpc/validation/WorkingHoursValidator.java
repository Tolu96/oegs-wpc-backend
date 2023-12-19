package com.oegs.wpc.validation;

import com.oegs.wpc.model.WorkingHours;
import com.oegs.wpc.repository.ClientRepository;
import com.oegs.wpc.repository.EmployeeRepository;
import com.oegs.wpc.repository.WorkingHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Component
public class WorkingHoursValidator {
    public final String WORKING_HOURS_NOT_FOUND_MESSAGE =
            "Working Hours with this ID does not exist and therefore " + "could not be found";
    public final String WRONG_PERMISSION = "You do not have the right permissions to execute this action";
    public final String UPDATE_NOT_POSSIBLE = "Update was not possible. Please try it again later";

    public final String DUPLICATE_WORKING_HOURS_FOUND = "Duplicate Working Hours were found. Entry could not saved.";

    public final String END_TIME_IS_BEFORE_START_TIME = "End time of this entry is before the start time. Please " +
            "check your entry.";

    @Autowired
    private WorkingHoursRepository workingHoursRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ClientRepository clientRepository;

    public void creationPreCondition(WorkingHours workingHours) {
        checkIfWorkingHourExistsForSameDateTime(workingHours);

    }

    public void updatePreCondition(WorkingHours workingHours, UUID workingHoursId) {
        if (workingHourExistenceChecker(workingHoursId)) {
            checkIfWorkingHourExistsForSameDateTime(workingHours);
        }
    }

    private void checkIfWorkingHourExistsForSameDateTime(WorkingHours workingHours) {
        checkIfDateTimeIsValid(workingHours);

        List<WorkingHours> allWorkingHoursForEmployee =
                workingHoursRepository.findAll().stream().filter(emp -> emp.getEmployee().getEmployeeId() == workingHours.getEmployee().getEmployeeId()).toList();

        for (WorkingHours workHour : allWorkingHoursForEmployee) {
            if (workHour.getWorkHourStart().isBefore(workingHours.getWorkHourEnd()) && workingHours.getWorkHourStart().isBefore(workHour.getWorkHourEnd()) && workHour.getWorkHourStart().isBefore(workHour.getWorkHourEnd())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, DUPLICATE_WORKING_HOURS_FOUND);
            }
        }
    }


    public boolean workingHourExistenceChecker(UUID workingHoursId) {
        boolean exists = workingHoursRepository.existsById(workingHoursId);
        if (exists) {
            return true;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, WORKING_HOURS_NOT_FOUND_MESSAGE);
    }

    private void checkIfDateTimeIsValid(WorkingHours workingHours) {
        if (workingHours.getWorkHourStart().isAfter(workingHours.getWorkHourEnd())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, END_TIME_IS_BEFORE_START_TIME);
        }
    }


}
