package com.example.classreservation.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.classreservation.bean.DesireddateBean;


public interface DesireddateRepository extends JpaRepository<DesireddateBean, Integer>, JpaSpecificationExecutor<DesireddateBean>{
    @Override
    public Page<DesireddateBean> findAll(Pageable pageable);
}