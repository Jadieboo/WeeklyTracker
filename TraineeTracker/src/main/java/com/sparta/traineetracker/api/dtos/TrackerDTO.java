package com.sparta.traineetracker.api.dtos;

public class TrackerDTO {

    private int week;
    private String start;
    private String stop;
    private String continueField;
    private String comment;
    private String technicalSkills;
    private String softSkills;

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public String getContinueField() {
        return continueField;
    }

    public void setContinueField(String continueField) {
        this.continueField = continueField;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTechnicalSkills() {
        return technicalSkills;
    }

    public void setTechnicalSkills(String technicalSkills) {
        this.technicalSkills = technicalSkills;
    }

    public String getSoftSkills() {
        return softSkills;
    }

    public void setSoftSkills(String softSkills) {
        this.softSkills = softSkills;
    }
}
