package com.telusko.quizapp.utils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizWrapper {
    private Integer id;
    private String title;
}
