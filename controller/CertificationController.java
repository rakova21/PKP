package com.crewrisk.controller;

import com.crewrisk.controller.main.Attributes;
import com.crewrisk.model.Certification;
import com.crewrisk.model.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/certification")
public class CertificationController extends Attributes {
    @GetMapping
    public String certification(Model model) {
        AddAttributes(model);
        return "certification";
    }

    @PostMapping
    public String certification(
            @RequestParam String answer1, @RequestParam String answer2, @RequestParam String answer3,
            @RequestParam String answer4, @RequestParam String answer5, @RequestParam String answer6,
            @RequestParam String answer7, @RequestParam String answer8, @RequestParam String answer9,
            @RequestParam String answer10, @RequestParam boolean answer11, @RequestParam boolean answer12
    ) {
        Users user = getUser();
        user.setCertification(new Certification(answer1, answer2, answer3, answer4, answer5, answer6, answer7, answer8, answer9, answer10, answer11, answer12));
        user.setCertificationed(false);
        usersRepo.save(user);
        return "redirect:/profile";
    }
}
