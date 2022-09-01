package com.example.classreservation.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.classreservation.bean.StudentEntryBean;
import com.example.classreservation.repository.ClassroomRepository;
import com.example.classreservation.repository.DesireddateRepository;
import com.example.classreservation.repository.StudentEntryRepository;

import lombok.var;

import com.example.classreservation.models.classReservation.*;

@Service
public class ClassReservationService {
  @Autowired
  ClassroomRepository classroomRepository;

  @Autowired
  StudentEntryRepository studentEntryRepository;

  @Autowired
  DesireddateRepository desireddateRepository;

  public Reservation create(String yearMonthText) {
    var yearMonth = YearMonth.parse(yearMonthText);

    // 該当年月の学生の授業予約のリストを取得する。
    var studentEntries = studentEntryRepository.findAll(
      new Specification<StudentEntryBean>() {
        @Override
        public Predicate toPredicate(Root<StudentEntryBean> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            return criteriaBuilder.equal(root.get("dt"), LocalDate.parse(yearMonthText + "-01"));
        }
      }
    );

    // 該当年月の講師の出勤可能リストを取得する。
    // ORMでどうやるかわかんないから、ひとまず全件取得して絞り込む
    var teachersDesiredDates = desireddateRepository.findAll().stream().filter(date -> {
      var target = YearMonth.from(date.getDesiredDt());
      return target.equals(YearMonth.parse(yearMonthText));
    }).collect(Collectors.toList());


    // 教室を取得する
    var classrooms = classroomRepository.findAll();

    // 空の予約表を作成する
    var reservation = new Reservation(yearMonth, classrooms);

    //　予約表に学生、講師をを割り当てる
    reservation.assign(studentEntries, teachersDesiredDates);

    // DBに保存する

    return reservation;
  }
}
