package com.example.classreservation.controller;

import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Locale;

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
import com.example.classreservation.service.ClassroomService;

@Controller
@RequestMapping("classReservations")
public class ClassReservationController {
  @Autowired
  ClassReservationService classReservationService;

  @Autowired
  ClassroomService classroomService;

  @ModelAttribute
  ClassReservationForm setupForm() {
    return new ClassReservationForm();
  }

  @GetMapping
  String index(ClassReservationForm form, Model model) {
      // ここで予約表を作成
      var reservation = classReservationService.create("2022-07");

      var classrooms = classroomService.findAll();
  
      model.addAttribute("reservation", reservation);
      model.addAttribute("classrooms", classrooms);
      model.addAttribute("formatter", DateTimeFormatter.ofPattern("dd日(E)", Locale.JAPANESE));
      model.addAttribute("yearMonthFormatter", DateTimeFormatter.ofPattern("yyyy年MM月", Locale.JAPANESE));
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
    var reservation = classReservationService.create(form.getYearMonth());

    var classrooms = classroomService.findAll();

    model.addAttribute("reservation", reservation);
    model.addAttribute("classrooms", classrooms);
    model.addAttribute("formatter", DateTimeFormatter.ofPattern("dd日(E)", Locale.JAPANESE));
    model.addAttribute("yearMonthFormatter", DateTimeFormatter.ofPattern("yyyy年MM月", Locale.JAPANESE));
    return "classReservation/index";
  }
}