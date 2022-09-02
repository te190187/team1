package com.example.classreservation.models.classReservation;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.classreservation.bean.ClassReservationBean;
import com.example.classreservation.bean.ClassroomBean;
import com.example.classreservation.bean.DesireddateBean;
import com.example.classreservation.bean.FrameBean;
import com.example.classreservation.bean.StudentEntryBean;

public class Reservation {
  public YearMonth yearMonth;
  public List<ReservationDate> reservationDates = new ArrayList<>();

  // DBに保存されているClassReservationBeanのデータからReservationクラスを作成する
  public Reservation(YearMonth yearMonth, List<ClassReservationBean> classReservations, List<ClassroomBean> classrooms, List<FrameBean> frames) {
    // まず空の予約表を作成する
    this(yearMonth, classrooms, frames);

    for (var date: reservationDates) {
      for (var frame: date.frames) {
        for (var classroom: frame.classrooms) {
          for (var bean: classReservations) {
            boolean isSameDate = date.date.equals(bean.getReservationDate());
            boolean isSameFrame = frame.frame.getId().equals(bean.getFrameId());
            boolean isSameClassRoom =  classroom.getClassroom().getId().equals(bean.getClassroomId());

            // ループ中の日、コマ、教室とデータが異なっていれば処理をスキップする
            if(!(isSameDate && isSameFrame && isSameClassRoom)) {
              continue;
            }

            // 講師が割り当てられていなければ教室が予約されていないということなので
            // まず教室の予約を行う
            if (classroom.getTeacher() == null) {
              classroom.setSubject(bean.getTeacher().getSubject());
              classroom.setTeacher(bean.getTeacher());
              classroom.setGrade(bean.getStudentEntry().getGrade());
            }

            // 学生を割り当てる
            classroom.students.add(bean.getStudentEntry());
          }
        }
      }
    }
  }

  // 空の予約表を作成する
  public Reservation(YearMonth yearMonth, List<ClassroomBean> classrooms, List<FrameBean> frames) {
    this.yearMonth = yearMonth;

    // 年月からその月の日数を求めてReservationDateのリストを作成する
    var maxDays = yearMonth.lengthOfMonth();
    for(int i = 1; i <= maxDays; i++) {
      var date = LocalDate.parse(yearMonth.toString() + "-" + String.format("%02d", i));
      var reservationDate = new ReservationDate(date, classrooms, frames);

      this.reservationDates.add(reservationDate);
    }
  }

  // 学生の授業申し込み、講師の出勤可能日のデータを予約表を割り当てる。
  public void assign(List<StudentEntryBean> studentEntries, List<DesireddateBean> teachersDesiredDates) {
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

    // 該当年月日に出勤可能な講師に絞り込む
    var teachers = teachersDesiredDates.stream().filter(date -> {
      return date.getDesiredDt().equals(reserveDate.date);
    }).collect(Collectors.toList());

      reserveDate.assign(studentLessons, teachers);
    }
  }

  // DBに保存されているデータから予約表を組み立てる
  public void build() {

  }
}