package com.oegs.wpc.controller;

import com.oegs.wpc.dto.AbsenceDTO;
import com.oegs.wpc.model.Absence;
import com.oegs.wpc.service.AbsenceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/absence")
public class AbsenceController {

    private AbsenceService absenceService;

    @GetMapping
    private ResponseEntity<List<AbsenceDTO>> getAllAbsenceEntries() {
        return ResponseEntity.ok().body(absenceService.getAllAbsenceEntries());
    }

    @GetMapping("{employeeId}")
    private ResponseEntity<List<AbsenceDTO>> getAllAbsenceEntriesForEmployee(@PathVariable("employeeId") UUID employeeId) {
        return ResponseEntity.ok().body(absenceService.getAllAbsenceEntriesForEmployee(employeeId));
    }

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<AbsenceDTO> createNewAbsenceEntry(@RequestBody AbsenceDTO absenceDTO) {
        return new ResponseEntity<>(absenceService.createNewAbsenceEntry(absenceDTO), HttpStatus.CREATED);
    }

    @PutMapping(path = "{absenceId}")
    public ResponseEntity<AbsenceDTO> updateAbsence(@PathVariable("absenceId") UUID absenceId,
                                                    @RequestBody Absence absenceEntry) {
        AbsenceDTO updateAbsenceEntry = absenceService.updateAbsenceEntry(absenceId, absenceEntry);
        return new ResponseEntity<>(updateAbsenceEntry, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "{absenceId}")
    public ResponseEntity<String> deleteAbsenceEntry(@PathVariable("absenceId") UUID absenceId) {
        absenceService.deleteAbsenceEntry(absenceId);
        return new ResponseEntity<>("Absence Entry with the Id" + absenceId + " successfully deleted", HttpStatus.OK);
    }

}
