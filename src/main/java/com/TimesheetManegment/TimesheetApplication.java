package com.TimesheetManegment;

import com.TimesheetManegment.DTO.TimesheetDTO;
import com.TimesheetManegment.Entity.Timesheet;
import com.TimesheetManegment.Mapper.TimesheetMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TimesheetApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimesheetApplication.class, args);
	}

}


