package com.crewrisk.controller;

import com.crewrisk.controller.main.Attributes;
import com.crewrisk.model.*;
import com.crewrisk.model.enums.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/humans")
public class HumansController extends Attributes {
    @GetMapping
    public String humans(Model model) {
        AddAttributes(model);
        AddAttributesEnums(model);
        List<Users> users = usersRepo.findAllByRole(Role.EMPLOYEE);
        users.addAll(usersRepo.findAllByRole(Role.USER));
        model.addAttribute("humans", users);
        return "humans";
    }

    @GetMapping("/filter") //поиск
    public String filter(Model model, @RequestParam String fio) {
        AddAttributes(model);
        AddAttributesEnums(model);
        List<Users> users = usersRepo.findAllByRole(Role.EMPLOYEE);
        users.addAll(usersRepo.findAllByRole(Role.USER));
        users = users.stream().filter(user -> user.getPrimarys().getFio().toLowerCase().contains(fio.toLowerCase())).toList();
        model.addAttribute("humans", users);
        model.addAttribute("fio", fio);
        return "humans";
    }

    @GetMapping("/search") //фильтрация
    public String search(Model model, @RequestParam Marital marital, @RequestParam Origin origin, @RequestParam Citizenship citizenship) {
        AddAttributes(model);
        AddAttributesEnums(model);
        List<Users> users = usersRepo.findAllByRoleAndTertiary_MaritalAndTertiary_OriginAndTertiary_Citizenship(Role.EMPLOYEE, marital, origin, citizenship);
        users.addAll(usersRepo.findAllByRoleAndTertiary_MaritalAndTertiary_OriginAndTertiary_Citizenship(Role.USER, marital, origin, citizenship));
        model.addAttribute("humans", users);
        model.addAttribute("marSelect", marital);
        model.addAttribute("oriSelect", origin);
        model.addAttribute("citSelect", citizenship);
        return "humans";
    }

    @GetMapping("/{id}")
    public String Human(Model model, @PathVariable Long id) {
        AddAttributes(model);
        model.addAttribute("human", usersRepo.getReferenceById(id));
        return "human";
    }

    @GetMapping("/{id}/comments")
    public String HumanComments(@PathVariable Long id, @RequestParam String text) {
        humanCommentsRepo.save(new HumanComments(text, getDateNow(), getUser(), usersRepo.getReferenceById(id)));
        return "redirect:/humans/{id}";
    }

    @GetMapping("/question/{id}")
    public String HumanQuestion(Model model, @PathVariable Long id) {
        AddAttributes(model);
        model.addAttribute("human", usersRepo.getReferenceById(id));
        return "human_question";
    }

    @GetMapping("/certification/{id}")
    public String HumanCertification(Model model, @PathVariable Long id) {
        AddAttributes(model);
        model.addAttribute("human", usersRepo.getReferenceById(id));
        return "human_certification";
    }

    @GetMapping("/questioned/{id}")
    public String HumanQuestioned(@PathVariable Long id) {
        Users user = usersRepo.getReferenceById(id);
        user.setQuestioned(true);
        if (user.getQuestion() != null) {
            Question question = user.getQuestion();
            user.setQuestion(null);
            questionRepo.deleteById(question.getId());
        }
        usersRepo.save(user);
        notificationRepo.save(new Notification("Пройдите пожалуйста опрос", user));
        return "redirect:/humans/{id}";
    }

    @GetMapping("/certificationed/{id}")
    public String HumanCertificationed(@PathVariable Long id) {
        Users user = usersRepo.getReferenceById(id);
        user.setCertificationed(true);
        if (user.getCertification() != null) {
            Certification certification = user.getCertification();
            user.setCertification(null);
            certificationRepo.deleteById(certification.getId());
        }
        usersRepo.save(user);
        notificationRepo.save(new Notification("Пройдите пожалуйста аттестацию", user));
        return "redirect:/humans/{id}";
    }

    @GetMapping("/edit/{id}")
    public String HumanEdit(Model model, @PathVariable Long id) {
        AddAttributesHumanEdit(model, id);
        return "human_edit";
    }

    @GetMapping("/add")
    public String HumanAdd(Model model) {
        AddAttributes(model);
        return "human_add";
    }

    @PostMapping("/add")
    public String HumanAdd(Model model, @RequestParam String username, @RequestParam String password, @RequestParam String surname, @RequestParam String name, @RequestParam String patronymic) {
        if (usersRepo.findByUsername(username) != null) {
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            AddAttributes(model);
            return "human_add";
        }
        Users user = new Users(username, password, Role.EMPLOYEE);
        user.getPrimarys().setSurname(surname);
        user.getPrimarys().setName(name);
        user.getPrimarys().setPatronymic(patronymic);
        usersRepo.save(user);
        return "redirect:/humans/add";
    }

    @PostMapping("/edit/{id}")
    public String HumanEditOld(
            Model model, @PathVariable Long id, @RequestParam MultipartFile photo, @RequestParam String surname,
            @RequestParam String name, @RequestParam String patronymic, @RequestParam String passport,
            @RequestParam String passport_number, @RequestParam String date, @RequestParam String issued,
            @RequestParam String issued_date, @RequestParam String identity, @RequestParam String address,
            @RequestParam String tel_mob, @RequestParam String tel_home, @RequestParam String email,
            @RequestParam String job, @RequestParam String post, @RequestParam int income, @RequestParam int experience,
            @RequestParam Marital marital, @RequestParam Origin origin, @RequestParam Citizenship citizenship, @RequestParam Education education) {
        Users user = usersRepo.getReferenceById(id);
        try {
            if (photo != null && !Objects.requireNonNull(photo.getOriginalFilename()).isEmpty()) {
                String uuidFile = UUID.randomUUID().toString();
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) uploadDir.mkdir();
                String result = "humans/" + uuidFile + "_" + photo.getOriginalFilename();
                photo.transferTo(new File(uploadImg + "/" + result));
                user.getPrimarys().setPhoto(result);
            }
        } catch (IOException e) {
            AddAttributesHumanEdit(model, id);
            model.addAttribute("message", "Некорректные данные!");
            return "human_edit";
        }

        Primarys primarys = user.getPrimarys();
        primarys.set(surname, name, patronymic, passport, passport_number);
        Secondary secondary = user.getSecondary();
        secondary.set(date, issued, issued_date, identity, address, tel_mob, tel_home, email, job, post, income, experience);
        Tertiary tertiary = user.getTertiary();
        tertiary.set(marital, origin, citizenship, education);

        usersRepo.save(user);

        return "redirect:/humans/{id}";
    }
}
