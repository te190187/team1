package com.example.classreservation.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.classreservation.bean.StudentEntryBean;
import com.example.classreservation.form.StudentEntryForm;
import com.example.classreservation.repository.StudentEntryRepository;

@Service
public class StudentEntryService {
    @Autowired
    StudentEntryRepository studentEntryRepository;

    public StudentEntryBean create(StudentEntryForm form) {
        StudentEntryBean bean = new StudentEntryBean();

        // yyyy-mm-ddをDateに変換する
        bean.setEntryDt(LocalDate.parse(form.getEntryDt()));
        bean.setName(form.getName());
        bean.setGradeId(Integer.parseInt(form.getGradeId()));

        // yyyy-mm を 日が1のDateに変換する
        String dateStr = form.getDt() + "-01";
        bean.setDt(LocalDate.parse(dateStr));
        bean.setSubjectId(Integer.parseInt(form.getSubjectId()));

        studentEntryRepository.save(bean);

        return bean;
    }
}
