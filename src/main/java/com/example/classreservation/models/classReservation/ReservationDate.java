package com.example.classreservation.models.classReservation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.classreservation.bean.ClassroomBean;
import com.example.classreservation.bean.StudentEntryBean;

public class ReservationDate {
  public LocalDate date;
  public List<ReservationFrame> frames = new ArrayList<>();

  public ReservationDate(LocalDate date, List<ClassroomBean> classrooms) {
    this.date = date;

    // 曜日に応じたframeのリストを作成する
    // 平日が 1,2,3,4,5コマ
    // 休日が 6,7,8コマ
    // 祝日の対応はとりあえず後回しにする。
    var week  = DayOfWeek.from(date);
    if(week == DayOfWeek.SUNDAY || week == DayOfWeek.SATURDAY) {
      frames.add(new ReservationFrame(6, classrooms));
      frames.add(new ReservationFrame(7, classrooms));
      frames.add(new ReservationFrame(8, classrooms));
    } else {
      frames.add(new ReservationFrame(1, classrooms));
      frames.add(new ReservationFrame(2, classrooms));
      frames.add(new ReservationFrame(3, classrooms));
      frames.add(new ReservationFrame(4, classrooms));
      frames.add(new ReservationFrame(5, classrooms));
    }
  }

  // 日のコマ毎に割り当て処理を行う
  public void assign(List<StudentEntryBean> studentLessons) {
    // コマごとの処理
    for(var frame: this.frames) {
      frame.assign(studentLessons);
    }
  }
}