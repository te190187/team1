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
public class BookForm {
  private Integer id;

  @NotNull
  @Size(min = 3)
  private String title;

  @Size(min = 3, max = 20)
  private String writter;

  private String publisher;

  @Min(0)
  private Integer price;
}