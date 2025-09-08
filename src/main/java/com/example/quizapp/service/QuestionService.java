package com.example.quizapp.service;

import com.example.quizapp.model.Question;
import com.example.quizapp.respository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public String addQuestion(Question question) {
        try{
            questionRepository.save(question);
            return "Question saved";
        }catch(Exception ex){
            return "Error : "+ ex.getMessage();
        }
    }

    public List<Question> getQuestionByCategory(String category) throws Exception {
        try{
            return questionRepository.findByCategoryIgnoreCase(category);
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
