package com.oegs.wpc.validation;

import com.oegs.wpc.repository.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Component
public class AbsenceValidator {
    public final String ABSENCE_ENTRY_NOT_FOUND_MESSAGE = "Absence entry with this ID does not exist and therefore " +
            "could not be found";
    public final String WRONG_PERMISSION = "You do not have the right permissions to execute this action";
    public final String UPDATE_NOT_POSSIBLE = "Update was not possible. Please try it again later";
    
    @Autowired
    private AbsenceRepository absenceRepository;

    public boolean creationPreCondition() {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Test");
    }

    public boolean updatePreCondition(UUID absenceId) {
        if (absenceExistenceChecker(absenceId)) {
            return true;
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

}
