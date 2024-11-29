package com.crewrisk.controller;

import com.crewrisk.controller.main.Attributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController extends Attributes {

    @GetMapping
    public String index1() {
        return "redirect:/about";
    }

    @GetMapping("/index")
    public String index2() {
        return "redirect:/about";
    }




}
