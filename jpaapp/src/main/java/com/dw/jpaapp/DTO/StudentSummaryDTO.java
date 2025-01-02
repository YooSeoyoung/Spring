package com.dw.jpaapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentSummaryDTO {
    private Long 학생ID;
    private String 학생이름;
    private String 강의명;
    private String 강사이름;
}
