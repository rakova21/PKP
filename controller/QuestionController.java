package com.crewrisk.controller;

import com.crewrisk.controller.main.Attributes;
import com.crewrisk.model.Question;
import com.crewrisk.model.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/question")
public class QuestionController extends Attributes {
    @GetMapping
    public String questions(Model model) {
        AddAttributes(model);
        return "question";
    }

    @PostMapping
    public String question(
            @RequestParam int answer1, @RequestParam int answer2, @RequestParam String answer3,
            @RequestParam String answer4, @RequestParam int answer5, @RequestParam int answer6,
            @RequestParam String answer7, @RequestParam int answer8, @RequestParam String answer9,
            @RequestParam String answer10, @RequestParam String answer11, @RequestParam String answer12
    ) {
        Users user = getUser();
        user.setQuestion(new Question(answer1, answer2, answer3, answer4, answer5, answer6, answer7, answer8, answer9, answer10, answer11, answer12));
        user.setQuestioned(false);
        usersRepo.save(user);
        return "redirect:/profile";
    }
}
