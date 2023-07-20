package com.sparta.traineetracker.web.controllers;

import com.sparta.traineetracker.entities.*;
import com.sparta.traineetracker.repositories.BatchRepository;
import com.sparta.traineetracker.repositories.TrackerRepository;
import com.sparta.traineetracker.repositories.UserBatchRepository;
import com.sparta.traineetracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
public class
TrainerController {

    @Autowired
    private TrackerRepository tRepo;

    @Autowired
    private UserRepository uRepo;

    @Autowired
    private UserBatchRepository ubRepo;

    @GetMapping("/web/trainerHomepage")
    public String getTrainerHomepage (Model model, @SessionAttribute("courses") List<Course> courses, @SessionAttribute("batches") List<Batch> batches, @SessionAttribute("user")Account acc){
        model.addAttribute("courses", courses);
        model.addAttribute("batches", batches);
        model.addAttribute("account", acc);
        return "trainerHomepage";
    }

    @GetMapping("/viewTrainee/{id}")
    public String viewTraineeTracker(@PathVariable int id, Model model){
        User trainee = uRepo.findById(id).get();
        Batch batch = ubRepo.findByUser(trainee).getBatch();
        List<Tracker> traineeTrackerList = tRepo.findAllByTrainee(trainee);
        model.addAttribute("batch", batch);
        model.addAttribute("trainee", trainee);
        model.addAttribute("traineeTracker", traineeTrackerList);
        return "traineeTrackerView";
    }
}
