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
public class StudentEntryForm {
    private Integer id;

    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    
    @Size(min = 1)
    private String subjectId;

    @Size(min = 1)
    private String gradeId;

    @Size(min = 1)
    private String dt;

    @Size(min = 1)
    private String entryDt;
}
