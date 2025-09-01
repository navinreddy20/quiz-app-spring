package com.telusko.quizapp.model;

import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionWrapper {

    private Integer id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    
}
