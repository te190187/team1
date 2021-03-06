package com.example.classreservation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.classreservation.bean.ClassroomBean;

public interface ClassroomRepository extends JpaRepository<ClassroomBean, Integer> {
  @Override
  public Page<ClassroomBean> findAll(Pageable pageable);
}
