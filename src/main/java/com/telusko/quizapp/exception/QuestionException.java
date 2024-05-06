package com.telusko.quizapp.exception;

public class QuestionException extends RuntimeException {

    public QuestionException() {
        super();
    }

    public QuestionException(String message) {
        super(message);
    }

    public QuestionException(String message, Throwable cause) {
        super(message, cause);
    }
}
