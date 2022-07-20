package com.example.classreservation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.classreservation.bean.TeacherBean;
import com.example.classreservation.form.TeacherForm;
import com.example.classreservation.repository.TeacherRepository;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;

    public TeacherForm create(TeacherForm teacherForm) {
        TeacherBean teacherBean = new TeacherBean();
        BeanUtils.copyProperties(teacherForm, teacherBean);
        teacherRepository.save(teacherBean);

        return teacherForm;
    }

    public TeacherForm update(TeacherForm teacherForm) {
        TeacherBean teacherBean = new TeacherBean();
        BeanUtils.copyProperties(teacherForm, teacherBean);
        teacherRepository.save(teacherBean);

        return teacherForm;
    }

    public void delete(Integer id) {
        teacherRepository.deleteById(id);
    }

    public List<TeacherBean> findAll() {
        List<TeacherBean> beanList = teacherRepository.findAll();
        return beanList;
    }

    public TeacherForm findOne(Integer id) {
        // OptionalからorElseで値を取り出す。
        // データが見つからなかった場合の処理は省略して、
        // 適当に新しいteacherBeanをデフォルトで渡す。
        TeacherBean teacherBean = teacherRepository.findById(id).orElse(new TeacherBean());
        TeacherForm teacherForm = new TeacherForm();
        BeanUtils.copyProperties(teacherBean, teacherForm);

        return teacherForm;
    }
}