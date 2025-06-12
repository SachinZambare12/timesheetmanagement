package com.TimesheetManegment.Controller;

import com.TimesheetManegment.DTO.TimesheetDTO;
import com.TimesheetManegment.Service.TimesheetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/timesheets")
@RequiredArgsConstructor
@Slf4j
public class TimesheetController {

    private final TimesheetService timesheetService;

    public TimesheetController(TimesheetService timesheetService) {
        this.timesheetService = timesheetService;
    }

    @PostMapping("/submit")
    public ResponseEntity<TimesheetDTO> submitTimesheet(@Valid @RequestBody TimesheetDTO dto, Principal principal) {
        TimesheetDTO result = timesheetService.submitTimesheet(dto, principal.getName());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/approve/{id}")
    public ResponseEntity<TimesheetDTO> approveTimesheet(@PathVariable Long id) {
        TimesheetDTO result = timesheetService.approveTimesheet(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/history")
    public ResponseEntity<List<TimesheetDTO>> getHistory(Principal principal) {
        List<TimesheetDTO> history = timesheetService.getHistory(principal.getName());
        return ResponseEntity.ok(history);
    }
}

