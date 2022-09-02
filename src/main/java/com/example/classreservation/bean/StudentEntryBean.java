package com.example.classreservation.bean;

import java.util.List;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student_entry")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntryBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer gradeId;

    @ManyToOne
    @JoinColumn(name = "gradeId", insertable = false, updatable = false)
    private GradeBean grade;

    private String name;

    private LocalDate dt;

    private Integer subjectId;

    @ManyToOne
    @JoinColumn(name = "subjectId", insertable = false, updatable = false)
    private SubjectBean subject;

    private LocalDate entryDt;

    @OneToMany(mappedBy = "studentEntry")
    private List<ClassReservationBean> classReservations;
}
