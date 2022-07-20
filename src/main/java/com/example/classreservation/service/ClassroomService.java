package com.example.classreservation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.classreservation.bean.ClassroomBean;
import com.example.classreservation.form.ClassroomForm;
import com.example.classreservation.repository.ClassroomRepository;

@Service
public class ClassroomService {
  @Autowired
  ClassroomRepository classroomRepository;

  public ClassroomForm create(ClassroomForm classroomForm) {
    ClassroomBean classroomBean = new ClassroomBean();
    BeanUtils.copyProperties(classroomForm, classroomBean);
    classroomRepository.save(classroomBean);

    return classroomForm;
  }

  public ClassroomForm update(ClassroomForm classroomForm) {
    ClassroomBean classroomBean = new ClassroomBean();
    BeanUtils.copyProperties(classroomForm, classroomBean);
    classroomRepository.save(classroomBean);

    return classroomForm;
  }

  public void delete(Integer id) {
      classroomRepository.deleteById(id);
  }

  public List<ClassroomForm> findAll() {
      List<ClassroomBean> beanList = classroomRepository.findAll();
      List<ClassroomForm> formList = new ArrayList<>();
      
      for(ClassroomBean classroomBena: beanList) {
          ClassroomForm classroomForm = new ClassroomForm();
          BeanUtils.copyProperties(classroomBena, classroomForm);
          formList.add(classroomForm);
      }

      return formList;
  }

  public ClassroomForm findOne(Integer id) {
      // OptionalからorElseで値を取り出す。
      // データが見つからなかった場合は例外を投げさせる
      ClassroomBean classroomBean = classroomRepository.findById(id).orElseThrow();
      ClassroomForm classroomForm = new ClassroomForm();
      BeanUtils.copyProperties(classroomBean, classroomForm);

      return classroomForm;
  }
}
