package com.example.classreservation.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
import com.example.classreservation.service.SubjectService;
import com.example.classreservation.service.TeacherService;



@Controller
@RequestMapping("teachers")
public class TeacherController {
    @Autowired
    TeacherService teacherservice;

    @Autowired
    SubjectService subjectService;

    // teacherFormっていうのが自動的にmodelに設定される？
    // 多分、model.addAttributeをやってくれるっぽい
    // 名前は指定しなければクラス名をキャメルケースにするらしい
    @ModelAttribute
    TeacherForm setupForm() {
        return new TeacherForm();
    }

    @GetMapping
    String list(Model model, Pageable pageable){
        // 教科データを読み込み、モデルに追加する
        model.addAttribute("subjects", subjectService.findAll());

        // 教師データを読み込み、モデルに追加する
        var page = teacherservice.findAll(pageable);
        model.addAttribute("teachers", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("url", "/teachers");

        return "Teachers/TeacherManage";
    }

    @PostMapping(path = "create")
    String create(@Validated TeacherForm form, BindingResult result, Model model, Pageable pageable){
        if(result.hasErrors()) {
            return list(model, pageable);
        }
        teacherservice.create(form);
        return "redirect:/teachers";
    }

    @PostMapping(path = "edit", params = "form")
    String editForm(@RequestParam Integer id, TeacherForm form, Model model) {
        TeacherForm teacherForm = teacherservice.findOne(id);
        BeanUtils.copyProperties(teacherForm, form);
        
        // 教科データを読み込み、モデルに追加する
        model.addAttribute("subjects", subjectService.findAll());
        return "Teachers/TeacherEdit";
    }

    @PostMapping(path = "edit")
    String edit(@RequestParam Integer id, @Validated TeacherForm form, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return editForm(id, form, model);
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