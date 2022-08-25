package com.example.classreservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.classreservation.bean.GradeBean;

public interface GradeRepository extends JpaRepository<GradeBean, Integer> {
    
}
