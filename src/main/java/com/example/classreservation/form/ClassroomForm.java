package com.example.classreservation.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomForm {
  private Integer id;

  @NotNull
  @Size(min = 1, max = 3)
  private String name;

  @NotNull
  @Min(value = 0)
  private Integer capacity;

  private boolean availableFlag;
}
