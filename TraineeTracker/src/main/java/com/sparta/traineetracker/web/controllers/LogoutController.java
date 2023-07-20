package com.sparta.traineetracker.web.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogoutController {
    @PostMapping("/web/logout")
    public String logout(HttpSession sessionStatus) {
        // Mark the session as complete to invalidate the user's session
        sessionStatus.invalidate();
        return "redirect:/login";
    }
}
