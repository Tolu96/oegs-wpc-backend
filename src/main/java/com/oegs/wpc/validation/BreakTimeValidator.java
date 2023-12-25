package com.oegs.wpc.validation;

import com.oegs.wpc.model.BreakTime;
import com.oegs.wpc.repository.BreakTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Component
public class BreakTimeValidator {

    public final String BREAK_TIME_NOT_FOUND_MESSAGE =
            "Break time with this ID does not exist and therefore " + "could not be found";
    public final String END_TIME_BEFORE_START_TIME = "Please correct your Break time starttime or endtime";
    public final String DUPLICATE_DATE_SELECTED = "You already took a break for this date";

    public final String START_TIME_AND_END_TIME_IS_EQUAL = "Start Time and Endtime are equal. Therefore the working " +
            "hours are 0 seconds. Please try again.";


    @Autowired
    private BreakTimeRepository breakTimeRepository;

    public void creationPreCondition(BreakTime breakTime) {
        checkIfBreakTimeExistsForSameDateTime(breakTime);
    }

    public void updatePreCondition(BreakTime breakTime, UUID breakTimeId) {
        if (breakTimeExistenceChecker(breakTimeId)) {
            checkIfBreakTimeExistsForSameDateTime(breakTime);
        }
    }

    public boolean breakTimeExistenceChecker(UUID breakTimeId) {
        boolean exists = breakTimeRepository.existsById(breakTimeId);
        if (exists) {
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, BREAK_TIME_NOT_FOUND_MESSAGE);
        }
    }

    private void checkIfBreakTimeExistsForSameDateTime(BreakTime breakTime) {
        checkIfDateTimeIsValid(breakTime);
        checkIfStartTimeIsEqualToEndTime(breakTime);

        List<BreakTime> allBreakTimesForEmployee =
                breakTimeRepository.findAll().stream().filter(emp -> emp.getEmployee().getEmployeeId() == breakTime.getEmployee().getEmployeeId()).toList();

        for (BreakTime breakTimes : allBreakTimesForEmployee) {
            if (breakTimes.getBreakStart().isBefore(breakTime.getBreakEnd())
                    && breakTime.getBreakStart().isBefore(breakTimes.getBreakEnd())
                    && breakTimes.getBreakStart().isBefore(breakTimes.getBreakEnd())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, DUPLICATE_DATE_SELECTED);
            }
        }
    }

    private void checkIfDateTimeIsValid(BreakTime breakTime) {
        if (breakTime.getBreakStart().isAfter(breakTime.getBreakEnd())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, END_TIME_BEFORE_START_TIME);
        }
    }

    private void checkIfStartTimeIsEqualToEndTime(BreakTime breakTime) {
        if (breakTime.getBreakStart().equals(breakTime.getBreakEnd())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, START_TIME_AND_END_TIME_IS_EQUAL);
        }
    }


}
