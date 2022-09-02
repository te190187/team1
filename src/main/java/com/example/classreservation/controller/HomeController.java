package com.example.classreservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class HomeController {
    
    @GetMapping
    public String index(Model model, RedirectAttributes redirectAttributes) {

        var message = model.getAttribute("message");
        redirectAttributes.addAttribute("message", message);
        return "redirect:/studentEntries/create";
    }
}
