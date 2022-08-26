package com.example.classreservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.classreservation.bean.StudentEntryBean;

public interface StudentEntryRepository extends JpaRepository<StudentEntryBean, Integer> {
    
}
