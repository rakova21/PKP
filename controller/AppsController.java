package com.crewrisk.controller;

import com.crewrisk.controller.main.Attributes;
import com.crewrisk.model.AppAnswer;
import com.crewrisk.model.Apps;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/apps")
public class AppsController extends Attributes {

    @GetMapping
    public String Apps(Model model) {
        AddAttributes(model);
        List<Apps> apps  =appsRepo.findAll();
        apps.sort(Comparator.comparing(Apps::getId));
        Collections.reverse(apps);
        model.addAttribute("apps", apps);
        return "apps";
    }

    @GetMapping("/{id}/delete")
    public String AppDelete(@PathVariable Long id) {
        appsRepo.deleteById(id);
        return "redirect:/apps";
    }

    @PostMapping("/{id}/answer")
    public String AppAnswer(@RequestParam String text, @PathVariable Long id) {
        appAnswerRepo.save(new AppAnswer(text, getDateNow(), getUser(), appsRepo.getReferenceById(id)));
        return "redirect:/apps";
    }


    @PostMapping("/add")
    public String AppsAdd(@RequestParam String text) {
        appsRepo.save(new Apps(text, getDateNow(), getUser()));
        return "redirect:/apps";
    }
}
