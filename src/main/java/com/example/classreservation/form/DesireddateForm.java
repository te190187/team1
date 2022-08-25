package com.example.classreservation.form;

//import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DesireddateForm {
    private Integer id;

    private String teacherId;

    @NotNull
    private String desiredDate;

    @Size(max = 1)
    private String frameId;
}