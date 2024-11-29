package com.crewrisk.controller;

import com.crewrisk.controller.main.Attributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notification")
public class NotificationController extends Attributes {
    @GetMapping
    public String notification(Model model) {
        AddAttributes(model);
        model.addAttribute("notifications", getUser().getNotifications());
        return "notification";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        notificationRepo.deleteById(id);
        return "redirect:/notification";
    }
}
