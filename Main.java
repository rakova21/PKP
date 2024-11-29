package com.crewrisk.controller.main;

import com.crewrisk.model.Users;
import com.crewrisk.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;

public class Main {

    @Autowired
    protected UsersRepository usersRepo;
    @Autowired
    protected ScoreRepository scoreRepo;
    @Autowired
    protected PrimarysRepository primarysRepo;
    @Autowired
    protected SecondaryRepository secondaryRepo;
    @Autowired
    protected TertiaryRepository tertiaryRepo;
    @Autowired
    protected ReviewsRepository reviewsRepo;
    @Autowired
    protected VacancyRepository vacancyRepo;
    @Autowired
    protected AppsRepository appsRepo;
    @Autowired
    protected JobsRepository jobsRepo;
    @Autowired
    protected OffersRepository offersRepo;
    @Autowired
    protected HumanCommentsRepository humanCommentsRepo;
    @Autowired
    protected AppAnswerRepository appAnswerRepo;
    @Autowired
    protected CertificationRepository certificationRepo;
    @Autowired
    protected EventRepository eventRepo;
    @Autowired
    protected NotificationRepository notificationRepo;
    @Autowired
    protected QuestionRepository questionRepo;

    @Value("${upload.img}")
    protected String uploadImg;

    protected Users getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            return usersRepo.findByUsername(userDetail.getUsername());
        }
        return null;
    }

    protected String getRole() {
        Users users = getUser();
        if (users == null) return "NOT";
        return users.getRole().toString();
    }

    public static String getDateNow() {
        return LocalDateTime.now().toString().substring(0, 10);
    }
}