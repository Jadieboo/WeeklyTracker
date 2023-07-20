package com.sparta.traineetracker.web.controllers;


import com.sparta.traineetracker.entities.*;
import com.sparta.traineetracker.repositories.TrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TrackerController {

    @Autowired
    private TrackerRepository trackRepo;

    @GetMapping("/web/traineeHomepage")
    public String getTraineeHomepage(Model model, @SessionAttribute("user") Account trainee ){
        List<Tracker> trackerInfo = trackRepo.findAllByTrainee(trainee.getUser());
        model.addAttribute("trackerInfo", trackerInfo);
        return "traineeHomepage";
    }

    @GetMapping("/web/week/{id}")
    public String getWeeklyTracker(@PathVariable int id,  Model model, @SessionAttribute("user") Account trainee){
        Tracker trackerWeek = trackRepo.findByTraineeAndWeek(trainee.getUser(), id);
/*
        model.addAttribute("trackerInfo", trackerInfo);
*/
        model.addAttribute("trackerWeek", trackerWeek);
        return "traineeTracker";
    }

    @PostMapping("/web/edit")
    public String editWeeklyTracker(@ModelAttribute("trackerWeek") Tracker trackerWeek){
        Tracker updateTracker = trackRepo.findById(trackerWeek.getId()).get();
        updateTracker.setStart(trackerWeek.getStart());
        updateTracker.setContinueField(trackerWeek.getContinueField());
        updateTracker.setStop(trackerWeek.getStop());
        updateTracker.setTechnicalSkills(trackerWeek.getTechnicalSkills());
        updateTracker.setSoftSkills(trackerWeek.getSoftSkills());
        updateTracker.setComment(trackerWeek.getComment());


        trackRepo.saveAndFlush(updateTracker);
        return "updateSuccess";
    }

}
