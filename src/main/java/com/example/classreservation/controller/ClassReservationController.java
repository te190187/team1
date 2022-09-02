package com.example.classreservation.controller;

import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.classreservation.bean.ClassroomBean;
import com.example.classreservation.form.ClassReservationForm;
import com.example.classreservation.models.classReservation.Reservation;
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

      // 年月が入力されていたら
      Reservation reservation = null;
      List<ClassroomBean> classrooms = new ArrayList<>();
      if (form.getYearMonth() != null && form.getYearMonth() != "") {
        // ここで予約表を作成
        reservation = classReservationService.createByBean(form.getYearMonth());

        classrooms = reservation.reservationDates.get(0)
        .frames.get(0)
        .classrooms.stream().map(resClassroom -> {
          // ReservationClassRoomのリストをClassRoomBeanのリストに変換する
          return resClassroom.classroom;
        }).collect(Collectors.toList());
      }
  
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
  String createPost(@Validated ClassReservationForm form, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
    if(result.hasErrors()) {
      return createGet(model); 
    }

    // ここで予約表を作成
    classReservationService.create(form.getYearMonth());

    redirectAttributes.addFlashAttribute("message", form.getYearMonth() + "の予約表を作成しました。");
    return "redirect:/studentEntries/create";
  }
}