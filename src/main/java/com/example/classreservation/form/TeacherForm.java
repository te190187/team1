package com.example.classreservation.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherForm {
  private Integer id;

  @NotNull
  @Size(min = 1, max = 255)
  private String name;

  @Size(max = 1)
  private String subjectCode;
}