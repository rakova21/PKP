package com.crewrisk.controller;

import com.crewrisk.controller.main.Attributes;
import com.crewrisk.model.Score;
import com.crewrisk.model.Users;
import com.crewrisk.model.enums.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/statistics")
public class StatsController extends Attributes {
    @GetMapping
    public String Stats(Model model) {
        AddAttributes(model);

        List<Users> users = usersRepo.findAllByRole(Role.EMPLOYEE);
        users.addAll(usersRepo.findAllByRole(Role.ADMIN));
        users.addAll(usersRepo.findAllByRole(Role.MANAGER));

        HashMap<String, Integer> maritals = new HashMap<>();
        HashMap<String, Integer> origins = new HashMap<>();
        HashMap<String, Integer> citizenships = new HashMap<>();

        for (Marital i : Marital.values()) {
            maritals.put(i.getName(), users.stream().reduce(0, (integer, user) -> {
                if (user.getTertiary().getMarital() == i) return integer + 1;
                return integer;
            }, Integer::sum));
        }
        for (Origin i : Origin.values()) {
            origins.put(i.getName(), users.stream().reduce(0, (integer, user) -> {
                if (user.getTertiary().getOrigin() == i) return integer + 1;
                return integer;
            }, Integer::sum));
        }
        for (Citizenship i : Citizenship.values()) {
            citizenships.put(i.getName(), users.stream().reduce(0, (integer, user) -> {
                if (user.getTertiary().getCitizenship() == i) return integer + 1;
                return integer;
            }, Integer::sum));
        }

        model.addAttribute("maritals", maritals);
        model.addAttribute("origins", origins);
        model.addAttribute("citizenships", citizenships);

        List<Score> scores = scoreRepo.findAll();

        scores.sort(Comparator.comparing(Score::getSummary));
        Collections.reverse(scores);

        String[] topQuantityName = new String[5];
        int[] topQuantityNumber = new int[5];
        for (int i = 0; i < scores.size(); i++) {
            if (i == 5) break;
            topQuantityName[i] = scores.get(i).getOwner().getPrimarys().getFio();
            topQuantityNumber[i] = scores.get(i).getSummary();
        }
        model.addAttribute("topQuantityName", topQuantityName);
        model.addAttribute("topQuantityNumber", topQuantityNumber);

        model.addAttribute("mid", users.stream().reduce(0, (integer, user) -> {
            if (user.getTertiary().getEducation() == Education.MID) return integer + 1;
            return integer;
        }, Integer::sum));
        model.addAttribute("high", users.stream().reduce(0, (integer, user) -> {
            if (user.getTertiary().getEducation() == Education.HIGH) return integer + 1;
            return integer;
        }, Integer::sum));

        users.sort(Comparator.comparing(Users::getExperience));
        Collections.reverse(users);

        model.addAttribute("userSize", users.size());

        String[] expString = new String[5];
        int[] expInt = new int[5];

        for (int i = 0; i < users.size(); i++) {
            if (i == 5) break;
            expString[i] = users.get(i).getPrimarys().getFio();
            expInt[i] = users.get(i).getSecondary().getExperience();
        }

        model.addAttribute("expString", expString);
        model.addAttribute("expInt", expInt);

        if (users.isEmpty()) {
            model.addAttribute("medExp", 0);
        } else {
            int res = users.stream().reduce(0, (i, user) -> i + user.getSecondary().getExperience(), Integer::sum);
            int medExp = res / users.size();
            int medExpMin = users.stream().reduce(0, (i, user) -> {
                if (user.getSecondary().getExperience() < medExp) return i + 1;
                return i;
            }, Integer::sum);
            int medExpMax = users.stream().reduce(0, (i, user) -> {
                if (user.getSecondary().getExperience() > medExp) return i + 1;
                return i;
            }, Integer::sum);
            model.addAttribute("medExp", medExp);
            model.addAttribute("medExpMin", medExpMin);
            model.addAttribute("medExpMax", medExpMax);
        }

        int minCertification = 0;
        int medCertification = 0;
        int maxCertification = 0;

        for (Users i : users) {
            if (i.getCertification() != null) {
                if (i.getCertification().getScore() < 5) minCertification += 1;
                if (i.getCertification().getScore() >= 5 && i.getCertification().getScore() <= 7) medCertification += 1;
                if (i.getCertification().getScore() > 7) maxCertification += 1;
            }
        }

        model.addAttribute("minCertification", minCertification);
        model.addAttribute("medCertification", medCertification);
        model.addAttribute("maxCertification", maxCertification);


        return "stats";
    }
}
