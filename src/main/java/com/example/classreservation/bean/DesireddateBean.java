package com.example.classreservation.bean;

import java.time.LocalDate;
//import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "desired_date")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DesireddateBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "teacher_id", nullable = false)
    private String teacherId;

    @Column(name = "dt")
    private LocalDate desiredDt;

    @Column(name = "frame_id")
    private String frameId;

    @ManyToOne
    @JoinColumn(name = "teacher_id", insertable = false, updatable = false)
    private TeacherBean teacher;

    @ManyToOne
    @JoinColumn(name = "frame_id", insertable = false, updatable = false)
    private FrameBean frame;
}