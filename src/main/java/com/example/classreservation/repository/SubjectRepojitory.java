package com.example.classreservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.classreservation.bean.SubjectBean;


public interface SubjectRepojitory extends JpaRepository<SubjectBean, Integer> {
  
}
