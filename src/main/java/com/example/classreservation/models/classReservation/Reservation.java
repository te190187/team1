package com.example.classreservation.models.classReservation;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.example.classreservation.bean.ClassroomBean;
import com.example.classreservation.bean.StudentEntryBean;

public class Reservation {
  public YearMonth yearMonth;
  public List<ReservationDate> reservationDates = new ArrayList<>();

  public Reservation(YearMonth yearMonth, List<ClassroomBean> classrooms) {
    this.yearMonth = yearMonth;

    // 年月からその月の日数を求めてReservationDateのリストを作成する
    var maxDays = yearMonth.lengthOfMonth();
    for(int i = 1; i < maxDays; i++) {
      var date = LocalDate.parse(yearMonth.toString() + "-" + String.format("%02d", i));
      var reservationDate = new ReservationDate(date, classrooms);

      this.reservationDates.add(reservationDate);
    }
  }

  public void assign(List<StudentEntryBean> studentEntries) {
    // 学生の申し込みデータを、授業を受ける回数分複製する。
    // 1月に12コマ受ける必要があるため、12個に複製する。
    var lessonsPerMonth = 12;
    List<StudentEntryBean> studentLessons = new ArrayList<>();
    for(var student: studentEntries) {
      for(int i = 0; i < lessonsPerMonth; i++) {
        studentLessons.add(student);
      }
    }

    // 日ごとの処理
    for (var reserveDate : this.reservationDates) {
      reserveDate.assign(studentLessons);
    }
  }
}