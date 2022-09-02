package com.example.classreservation.bean;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "class_reservation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassReservationBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "classroom_id")
    private Integer classroomId;
    @ManyToOne
    @JoinColumn(name = "classroom_id", insertable = false, updatable = false)
    private ClassroomBean classroom;

    @Column(name = "teacher_id")
    private Integer teacherId;
    @ManyToOne
    @JoinColumn(name = "teacher_id", insertable = false, updatable = false)
    private TeacherBean teacher;

    @Column(name = "student_entry_id")
    private Integer studentEntryId;
    @ManyToOne
    @JoinColumn(name = "student_entry_id", insertable = false, updatable = false)
    private StudentEntryBean studentEntry;

    @Column(name = "frame_id")
    private Integer frameId;
    @ManyToOne
    @JoinColumn(name = "frame_id", insertable = false, updatable = false)
    private FrameBean frame;

    @Column(name = "reservation_date")
    private LocalDate reservationDate;
}
