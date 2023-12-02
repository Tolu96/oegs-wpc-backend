package com.oegs.wpc.controller;

import com.oegs.wpc.dto.WorkingHoursDTO;
import com.oegs.wpc.model.WorkingHours;
import com.oegs.wpc.service.WorkingHoursService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/workingHours")
public class WorkingHoursController {

    private WorkingHoursService workingHoursService;

    @GetMapping
    private ResponseEntity<List<WorkingHoursDTO>> getAllWorkingHours() {
        return ResponseEntity.ok().body(workingHoursService.getAllWorkingHours());
    }

    @GetMapping("{workingHoursId}")
    private ResponseEntity<Optional<WorkingHoursDTO>> getWorkingHoursById(@PathVariable("workingHoursId") UUID workingHoursId) {
        return ResponseEntity.ok().body(workingHoursService.getWorkingHoursById(workingHoursId));
    }

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<WorkingHoursDTO> createNewWorkingHourEntry(@RequestBody WorkingHoursDTO newWorkingHoursDTO) {
        return new ResponseEntity<>(workingHoursService.createNewWorkingHourEntry(newWorkingHoursDTO),
                HttpStatus.CREATED);
    }

    @PutMapping(path = "{workingHoursId}")
    public ResponseEntity<WorkingHoursDTO> updateWorkingHourEntry(@PathVariable("workingHoursId") UUID workingHoursId
            , @RequestBody WorkingHours workingHours) {
        WorkingHoursDTO updatedWorkingHourEntry = workingHoursService.updateWorkingHourEntry(workingHoursId,
                workingHours);
        return new ResponseEntity<>(updatedWorkingHourEntry, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "{workingHoursId}")
    public ResponseEntity<String> deleteWorkingHourEntry(@PathVariable("workingHoursId") UUID workingHoursId) {
        workingHoursService.deleteWorkingHourEntry(workingHoursId);
        return new ResponseEntity<>("Working hour entry with the Id" + workingHoursId + " successfully deleted",
                HttpStatus.OK);
    }
}
