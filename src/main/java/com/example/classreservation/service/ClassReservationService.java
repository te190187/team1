package com.example.classreservation.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.classreservation.bean.ClassReservationBean;
import com.example.classreservation.bean.FrameBean;
import com.example.classreservation.bean.StudentEntryBean;
import com.example.classreservation.repository.ClassReservationRepository;
import com.example.classreservation.repository.ClassroomRepository;
import com.example.classreservation.repository.DesireddateRepository;
import com.example.classreservation.repository.FrameRepository;
import com.example.classreservation.repository.StudentEntryRepository;

import lombok.var;

import com.example.classreservation.models.classReservation.*;

@Service
public class ClassReservationService {
  @Autowired
  ClassReservationRepository classReservationRepository;

  @Autowired
  ClassroomRepository classroomRepository;

  @Autowired
  StudentEntryRepository studentEntryRepository;

  @Autowired
  DesireddateRepository desireddateRepository;

  @Autowired
  FrameRepository frameRepository;

  // DBに保存されているデータからReservationクラスを作成する
  public Reservation createByBean(String yearMonthText) {
    var yearMonth = YearMonth.parse(yearMonthText);

    // 該当年月の教室予約のリストを取得する。
    // ORMでどうやるかわかんないから、ひとまず全件取得して絞り込む
    var classReservations = classReservationRepository.findAll().stream().filter(reservatoin -> {
      var target = YearMonth.from(reservatoin.getReservationDate());
      return target.equals(yearMonth);
    }).collect(Collectors.toList());

      // 教室を取得する
      var classrooms = classroomRepository.findAll();

      // コマの情報を取得する
      var frames = frameRepository.findAll();

    return new Reservation(yearMonth, classReservations, classrooms, frames);
  }

  // 年月から教室予約を作成する
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

    // コマの情報を取得する
    var frames = frameRepository.findAll();

    // 空の予約表を作成する
    var reservation = new Reservation(yearMonth, classrooms, frames);

    //　予約表に学生、講師をを割り当てる
    reservation.assign(studentEntries, teachersDesiredDates);

    // TODO
    // DBに保存する前に該当年月の予約表を先に削除する
    // 年月だけで絞りこむ方法がわからないので全件取得して、該当するbeanをdeleteする
    var deletingReservationBeans = classReservationRepository.findAll().stream().filter(res-> {
      var target = YearMonth.from(res.getReservationDate());
      return yearMonth.equals(target);
    }).collect(Collectors.toList());
    classReservationRepository.deleteAll(deletingReservationBeans);

    // DBに保存する
    for (var date : reservation.reservationDates) {
      for (var frame: date.frames) {
        for (var classroom: frame.classrooms) {
          for (var student: classroom.students) {
            var bean = new ClassReservationBean();

            bean.setClassroomId(classroom.getClassroom().getId());
            bean.setTeacherId(classroom.getTeacher().getId());
            bean.setStudentEntryId(student.getId());
            bean.setFrameId(frame.frame.getId());
            bean.setReservationDate(date.date);

            classReservationRepository.save(bean);
          }
        }
      }
    }

    return reservation;
  }
}
