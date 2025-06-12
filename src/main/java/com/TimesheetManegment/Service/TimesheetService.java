package com.TimesheetManegment.Service;

import com.TimesheetManegment.DTO.TimesheetDTO;

import java.util.List;

public interface TimesheetService {
    TimesheetDTO submitTimesheet(TimesheetDTO dto, String username);
    TimesheetDTO approveTimesheet(Long id);
    List<TimesheetDTO> getHistory(String username);
}

