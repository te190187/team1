package com.example.classreservation.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

  public Page<ClassroomBean> findAll(Pageable page) {
    return classroomRepository.findAll(page);
  }

  public List<ClassroomBean> findAll(){
    return classroomRepository.findAll();
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
