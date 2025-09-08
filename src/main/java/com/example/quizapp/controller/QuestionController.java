package com.example.quizapp.controller;

import com.example.quizapp.model.Question;
import com.example.quizapp.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(questionService.getAllQuestions());
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestion(@PathVariable String category){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(questionService.getQuestionByCategory(category));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(questionService.addQuestion(question));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


}
