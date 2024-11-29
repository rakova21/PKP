package com.crewrisk.controller.main;

import com.crewrisk.model.enums.*;
import org.springframework.ui.Model;

public class Attributes extends Main {

    protected void AddAttributes(Model model) {
        model.addAttribute("role", getRole());
        model.addAttribute("user", getUser());
        if (getUser() != null) {
            if (getUser().getQuestion() == null && getUser().isQuestioned()) {
                model.addAttribute("question", true);
            }
            model.addAttribute("certification", getUser().isCertificationed());
        }
    }

    protected void AddAttributesEnums(Model model) {
        model.addAttribute("yesnos", YesNo.values());
        model.addAttribute("citizenships", Citizenship.values());
        model.addAttribute("maritals", Marital.values());
        model.addAttribute("origins", Origin.values());
        model.addAttribute("disabilities", Disability.values());
        model.addAttribute("educations", Education.values());
    }

    protected void AddAttributesUsers(Model model) {
        AddAttributes(model);
        model.addAttribute("users", usersRepo.findAllByOrderByRole());
        model.addAttribute("roles", Role.values());
    }

    protected void AddAttributesProfile(Model model) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("human", usersRepo.getReferenceById(getUser().getId()));
    }

    protected void AddAttributesHumanEdit(Model model, Long id) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("human", usersRepo.getReferenceById(id));
    }

}
