package com.TimesheetManegment.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TimesheetDTO {
    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;
    private LocalDate date;
    private String description;
    private boolean approved;
    private String projectName;
    public String getProjectName(){
        return projectName;
    }


   public void setProjectName(String projectName){
        this.projectName = projectName ;
   }
}

