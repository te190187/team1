package com.example.classreservation.models.classReservation;

import java.util.ArrayList;
import java.util.List;

import com.example.classreservation.bean.ClassroomBean;
import com.example.classreservation.bean.GradeBean;
import com.example.classreservation.bean.StudentEntryBean;
import com.example.classreservation.bean.TeacherBean;

import lombok.Data;

@Data
// TODO: idじゃなくてBeanを直接受け取りたいが、StudentEntiriesなどをリストに追加するときにエラーになってしまう。
public class ReservationClassroom {
  public ClassroomBean classroom;
  public Integer subjectId;
  public Integer teacherId;
  public Integer gradeId;
  public List<String> studentNames = new ArrayList<>();

  public ReservationClassroom(ClassroomBean classroom) {
    this.classroom = classroom;
  }

  // 授業を割り当てる
  public void assingLesson(TeacherBean teacher, GradeBean grade, Integer subjectId) {
    this.teacherId = teacher.getId();
    this.gradeId = grade.getId();
    this.subjectId = subjectId;
  }

  // 学生を割り当てる
  public void assignStudent(StudentEntryBean student) {
    studentNames.add(student.getName());
  }
}