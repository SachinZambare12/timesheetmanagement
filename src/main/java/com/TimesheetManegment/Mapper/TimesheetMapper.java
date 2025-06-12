package com.TimesheetManegment.Mapper;


import com.TimesheetManegment.DTO.TimesheetDTO;

import com.TimesheetManegment.Entity.Timesheet;
import org.springframework.stereotype.Component;

@Component // This makes it a Spring bean
public interface TimesheetMapper {


    TimesheetDTO toDto(Timesheet timesheet);

    Timesheet toEntity(TimesheetDTO dto);
}
