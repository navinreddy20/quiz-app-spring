package com.telusko.quizapp.service;

import com.telusko.quizapp.exception.QuestionException;
import com.telusko.quizapp.model.Question;
import com.telusko.quizapp.dao.QuestionDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionDao questionDao;

    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public List<Question> getAllQuestions() {
        return this.questionDao.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return this.questionDao.findByCategory(category);
    }

    public Question addQuestion(Question question) {
        if (questionDao.existsByQuestionTitle(question.getQuestionTitle())) {
            throw new QuestionException("Question with title " + question.getQuestionTitle() + " already exists");
        }
        return questionDao.save(question);
    }
}
