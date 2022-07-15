package com.example.classreservation.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.classreservation.bean.TeacherBean;


public interface TeacherRepository extends JpaRepository<TeacherBean, Integer>{

}