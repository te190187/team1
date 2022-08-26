package com.example.classreservation.service;

import java.util.Calendar;
import java.util.GregorianCalendar;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.classreservation.bean.DesireddateBean;
import com.example.classreservation.form.DesiredYearMonthForm;
import com.example.classreservation.form.DesireddateForm;
import com.example.classreservation.repository.DesireddateRepository;

@Service
public class DesireddateService {
    @Autowired
    DesireddateRepository desireddateRepository;

    public DesireddateBean create(DesireddateForm form) {
        DesireddateBean bean = new DesireddateBean();

        String dateStr = form.getDesiredDate();
        bean.setDesiredDate(LocalDate.parse(dateStr));
        bean.setFrameId(form.getFrameId());

        desireddateRepository.save(bean);

        return bean;
    }

    public void delete(Integer id) {
        desireddateRepository.deleteById(id);
    }
}