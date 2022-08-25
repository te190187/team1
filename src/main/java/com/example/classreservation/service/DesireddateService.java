package com.example.classreservation.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.classreservation.bean.DesireddateBean;
import com.example.classreservation.form.DesireddateForm;
import com.example.classreservation.repository.DesireddateRepository;

@Service
public class DesireddateService {
    @Autowired
    DesireddateRepository desireddateRepository;

    public DesireddateForm create(DesireddateForm desireddateForm) {
        DesireddateBean desireddateBean = new DesireddateBean();
        BeanUtils.copyProperties(desireddateForm, desireddateBean);
        desireddateRepository.save(desireddateBean);

        return desireddateForm;
    }

    public DesireddateForm update(DesireddateForm desireddateForm) {
        DesireddateBean desireddateBean = new DesireddateBean();
        BeanUtils.copyProperties(desireddateForm, desireddateBean);
        desireddateRepository.save(desireddateBean);

        return desireddateForm;
    }

    public void delete(Integer id) {
        desireddateRepository.deleteById(id);
    }

    public Page<DesireddateBean> findAll(Pageable pageable) {
        return desireddateRepository.findAll(pageable);
    }

    public DesireddateForm findOne(Integer id) {
        // OptionalからorElseで値を取り出す。
        // データが見つからなかった場合の処理は省略して、
        // 適当に新しいDesireddateBeanをデフォルトで渡す。
        DesireddateBean desireddateBean = desireddateRepository.findById(id).orElse(new DesireddateBean());
        DesireddateForm desireddateForm = new DesireddateForm();
        BeanUtils.copyProperties(desireddateBean, desireddateForm);

        return desireddateForm;
    }
}