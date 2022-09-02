package com.example.classreservation.models.classReservation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.classreservation.bean.ClassroomBean;
import com.example.classreservation.bean.DesireddateBean;
import com.example.classreservation.bean.StudentEntryBean;


public class ReservationFrame {
  public Integer frameNumber;
  public List<ReservationClassroom> classrooms = new ArrayList<>();

  public ReservationFrame(Integer frameNumber, List<ClassroomBean> allClassrooms) {
    this.frameNumber = frameNumber;

    for(var classroom: allClassrooms) {
      classrooms.add(new ReservationClassroom(classroom));
    }
  }

  public void assign(List<StudentEntryBean> studentLessons, List<DesireddateBean> teachersDesiredDates) {
    // 特定の日の特定のコマには、同じ学生を割り当てることはできないので、
    // studentLessonsから 重複している学生を取り除く
    var uniqueStudents = studentLessons.stream().distinct().collect(Collectors.toList());
    var assignedStudents = new ArrayList<StudentEntryBean>();

    // ここで、特定のコマのReservationClassroomに講師、学生を割り当てていく。
    // この中の実装を変えることで、割り当て条件を変更したりできる？
    for(var classroom: this.classrooms) {
      for(var student: uniqueStudents) {

        // 教室が予約されていなければ
        if (classroom.getSubject() == null) {

          // 割り当てる予定の学生が受講したい項目を担当する教師を選択する
          var teachers = teachersDesiredDates.stream().filter(t -> {
            return Integer.parseInt(t.getTeacher().getSubjectCode()) == student.getSubject().getId();
          }).collect(Collectors.toList());

          // 割り当てられる講師がいなければ
          if(teachers.isEmpty()){
            continue;
          }

          var teacher = teachers.get(0);

          // 教室に授業、学生を割り当てる
          classroom.assingLesson(teacher.getTeacher(), student.getGrade(), student.getSubject());
          classroom.assignStudent(student);

          // 学生を割り当て済みのリストに入れる
          assignedStudents.add(student);

          // 割り当てられる講師のリストから講師を削除する
          teachersDesiredDates.remove(teacher);
        } else {
          
          // 学年、科目が等しい学生を教室に割り当てる
          if(
            classroom.getGrade().getId() == student.getGradeId() &&
            classroom.getSubject().getId() == student.getSubjectId()
          ) {
            classroom.assignStudent(student);
            assignedStudents.add(student);
          }
        }
        //ここでClassReservationBeanにsetしてClassReservationRepositryにsave？
        //classroom.saveReservation()???
      }

      // 学生のループが終わった後に、割り当てを行った学生をリストから取り除く
      assignedStudents.forEach(assignedStudent -> {

        // このコマに割り当てられる学生のリストから削除する
        uniqueStudents.remove(assignedStudent);

        // 全体のリストから割り当てた学生のアイテムを一つだけ削除する (12個に複製されているため)
        // filterで同じidのアイテムを取得してfindFirstで先頭のアイテムを取得する
        var target = studentLessons.stream().filter(lesson -> lesson.getId() == assignedStudent.getId()).findFirst();
        if(target.isPresent()){
          studentLessons.remove(target.get());
        }
      });

      // リストを初期化する
      assignedStudents.clear();
    }
  }
}
