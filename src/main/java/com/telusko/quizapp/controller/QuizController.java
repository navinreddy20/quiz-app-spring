package com.telusko.quizapp.controller;

import com.telusko.quizapp.exception.QuizException;
import com.telusko.quizapp.model.QuestionWrapper;
import com.telusko.quizapp.service.QuizService;

import com.telusko.quizapp.utils.QuizWrapper;
import com.telusko.quizapp.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("create")
    public ResponseEntity<Response> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        QuizWrapper quizWrapper = null;
        try {
            quizWrapper = quizService.createQuiz(category, numQ, title);
        } catch (QuizException e) {
            return new ResponseEntity<>(Response.builder()
                    .message(e.getMessage())
                    .build(),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(Response.builder()
                .data(quizWrapper)
                .message("")
                .build(),
                HttpStatus.CREATED);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Response> getQuizQuestions(@PathVariable Integer id){
        try {
            List<QuestionWrapper> quizQuestions = quizService.getQuizQuestions(id);
            return new ResponseEntity<>(Response.builder()
                    .data(quizQuestions)
                    .message(String.format("Questions of quiz with id %s retrieved successfully", id))
                    .build(),
                    HttpStatus.OK);
        } catch (QuizException e) {
            return new ResponseEntity<>(Response.builder()
                    .message(e.getMessage())
                    .build(),
                    HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Response> submitQuiz(@PathVariable Integer id, @RequestBody List<com.telusko.quizapp.model.Response> responses){
        Integer mark = quizService.calculateResult(id, responses);
        return new ResponseEntity<>(Response.builder()
                .data(mark)
                .message("Quiz result calculated successfully")
                .build(),
                HttpStatus.OK);
    }


}
