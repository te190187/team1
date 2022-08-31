package com.example.classreservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.classreservation.bean.FrameBean;


public interface FrameRepository extends JpaRepository<FrameBean, Integer> {
  
}
