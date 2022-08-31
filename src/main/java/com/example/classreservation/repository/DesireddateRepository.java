package com.example.classreservation.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.classreservation.bean.DesireddateBean;


public interface DesireddateRepository extends JpaRepository<DesireddateBean, Integer>{
    @Override
    public Page<DesireddateBean> findAll(Pageable pageable);
}