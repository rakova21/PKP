package com.crewrisk.controller;

import com.crewrisk.controller.main.Attributes;
import com.crewrisk.model.Reviews;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reviews")
public class ReviewsController extends Attributes {

    @GetMapping
    public String Reviews(Model model) {
        AddAttributes(model);
        model.addAttribute("reviews", reviewsRepo.findAll());
        return "reviews";
    }

    @GetMapping("/{id}/delete")
    public String ReviewDelete(@PathVariable Long id) {
        reviewsRepo.deleteById(id);
        return "redirect:/reviews";
    }

    @PostMapping("/add")
    public String ReviewsAdd(@RequestParam String review) {
        reviewsRepo.save(new Reviews(review, getDateNow(), getUser()));
        return "redirect:/reviews";
    }
}
