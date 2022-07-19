package com.example.classreservation.controller;

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

import com.example.classreservation.form.ClassroomForm;
import com.example.classreservation.service.ClassroomService;

@Controller
@RequestMapping("classrooms")
public class ClassroomController {
  @Autowired
  ClassroomService classroomService;

  @ModelAttribute
  ClassroomForm setupForm() {
    return new ClassroomForm();
  }

  @GetMapping
  String index(Model model){
    var classrooms = classroomService.findAll();
    model.addAttribute("classrooms", classrooms);
    return "classrooms/index";
  }

  @PostMapping(path = "create")
  String create(@Validated ClassroomForm form, BindingResult result, Model model){
    if(result.hasErrors()){
      return index(model);
    }

    classroomService.create(form);
    return "redirect:/classrooms";
  }

  @PostMapping(path =  "edit", params = "form")
  String editForm(@RequestParam Integer id, ClassroomForm form) {
    ClassroomForm classroomForm = classroomService.findOne(id);
    BeanUtils.copyProperties(classroomForm, form);
    return "classrooms/edit";
  }

  @PostMapping(path = "edit")
  String edit(@RequestParam Integer id, @Validated ClassroomForm form, BindingResult result){
    if(result.hasErrors()) {
      return editForm(id, form);
    }

    classroomService.update(form);
    return "redirect:/classrooms";
  }

  @PostMapping(path = "delete")
  String delete(@RequestParam Integer id) {
    classroomService.delete(id);
    return "redirect:/classrooms";
  }

  @PostMapping(path = "edit", params = "goToTop")
  String goToTop() {
    return "redirect:/classrooms";
  }
}
