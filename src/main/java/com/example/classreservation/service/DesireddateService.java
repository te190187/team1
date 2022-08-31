package com.example.classreservation.service;

import java.util.List;
import java.time.LocalDate;

//import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.classreservation.bean.DesireddateBean;
//import com.example.classreservation.form.DesiredYearMonthForm;
import com.example.classreservation.form.DesireddateForm;
import com.example.classreservation.repository.DesireddateRepository;

@Service
public class DesireddateService {
    @Autowired
    DesireddateRepository desireddateRepository;

    public DesireddateBean create(DesireddateForm form) {
        DesireddateBean bean = new DesireddateBean();

        String dateStr = form.getDesiredDate();
        if(Integer.parseInt(dateStr) < 10){
            dateStr = "0" + dateStr;
        }
        String yearMonthStr = form.getDesiredYearMonth();
        bean.setTeacherId(form.getTeacherId());
        bean.setFrameId(form.getFrameId());
        bean.setDesiredDt(LocalDate.parse(yearMonthStr + "-" + dateStr));

        desireddateRepository.save(bean);

        return bean;
    }

    public void delete(Integer id) {
        desireddateRepository.deleteById(id);
    }

    public List<DesireddateBean> findAll() {
        List<DesireddateBean> beanList = desireddateRepository.findAll();
        return beanList;
    }
}