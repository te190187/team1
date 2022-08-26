package com.example.classreservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.classreservation.form.StudentEntryForm;
import com.example.classreservation.service.GradeService;
import com.example.classreservation.service.StudentEntryService;
import com.example.classreservation.service.SubjectService;

import net.bytebuddy.implementation.bind.MethodDelegationBinder.BindingResolver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("studentEntries")
public class StudentEntryController {
    @Autowired
    StudentEntryService studentEntryService;
    @Autowired
    GradeService gradeService;
    @Autowired
    SubjectService subjectService;

    @ModelAttribute
    StudentEntryForm setupForm(){
        return new StudentEntryForm();
    }

    @GetMapping(path = "create")
    String createGet(Model model) {
        var grades = gradeService.findAll();
        model.addAttribute("grades", grades);

        var subjects = subjectService.findAll();
        model.addAttribute("subjects", subjects);

        return "studentEntries/create";
    }

    @PostMapping(path = "create")
    String createPost(@Validated StudentEntryForm form, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return createGet(model);
        }
        //　ここで予約を作成する
        studentEntryService.create(form);
        

        return "redirect:/studentEntries/create";
    }
    
}
