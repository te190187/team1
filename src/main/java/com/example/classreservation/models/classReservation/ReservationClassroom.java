package com.example.classreservation.models.classReservation;

import java.util.ArrayList;
import java.util.List;

import com.example.classreservation.bean.ClassroomBean;
import com.example.classreservation.bean.GradeBean;
import com.example.classreservation.bean.StudentEntryBean;
import com.example.classreservation.bean.SubjectBean;
import com.example.classreservation.bean.TeacherBean;

import lombok.Data;

@Data
public class ReservationClassroom {
  public ClassroomBean classroom;
  public SubjectBean subject;
  public TeacherBean teacher;
  public GradeBean grade;
  public List<StudentEntryBean> students = new ArrayList<>();

  public ReservationClassroom(ClassroomBean classroom) {
    this.classroom = classroom;
  }

  // 授業を割り当てる
  public void assingLesson(TeacherBean teacher, GradeBean grade, SubjectBean subject) {
    this.teacher = teacher;
    this.grade = grade;
    this.subject = subject;
  }

  // 学生を割り当てる
  public void assignStudent(StudentEntryBean student) {
    students.add(student);
  }
}