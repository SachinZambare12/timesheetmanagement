package com.TimesheetManegment.Service;

import com.TimesheetManegment.DTO.TimesheetDTO;
import com.TimesheetManegment.Entity.Timesheet;
import com.TimesheetManegment.Entity.User;
import com.TimesheetManegment.Exception.BusinessException;
import com.TimesheetManegment.Mapper.TimesheetMapper;
import com.TimesheetManegment.Repository.TimesheetRepository;
import com.TimesheetManegment.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j

public class TimesheetServiceImpl implements TimesheetService {

    private final TimesheetRepository timesheetRepository;
    private final UserRepository userRepository;
    private final TimesheetMapper timesheetMapper;

    public TimesheetServiceImpl(TimesheetRepository timesheetRepository, UserRepository userRepository, @Lazy TimesheetMapper timesheetMapper) {
        this.timesheetRepository = timesheetRepository;
        this.userRepository = userRepository;


        this.timesheetMapper = timesheetMapper;
    }

    @Override
    public TimesheetDTO submitTimesheet(TimesheetDTO dto, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException("User not found"));
        Timesheet timesheet = timesheetMapper.toEntity(dto);
        timesheet.setUser(user);
        timesheet.setApproved(false);
        Timesheet saved = timesheetRepository.save(timesheet);
        return timesheetMapper.toDto(saved);
    }

    @Override
    public TimesheetDTO approveTimesheet(Long id) {
        Timesheet timesheet = timesheetRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Timesheet not found"));
        timesheet.setApproved(true);
        Timesheet updated = timesheetRepository.save(timesheet);
        return timesheetMapper.toDto(updated);
    }

    @Override
    public List<TimesheetDTO> getHistory(String username) {
        return timesheetRepository.findByUserUsername(username)
                .stream()
                .map(timesheetMapper::toDto)
                .collect(Collectors.toList());
    }
}

