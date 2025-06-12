package com.TimesheetManegment.Controller;



import com.TimesheetManegment.DTO.TimesheetDTO;
import com.TimesheetManegment.Service.TimesheetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TimesheetControllerTest {

    @InjectMocks
    private TimesheetController controller;

    @Mock
    private TimesheetService timesheetService;

    private Principal principal;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        principal = () -> "john";
    }

    @Test
    void testSubmitTimesheet() {
        // Arrange
        TimesheetDTO dto = new TimesheetDTO();
        dto.setProjectName("Test Project");

        TimesheetDTO expected = new TimesheetDTO();
        expected.setProjectName("Test Project");

        when(timesheetService.submitTimesheet(dto, "john")).thenReturn(expected);

        // Act
        ResponseEntity<TimesheetDTO> response = controller.submitTimesheet(dto, principal);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Test Project", response.getBody().getProjectName());

        verify(timesheetService, times(1)).submitTimesheet(dto, "john");
    }

    @Test
    void testApproveTimesheet() {
        // Arrange
        Long timesheetId = 1L;
        TimesheetDTO expected = new TimesheetDTO();
        expected.setId(timesheetId);

        when(timesheetService.approveTimesheet(timesheetId)).thenReturn(expected);

        // Act
        ResponseEntity<TimesheetDTO> response = controller.approveTimesheet(timesheetId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(timesheetId, response.getBody().getId());

        verify(timesheetService, times(1)).approveTimesheet(timesheetId);
    }

    @Test
    void testGetHistory() {
        // Arrange
        TimesheetDTO dto1 = new TimesheetDTO();
        dto1.setProjectName("Project A");

        TimesheetDTO dto2 = new TimesheetDTO();
        dto2.setProjectName("Project B");

        List<TimesheetDTO> historyList = Arrays.asList(dto1, dto2);

        when(timesheetService.getHistory("john")).thenReturn(historyList);

        // Act
        ResponseEntity<List<TimesheetDTO>> response = controller.getHistory(principal);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("Project A", response.getBody().get(0).getProjectName());

        verify(timesheetService, times(1)).getHistory("john");
    }
}
