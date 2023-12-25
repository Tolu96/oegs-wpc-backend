package com.oegs.wpc.controller;


import com.oegs.wpc.dto.BreakTimeDTO;
import com.oegs.wpc.model.BreakTime;
import com.oegs.wpc.service.BreakTimeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/breakTime")
public class BreakTimeController {

    private BreakTimeService breakTimeService;

    @GetMapping
    private ResponseEntity<List<BreakTimeDTO>> getAllBreakTimes() {
        return ResponseEntity.ok().body(breakTimeService.getAllBreakTimes());
    }

    @GetMapping("{employeeId}")
    private ResponseEntity<List<BreakTimeDTO>> getAllBreakTimesForEmployee(@PathVariable("employeeId") UUID employeeId) {
        return ResponseEntity.ok().body(breakTimeService.getAllBreakTimesForEmployee(employeeId));
    }

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<BreakTimeDTO> createNewWorkingHourEntry(@RequestBody BreakTimeDTO newBreakTimeDTO) {
        return new ResponseEntity<>(breakTimeService.createNewBreakTime(newBreakTimeDTO),
                HttpStatus.CREATED);
    }

    @PutMapping(path = "{breakTimeId}")
    public ResponseEntity<BreakTimeDTO> updateBreakTime(@PathVariable("breakTimeId") UUID breakTimeId
            , @RequestBody BreakTime breakTime) {
        BreakTimeDTO updateBreakTime = breakTimeService.updateBreakTime(breakTimeId,
                breakTime);
        return new ResponseEntity<>(updateBreakTime, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "{breakTimeId}")
    public ResponseEntity<String> deleteWorkingHourEntry(@PathVariable("breakTimeId") UUID breakTimeId) {
        breakTimeService.deleteBreakTime(breakTimeId);
        return new ResponseEntity<>("Break time with the Id" + breakTimeId + " successfully deleted",
                HttpStatus.OK);
    }
}
