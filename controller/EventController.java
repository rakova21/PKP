package com.crewrisk.controller;

import com.crewrisk.controller.main.Attributes;
import com.crewrisk.model.Event;
import com.crewrisk.model.enums.EventStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/events")
public class EventController extends Attributes {
    @GetMapping
    public String events(Model model) {
        AddAttributes(model);
        model.addAttribute("events", eventRepo.findAll());
        model.addAttribute("statuses", EventStatus.values());
        return "event";
    }

    @PostMapping("/add")
    public String add(@RequestParam String name, @RequestParam String date, @RequestParam EventStatus status) {
        eventRepo.save(new Event(name, date, status));
        return "redirect:/events";
    }

    @PostMapping("/{id}/edit")
    public String edit(@RequestParam String name, @RequestParam String date, @RequestParam EventStatus status, @PathVariable Long id) {
        Event event = eventRepo.getReferenceById(id);
        event.set(name, date, status);
        eventRepo.save(event);
        return "redirect:/events";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        eventRepo.deleteById(id);
        return "redirect:/events";
    }
}
