package com.oegs.wpc.validation;

import com.oegs.wpc.repository.WorkingHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkingHoursValidator {
    public final String ABSENCE_ENTRY_NOT_FOUND_MESSAGE = "Absence entry with this ID does not exist and therefore " +
            "could not be found";
    public final String WRONG_PERMISSION = "You do not have the right permissions to execute this action";
    public final String UPDATE_NOT_POSSIBLE = "Update was not possible. Please try it again later";

    @Autowired
    private WorkingHoursRepository workingHoursRepository;


}
