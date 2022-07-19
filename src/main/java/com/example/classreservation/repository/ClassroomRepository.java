package com.example.classreservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.classreservation.bean.ClassroomBean;

public interface ClassroomRepository extends JpaRepository<ClassroomBean, Integer> {
  
}
