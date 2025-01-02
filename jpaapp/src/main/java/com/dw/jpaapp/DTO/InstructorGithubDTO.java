package com.dw.jpaapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InstructorGithubDTO {

    private Long id;
    private String name;
    private String githubUrl;
}

