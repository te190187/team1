package com.example.classreservation.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.classreservation.bean.SubjectBean;
import com.example.classreservation.repository.SubjectRepojitory;

@Service
public class SubjectService {
  @Autowired
  SubjectRepojitory subjectRepojitory;

  public List<SubjectBean> findAll() {
    List<SubjectBean> beanList = subjectRepojitory.findAll();
    return beanList;
  }
}
