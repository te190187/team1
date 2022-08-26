package com.example.classreservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.classreservation.bean.GradeBean;
import com.example.classreservation.repository.GradeRepository;

@Service
public class GradeService {
    @Autowired
    GradeRepository gradeRepository;

    public List<GradeBean> findAll() {
        List<GradeBean> beanList = gradeRepository.findAll();

        return beanList;
    }
}
