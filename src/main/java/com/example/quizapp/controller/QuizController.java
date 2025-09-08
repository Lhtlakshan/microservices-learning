package com.example.quizapp.controller;

import com.example.quizapp.model.Question;
import com.example.quizapp.model.QuestionsWrapper;
import com.example.quizapp.model.Quiz;
import com.example.quizapp.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/create")
    private ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int num, @RequestParam String title){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(quizService.createQuiz(category, num, title));
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/questions/{id}")
    public ResponseEntity<List<QuestionsWrapper>> getQuizQuestions(@PathVariable Integer id){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(quizService.getQuizQuestions(id));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
