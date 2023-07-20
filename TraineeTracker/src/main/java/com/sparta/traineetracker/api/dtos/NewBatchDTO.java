package com.sparta.traineetracker.api.dtos;

import com.sparta.traineetracker.entities.User;

import java.util.List;

public class NewBatchDTO {

    private String courseName;
    private String batchName;

    private Integer numOfWeeks;
    private List<NewTraineeDTO> newTrainees;
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public Integer getNumOfWeeks() {
        return numOfWeeks;
    }

    public void setNumOfWeeks(Integer numOfWeeks) {
        this.numOfWeeks = numOfWeeks;
    }

    public List<NewTraineeDTO> getNewTrainees() {
        return newTrainees;
    }

    public void setNewTrainees(List<NewTraineeDTO> newTrainees) {
        this.newTrainees = newTrainees;
    }
}
