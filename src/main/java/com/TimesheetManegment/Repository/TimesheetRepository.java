package com.TimesheetManegment.Repository;

import com.TimesheetManegment.Entity.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
    List<Timesheet> findByUserUsername(String username);
}