package com.example.classreservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.classreservation.bean.StudentEntryBean;

public interface StudentEntryRepository extends JpaRepository<StudentEntryBean, Integer>, JpaSpecificationExecutor<StudentEntryBean> {
}
