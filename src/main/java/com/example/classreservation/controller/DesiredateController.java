package com.example.classreservation.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//import com.example.classreservation.bean.DesireddateBean;
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

    @GetMapping
    String indexGet(Model model) {
        var teachers = teacherService.findAll();
        model.addAttribute("teachers", teachers);

        var desireddates = desireddateService.findAll();
        model.addAttribute("desireddates", desireddates);

        Calendar c = Calendar.getInstance();
        String year = String.valueOf(c.get(Calendar.YEAR));
        String month = String.valueOf(c.get(Calendar.MONTH) + 1);
        if(Integer.parseInt(month)<10){
            month = "0" + month;
        }
        String yearMonth = year + "-" + month;
        model.addAttribute("yearMonth", yearMonth);

        var frames = frameService.findAll();
        model.addAttribute("frames", frames);


        return "desiredDate/index";
    }

    /*@GetMapping(path = "create")
    String createGet(Model model) {
        var frames = frameService.findAll();
        model.addAttribute("frames", frames);
        String teacherId = (String) model.getAttribute("flashTeacherId");
        model.addAttribute("teacherId", teacherId);
        String yearMonth = (String) model.getAttribute("flashYearMonth");
        model.addAttribute("yearMonth", yearMonth);
        List<String> days = (List<String>) model.getAttribute("flashDays");
        model.addAttribute("days", days);

        return "desiredDate/create";
    }*/

    @PostMapping(path = "delete") 
    String delete(@RequestParam Integer id) {
        desireddateService.delete(id);
        return "redirect:/desiredDate/index";
    }

    @PostMapping(path = "index")
    String indexPost(RedirectAttributes redirectAttributes, @Validated DesiredYearMonthForm form, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return indexGet(model);
        }

        var teacherId = form.getTeacherId();
        //redirectAttributes.addFlashAttribute("flashTeacherId", teacherId);
        model.addAttribute("teacherId", teacherId);
        model.addAttribute("tId", Integer.parseInt(teacherId));

        var yearMonth = form.getDesiredYearMonth();
        //redirectAttributes.addFlashAttribute("flashYearMonth", yearMonth);
        model.addAttribute("yearMonth", yearMonth);

        String[] split = yearMonth.split("-");
        Calendar c = new GregorianCalendar(Integer.parseInt(split[0]),Integer.parseInt(split[1])-1,1);
        var dateLength=c.getActualMaximum(Calendar.DAY_OF_MONTH);
        List<String> days = new ArrayList<String>();
        for(int i=0;i<dateLength;i++){
            days.add(String.valueOf(i+1));
        }
        //redirectAttributes.addFlashAttribute("flashDays", days);
        model.addAttribute("days", days);

        var teachers = teacherService.findAll();
        model.addAttribute("teachers", teachers);

        var frames = frameService.findAll();
        model.addAttribute("frames", frames);

        var desireddates = desireddateService.findAll();
        model.addAttribute("desireddates", desireddates);

        var fYearMonth = LocalDate.parse(yearMonth + "-01");
        model.addAttribute("fYearMonth", fYearMonth);
        var lYearMonth = LocalDate.parse(yearMonth + "-" + String.valueOf(dateLength));
        model.addAttribute("lYearMonth", lYearMonth);


        return "desiredDate/create";
    }

    @PostMapping(path = "create")
    String createPost(@Validated DesireddateForm form, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return indexGet(model);
        }
        desireddateService.create(form);

        var teacherId = form.getTeacherId();
        //redirectAttributes.addFlashAttribute("flashTeacherId", teacherId);
        model.addAttribute("teacherId", teacherId);

        var yearMonth = form.getDesiredYearMonth();
        //redirectAttributes.addFlashAttribute("flashYearMonth", yearMonth);
        model.addAttribute("yearMonth", yearMonth);

        String[] split = yearMonth.split("-");
        Calendar c = new GregorianCalendar(Integer.parseInt(split[0]),Integer.parseInt(split[1])-1,1);
        var dateLength=c.getActualMaximum(Calendar.DAY_OF_MONTH);
        List<String> days = new ArrayList<String>();
        for(int i=0;i<dateLength;i++){
            days.add(String.valueOf(i+1));
        }
        //redirectAttributes.addFlashAttribute("flashDays", days);
        model.addAttribute("days", days);

        var teachers = teacherService.findAll();
        model.addAttribute("teachers", teachers);

        var frames = frameService.findAll();
        model.addAttribute("frames", frames);

        var desireddates = desireddateService.findAll();
        model.addAttribute("desireddates", desireddates);

        
        model.addAttribute("tId", Integer.parseInt(teacherId));

        var fYearMonth = LocalDate.parse(yearMonth + "-01");
        model.addAttribute("fYearMonth", fYearMonth);
        var lYearMonth = LocalDate.parse(yearMonth + "-" + String.valueOf(dateLength));
        model.addAttribute("lYearMonth", lYearMonth);

        return "desiredDate/create";
    }
}
