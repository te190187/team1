package com.example.classreservation.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.classreservation.bean.ClassReservationBean;

public interface ClassReservationRepository extends JpaRepository<ClassReservationBean, Integer>, JpaSpecificationExecutor<ClassReservationBean> {
}
