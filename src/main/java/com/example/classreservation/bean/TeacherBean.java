package com.example.classreservation.bean;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "teachers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(name = "subjectcode")
    private String subjectCode;

    @ManyToOne
    @JoinColumn(name = "subjectcode", insertable = false, updatable = false)
    private SubjectBean subject;
}