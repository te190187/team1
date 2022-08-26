package com.example.classreservation.form;

//import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DesiredYearMonthForm {
    private String teacherId;

    @NotNull
    private String desiredYearMonth;
}