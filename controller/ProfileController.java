package com.crewrisk.controller;

import com.crewrisk.controller.main.Attributes;
import com.crewrisk.model.*;
import com.crewrisk.model.enums.Citizenship;
import com.crewrisk.model.enums.Education;
import com.crewrisk.model.enums.Marital;
import com.crewrisk.model.enums.Origin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/profile")
public class ProfileController extends Attributes {

    @GetMapping
    public String Profile(Model model) {
        AddAttributesProfile(model);
        return "profile";
    }

    @PostMapping("/offer")
    public String ProfileOffer(@RequestParam String offer) {
        offersRepo.save(new Offers(getUser(), offer));
        return "redirect:/profile";
    }

    @GetMapping("/edit")
    public String ProfileEdit(Model model) {
        AddAttributesProfile(model);
        return "profile_edit";
    }

    @GetMapping("/score")
    public String ProfileScore(Model model) {
        AddAttributes(model);
        model.addAttribute("human", usersRepo.getReferenceById(getUser().getId()));
        return "profile_score";
    }

    @PostMapping("/resume")
    public String resume(Model model, @RequestParam MultipartFile resume) {
        try {
            if (resume != null && !Objects.requireNonNull(resume.getOriginalFilename()).isEmpty()) {
                String uuidFile = UUID.randomUUID().toString();
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) uploadDir.mkdir();
                String result = "humans/" + uuidFile + "_" + resume.getOriginalFilename();
                resume.transferTo(new File(uploadImg + "/" + result));
                Users user = getUser();
                user.setResume(result);
                usersRepo.save(user);
            }
        } catch (IOException e) {
            model.addAttribute("message", "Некорректные данные!");
            AddAttributesProfile(model);
            return "profile";
        }
        return "redirect:/profile";
    }

    @PostMapping("/score")
    public String ProfileScore(@RequestParam int com, @RequestParam int team, @RequestParam int stress, @RequestParam int ind, @RequestParam int dis) {
        Score score = usersRepo.getReferenceById(getUser().getId()).getScore();
        score.setCom(com);
        score.setTeam(team);
        score.setStress(stress);
        score.setInd(ind);
        score.setDis(dis);
        score.setSummary(com + team + stress + ind + dis);
        scoreRepo.save(score);
        return "redirect:/profile";
    }

    @PostMapping("/edit/")
    public String ProfileEdit(
            Model model, @RequestParam MultipartFile photo, @RequestParam String surname,
            @RequestParam String name, @RequestParam String patronymic, @RequestParam String passport,
            @RequestParam String passport_number, @RequestParam String date, @RequestParam String issued,
            @RequestParam String issued_date, @RequestParam String identity, @RequestParam String address,
            @RequestParam String tel_mob, @RequestParam String tel_home, @RequestParam String email,
            @RequestParam String job, @RequestParam String post, @RequestParam int income, @RequestParam int experience,
            @RequestParam Marital marital, @RequestParam Origin origin, @RequestParam Citizenship citizenship, @RequestParam Education education) {
        Users user = getUser();
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
            AddAttributesProfile(model);
            model.addAttribute("message", "Некорректные данные!");
            return "profile_edit";
        }

        Primarys primarys = user.getPrimarys();
        primarys.set(surname, name, patronymic, passport, passport_number);
        Secondary secondary = user.getSecondary();
        secondary.set(date, issued, issued_date, identity, address, tel_mob, tel_home, email, job, post, income, experience);
        Tertiary tertiary = user.getTertiary();
        tertiary.set(marital, origin, citizenship, education);

        usersRepo.save(user);

        return "redirect:/profile";
    }
}
