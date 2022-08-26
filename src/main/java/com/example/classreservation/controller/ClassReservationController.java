package com.example.classreservation.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.classreservation.form.ClassReservationForm;
import com.example.classreservation.service.ClassReservationService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("classReservations")
public class ClassReservationController {
  @Autowired
  ClassReservationService classReservationService;

  @ModelAttribute
  ClassReservationForm setupForm() {
    return new ClassReservationForm();
  }

  @GetMapping
  String index() {
    return "classReservation/index";
  }

  @GetMapping(path = "create")
  String createGet(Model model) {
    return "classReservation/create";
  }

  @PostMapping(path = "create")
  String createPost(@Validated ClassReservationForm form, BindingResult result, Model model) {
    if(result.hasErrors()) {
      return createGet(model); 
    }

    // ここで予約表を作成
    classReservationService.create(form.getYearMonth());

    return "redirect:/";
  }
}