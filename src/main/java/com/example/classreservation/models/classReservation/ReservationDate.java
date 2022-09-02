package com.example.classreservation.models.classReservation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.classreservation.bean.ClassroomBean;
import com.example.classreservation.bean.DesireddateBean;
import com.example.classreservation.bean.FrameBean;
import com.example.classreservation.bean.StudentEntryBean;

public class ReservationDate {
  public LocalDate date;
  public boolean isSaturday;
  public boolean isSunday;
  public List<ReservationFrame> frames = new ArrayList<>();

  public ReservationDate(LocalDate date, List<ClassroomBean> classrooms, List<FrameBean> allFrames) {
    this.date = date;

    // 曜日に応じたframeのリストを作成する
    // 平日が 1,2,3,4,5コマ
    // 休日が 6,7,8コマ
    // 祝日の対応はとりあえず後回しにする。
    var week  = DayOfWeek.from(date);
    if(week == DayOfWeek.SUNDAY || week == DayOfWeek.SATURDAY) {
      isSunday = week == DayOfWeek.SUNDAY;
      isSaturday = week == DayOfWeek.SATURDAY;

      var holidayFrames =  allFrames.stream().filter(frame -> {
        return Arrays.asList(1,2,3,4,5).contains(frame.getId());
      }).collect(Collectors.toList());

      holidayFrames.forEach(frame -> {
        frames.add(new ReservationFrame(frame, classrooms));
      });

    } else {

      var weekDayFrames =  allFrames.stream().filter(frame -> {
        return Arrays.asList(6,7,8).contains(frame.getId());
      }).collect(Collectors.toList());

      weekDayFrames.forEach(frame -> {
        frames.add(new ReservationFrame(frame, classrooms));
      });
    }
  }

  // 日のコマ毎に割り当て処理を行う
  public void assign(List<StudentEntryBean> studentLessons, List<DesireddateBean> teachersDesiredDates) {
    // コマごとの処理
    for(var frame: this.frames) {

      // 該当コマに出勤可能な講師に絞り込む
      var teachers = teachersDesiredDates.stream().filter(teacher -> {
        return Integer.parseInt(teacher.getFrameId()) == frame.frame.getId();
      }).collect(Collectors.toList());

      frame.assign(studentLessons, teachers);
    }
  }
}