package com.example.classreservation.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.classreservation.bean.FrameBean;
import com.example.classreservation.repository.FrameRepository;

@Service
public class FrameService {
    @Autowired
    FrameRepository frameRepository;

    public List<FrameBean> findAll() {
        List<FrameBean> beanList = frameRepository.findAll();
        return beanList;
    }
}
