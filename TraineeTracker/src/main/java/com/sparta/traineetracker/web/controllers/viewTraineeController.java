package com.sparta.traineetracker.web.controllers;

import com.sparta.traineetracker.entities.Account;
import com.sparta.traineetracker.entities.User;
import com.sparta.traineetracker.entities.UserBatch;
import com.sparta.traineetracker.repositories.AccountRepository;
import com.sparta.traineetracker.repositories.BatchRepository;
import com.sparta.traineetracker.repositories.UserBatchRepository;
import com.sparta.traineetracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class viewTraineeController {
    @Autowired
    AccountRepository accRepo;
    @Autowired
    BatchRepository batchRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    UserBatchRepository ubRepo;

    @GetMapping("viewTrainees/batch/{id}")
    public String getAllTrainees(@PathVariable int id, Model model) {
        List<UserBatch> traineeList = ubRepo.findAllByBatch(batchRepo.findById(id).get());

        List<User> userList = new ArrayList<>();

        for (UserBatch userBatch : traineeList) {
            User user = userRepo.findById(userBatch.getUser().getId()).get();
            Account account = accRepo.findByUser(user);

            if (account.getRole().equals("trainee")) {
                userList.add(userBatch.getUser());
            }
        }

        model.addAttribute("traineeList", userList);

        return "viewTrainees";
    }

}
