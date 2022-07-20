package com.example.classreservation.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.classreservation.form.TeacherForm;
import com.example.classreservation.service.TeacherService;



@Controller
@RequestMapping("teachers")
public class TeacherController {
    @Autowired
    TeacherService teacherservice;

    // teacherFormっていうのが自動的にmodelに設定される？
    // 多分、model.addAttributeをやってくれるっぽい
    // 名前は指定しなければクラス名をキャメルケースにするらしい
    @ModelAttribute
    TeacherForm setupForm() {
        return new TeacherForm();
    }

    @GetMapping
    String list(Model model){
        model.addAttribute("teachers", teacherservice.findAll());
        return "Teachers/TeacherManage";
    }

    @PostMapping(path = "create")
    String create(@Validated TeacherForm form, BindingResult result, Model model){
        if(result.hasErrors()) {
            return list(model);
        }
        teacherservice.create(form);
        return "redirect:/teachers";
    }

    @PostMapping(path = "edit", params = "form")
    String editForm(@RequestParam Integer id, TeacherForm form) {
        TeacherForm teacherForm = teacherservice.findOne(id);
        BeanUtils.copyProperties(teacherForm, form);
        return "Teachers/edit";
    }

    @PostMapping(path = "edit")
    String edit(@RequestParam Integer id, @Validated TeacherForm form, BindingResult result) {
        if(result.hasErrors()) {
            return editForm(id, form);
        }

        teacherservice.update(form);
        return "redirect:/teachers";
    }

    @PostMapping(path = "delete") 
    String delete(@RequestParam Integer id) {
        teacherservice.delete(id);
        return "redirect:/teachers";
    }

    @PostMapping(path = "edit", params = "goToTop")
    String goToTop() {
        return "redirect:/teachers";
    }
}