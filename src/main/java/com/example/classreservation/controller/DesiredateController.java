package com.example.classreservation.controller;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.classreservation.form.DesiredYearMonthForm;
import com.example.classreservation.form.DesireddateForm;
import com.example.classreservation.service.DesireddateService;
import com.example.classreservation.service.FrameService;
import com.example.classreservation.service.TeacherService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("desiredDate")
public class DesiredateController {
    @Autowired
    DesireddateService desireddateService;
    @Autowired
    FrameService frameService;
    @Autowired
    TeacherService teacherService;

    @ModelAttribute
    DesireddateForm setupForm(){
        return new DesireddateForm();
    }
    @ModelAttribute
    DesiredYearMonthForm setupForm2(){
        return new DesiredYearMonthForm();
    }

    @GetMapping(path = "index")
    String indexGet(Model model) {
        var teachers = teacherService.findAll();
        model.addAttribute("teachers", teachers);

        return "desiredDate/index";
    }

    @GetMapping(path = "create")
    String createGet(Model model) {
        var frames = frameService.findAll();
        model.addAttribute("frames", frames);

        return "desiredDate/create";
    }

    
    @PostMapping(path = "index")
    String indexPost(@Validated DesiredYearMonthForm form, BindingResult result, Model model) {
        System.out.print(form);
        if(result.hasErrors()) {
            System.out.print("error!!!!!!!!!!!!");
            return createGet(model);
        }
        
        String yearMonth = form.getDesiredYearMonth();
        model.addAttribute("yearMonth", yearMonth);

        var teacherId = form.getTeacherId();
        model.addAttribute("teacherId", teacherId);

        String[] split = yearMonth.split("-");
        Calendar c = new GregorianCalendar(Integer.parseInt(split[0]),Integer.parseInt(split[1])-1,1);
        var days=c.getActualMaximum(Calendar.DAY_OF_MONTH);
        model.addAttribute("days", days);
        

        return "redirect:/desiredDate/create";
    }

    @PostMapping(path = "create")
    String createPost(@Validated DesireddateForm form, BindingResult result, Model model) {
        System.out.print(form);
        if(result.hasErrors()) {
            System.out.print("error!!!!!!!!!!!!");
            return createGet(model);
        }
        desireddateService.create(form);
        

        return "redirect:/desiredDate/create";
    }
}
