package com.telusko.quizapp.exception;

public class QuizException extends RuntimeException {

    public QuizException() {
        super();
    }

    public QuizException(String message) {
        super(message);
    }

    public QuizException(String message, Throwable cause) {
        super(message, cause);
    }
}
