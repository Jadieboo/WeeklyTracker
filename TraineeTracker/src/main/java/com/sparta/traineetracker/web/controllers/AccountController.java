package com.sparta.traineetracker.web.controllers;


import com.sparta.traineetracker.entities.*;
import com.sparta.traineetracker.repositories.AccountRepository;
import com.sparta.traineetracker.repositories.BatchRepository;
import com.sparta.traineetracker.repositories.UserBatchRepository;
import com.sparta.traineetracker.repositories.UserRepository;
import com.sparta.traineetracker.web.dtos.AccountDTO;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/login")
@Controller
public class AccountController {

    Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private UserRepository uRepo;
    @Autowired
    private UserBatchRepository ubRepo;

    @Autowired
    private AccountRepository accRepo;

    @Autowired
    private BatchRepository bRepo;


    @GetMapping
    public String login(Model model) {
        model.addAttribute("loginDetails", new AccountDTO());
        return "loginPage";
    }

    @PostMapping

    public String processLogin(@ModelAttribute("loginDetails") AccountDTO accDet, Model model, HttpSession session) {
        Optional<Account> accOpt = accRepo.findByUsername(accDet.getUsername());

        if (accOpt.isPresent()) {
            Account acc = accOpt.get();
            if (acc.getPassword().equals(accDet.getPassword())) {

                if (acc.getRole().equals("trainee")) {
                    session.setAttribute("user", acc);
                    Optional<User> user = uRepo.findById(acc.getId());
                    Batch batch= ubRepo.findByUser(user.get()).getBatch();
                    session.setAttribute("batch", batch);
                    session.setAttribute("course", batch.getCourse().getCourseName());
                    return "loginSuccess";
                }
                else  {
                    session.setAttribute("user", acc);
                    Optional<User> user = uRepo.findById(acc.getId());

                    var ubatchList = ubRepo.findAllByUser(user.get());
                    List<Batch> batchList = new ArrayList<>();
                    for (UserBatch ub: ubatchList){
                        batchList.add(ub.getBatch());
                    }
                    session.setAttribute("batches", batchList);


                    List<Course> courseList = new ArrayList<>();
                    for (Batch batch: batchList){
                        courseList.add(batch.getCourse());
                    }
                    session.setAttribute("courses", courseList);


                    return "loginSuccessTrainer";
                }
            } else {
                return "badPassword";
            }

        } else {
            return "noUser";

        }
    }


}
