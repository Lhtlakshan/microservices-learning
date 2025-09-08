package com.example.quizapp.service;

import com.example.quizapp.model.Question;
import com.example.quizapp.model.QuestionsWrapper;
import com.example.quizapp.model.Quiz;
import com.example.quizapp.respository.QuestionRepository;
import com.example.quizapp.respository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    public String createQuiz(String category, int numQ, String title) {
        try{
            List<Question> questions = questionRepository.findRandomQuestionsByCategory(category, numQ);
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            quizRepository.save(quiz);
            return "Quiz successfully created";
        }catch (Exception ex){
            return ex.getMessage();
        }
    }

    public List<QuestionsWrapper> getQuizQuestions(Integer id) {

        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        List<QuestionsWrapper> questionsWrappers = new ArrayList<>();

        for (Question q: questions){
            QuestionsWrapper questionsWrapper = new QuestionsWrapper(
                    q.getId(),
                    q.getQuestionTitle(),
                    q.getOption1(),
                    q.getOption2(),
                    q.getOption3(),
                    q.getOption4()
            );
            questionsWrappers.add(questionsWrapper);
        }
        return questionsWrappers;
    }
}
