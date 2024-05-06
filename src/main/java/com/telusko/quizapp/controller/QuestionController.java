package com.telusko.quizapp.controller;


import com.telusko.quizapp.exception.QuestionException;
import com.telusko.quizapp.model.Question;
import com.telusko.quizapp.service.QuestionService;
import com.telusko.quizapp.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("allQuestions")
    public ResponseEntity<Response> getAllQuestions(){
        List<Question> questions = questionService.getAllQuestions();
        if (questions.isEmpty()) {
            return new ResponseEntity<>(
                    Response.builder()
                            .data(null)
                            .message("No question found").build(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(
                Response.builder()
                        .data(questions)
                        .message("Questions retrieved successfully").build(),
                HttpStatus.OK);
    }

    @GetMapping("category/{category}")
    public ResponseEntity<Response> getQuestionsByCategory(@PathVariable String category){
        List<Question> questionsByCategory = questionService.getQuestionsByCategory(category);
        if (questionsByCategory.isEmpty()) {
            return new ResponseEntity<>(
                    Response.builder()
                            .data(null)
                            .message(String.format("No question of category %s found", category)).build(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(
                Response.builder()
                        .data(questionsByCategory)
                        .message(String.format("Questions of category %s retrieved successfully", category)).build(),
                HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<Response> addQuestion(@RequestBody Question question){
        Question savedQuestion = null;
        try {
            savedQuestion = questionService.addQuestion(question);
        } catch (QuestionException e) {
            return new ResponseEntity<>(
                    Response.builder()
                            .data(null)
                            .message(e.getMessage()).build(),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(
                Response.builder()
                        .data(savedQuestion)
                        .message(String.format("Question %s created successfully", question.getQuestionTitle()))
                        .build(),
                HttpStatus.CREATED
        );
    }

}
