package com.oegs.wpc.validation;

import com.oegs.wpc.enums.AbsenceStatusEnum;
import com.oegs.wpc.model.Absence;
import com.oegs.wpc.model.Employee;
import com.oegs.wpc.repository.AbsenceRepository;
import com.oegs.wpc.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
public class AbsenceValidator {
    public final String ABSENCE_ENTRY_NOT_FOUND_MESSAGE =
            "Absence entry with this ID does not exist and therefore " + "could not be found";
    public final String WRONG_PERMISSION = "You do not have the right permissions to execute this action";
    public final String UPDATE_NOT_POSSIBLE = "Update was not possible. Please try it again later";
    public final String VACATION_TOO_LONG = "You can only take up to 3 Weeks of vacation at once";

    public final String INVALID_ABSENCE_ENTRY = "Please correct your Absence Entry starttime or endtime";

    public final String HAVE_NOT_ENOUGH_REMAINING_VACATION =
            "You can't take Vacation because you don't have enough " + "Vacation left";

    public final String DUPLICATE_DATE_SELECTED = "You already took an absence entry for this date";

    public final short MAX_DURATION = 21;


    @Autowired
    private AbsenceRepository absenceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public void creationPreCondition(Absence absence) {
        validDateChecker(absence);
        processRemainingVacation(absence);
    }

    public void updatePreCondition(Absence absence, UUID absenceId) {
        if (absenceExistenceChecker(absenceId)) {
            validDateChecker(absence);
            return;
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT, UPDATE_NOT_POSSIBLE);
    }

    private void permissionChecker() {
        System.out.println(WRONG_PERMISSION);
    }


    public boolean absenceExistenceChecker(UUID absenceId) {
        boolean exists = absenceRepository.existsById(absenceId);
        if (exists) {
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ABSENCE_ENTRY_NOT_FOUND_MESSAGE);
        }
    }

    private int dateFilterForDayOfWeek(LocalDate startTime, LocalDate endTime) {
        List<LocalDate> test =
                startTime.datesUntil(endTime.plusDays(1)).filter(date -> date.getDayOfWeek() != DayOfWeek.SUNDAY).toList();

        return test.size();
    }

    private void validDateChecker(Absence absence) {
        int filteredDate = dateFilterForDayOfWeek(absence.getAbsenceStart(), absence.getAbsenceEnd());
        if (filteredDate > MAX_DURATION && absence.getAbsenceStatus().equals(AbsenceStatusEnum.VACATION)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, VACATION_TOO_LONG);
        }
        if (filteredDate <= 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, INVALID_ABSENCE_ENTRY);
        }
    }

    private void processRemainingVacation(Absence absence) {
        int vacationTaken = dateFilterForDayOfWeek(absence.getAbsenceStart(), absence.getAbsenceEnd());
        int currentVacation = absence.getEmployee().getRemainingVacation();
        int result = currentVacation - vacationTaken;

        checkIfVacationExistsForSameDate(absence);

        if (result >= 0) {
            Employee employeeToBeProcessed = absence.getEmployee();
            employeeToBeProcessed.setRemainingVacation(result);
            employeeRepository.saveAndFlush(employeeToBeProcessed);
            return;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, HAVE_NOT_ENOUGH_REMAINING_VACATION);
    }

    private void checkIfVacationExistsForSameDate(Absence absence) {
        List<Absence> allAbsenceEntriesForEmployee =
                absenceRepository
                        .findAll()
                        .stream()
                        .filter(emp -> emp.getEmployee().getEmployeeId() == absence.getEmployee().getEmployeeId())
                        .toList();

        for (Absence abs : allAbsenceEntriesForEmployee) {
            List<LocalDate> test = new java.util.ArrayList<>(abs
                    .getAbsenceStart()
                    .datesUntil(abs.getAbsenceEnd().plusDays(1))
                    .toList());

            List<LocalDate> test2 = absence
                    .getAbsenceStart()
                    .datesUntil(absence.getAbsenceEnd().plusDays(1))
                    .toList();

            test.retainAll(test2);

            if (!test.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, DUPLICATE_DATE_SELECTED);
            }
            return;
        }

    }
}
