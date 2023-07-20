package com.sparta.traineetracker.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tracker")
public class Tracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tracker_id", nullable = false)
    private Integer id;

    @Column(name = "week", nullable = false)
    private Integer week;

    @Column(name = "start", length = 1000)
    private String start;

    @Column(name = "stop", length = 1000)
    private String stop;

    @Column(name = "continue_field", length = 1000)
    private String continueField;

    @Column(name = "comment", length = 1000)
    private String comment;

    @Column(name = "technical_skills", length = 50)
    private String technicalSkills;

    @Column(name = "soft_skills", length = 50)
    private String softSkills;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trainee_id")
    private User trainee;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
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

    public User getTrainee() {
        return trainee;
    }

    public void setTrainee(User trainee) {
        this.trainee = trainee;
    }

    @Override
    public String toString() {
        return "Tracker{" +
                "id=" + id +
                ", week=" + week +
                ", start='" + start + '\'' +
                ", stop='" + stop + '\'' +
                ", continueField='" + continueField + '\'' +
                ", comment='" + comment + '\'' +
                ", technicalSkills='" + technicalSkills + '\'' +
                ", softSkills='" + softSkills + '\'' +
                ", trainee=" + trainee +
                '}';
    }
}