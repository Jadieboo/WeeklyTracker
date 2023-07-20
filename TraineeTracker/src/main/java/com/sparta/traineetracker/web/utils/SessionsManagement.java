package com.sparta.traineetracker.web.utils;


import com.sparta.traineetracker.entities.Account;
import com.sparta.traineetracker.entities.Batch;
import com.sparta.traineetracker.entities.User;
import com.sparta.traineetracker.entities.UserBatch;
import com.sparta.traineetracker.repositories.BatchRepository;
import com.sparta.traineetracker.repositories.UserBatchRepository;
import com.sparta.traineetracker.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Optional;

@Controller
public class SessionsManagement {

    @Autowired
    private UserRepository uRepo;
    @Autowired
    private UserBatchRepository ubRepo;



    public void generateSession(Account acc, HttpSession session){
        session.setAttribute("user", acc);
        Optional<User> user = uRepo.findById(acc.getId());
        Batch batch= ubRepo.findByUser(user.get()).getBatch();
        session.setAttribute("batch", batch);
        session.setAttribute("course", batch.getCourse().getCourseName());

    }


}
