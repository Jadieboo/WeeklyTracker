package com.sparta.traineetracker.api.utils;

import com.sparta.traineetracker.api.dtos.TrackerDTO;
import com.sparta.traineetracker.api.dtos.TraineeDTO;
import com.sparta.traineetracker.entities.Tracker;
import com.sparta.traineetracker.entities.User;

public class DTOConverter {

    public static TraineeDTO toTraineeDTO(User user){

        TraineeDTO tDTO=new TraineeDTO();
        tDTO.setName(user.getFirstName()+" "+user.getLastName());
        return tDTO;

    }

    public static TrackerDTO toTrackerDTO(Tracker tracker){

        TrackerDTO tDTO=new TrackerDTO();

        tDTO.setWeek(tracker.getWeek());
        tDTO.setStart(tracker.getStart());
        tDTO.setStop(tracker.getStop());
        tDTO.setContinueField(tracker.getContinueField());
        tDTO.setComment(tracker.getComment());
        tDTO.setTechnicalSkills(tracker.getTechnicalSkills());
        tDTO.setSoftSkills(tracker.getSoftSkills());

        return tDTO;
    }
}
