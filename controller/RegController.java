package com.crewrisk.controller;

import com.crewrisk.controller.main.Attributes;
import com.crewrisk.model.Users;
import com.crewrisk.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reg")
public class RegController extends Attributes {

    @GetMapping
    public String reg(Model model) {
        AddAttributes(model);
        return "reg";
    }

    @PostMapping
    public String regUser(Model model, @RequestParam String username, @RequestParam String password, @RequestParam String surname, @RequestParam String name, @RequestParam String patronymic) {
        Users user;

        if (usersRepo.findAll().isEmpty()) {
            user = new Users(username, password, Role.ADMIN);
        } else {
            if (usersRepo.findByUsername(username) != null) {
                model.addAttribute("message", "Пользователь с таким логином уже существует");
                AddAttributes(model);
                return "reg";
            }
            user = new Users(username, password, Role.USER);
        }

        user.getPrimarys().setSurname(surname);
        user.getPrimarys().setName(name);
        user.getPrimarys().setPatronymic(patronymic);

        usersRepo.save(user);

        return "redirect:/login";
    }
}
