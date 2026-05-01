package com.example.tuwaiqlab7.Controller;

import com.example.tuwaiqlab7.ApiResponse.ApiResponse;
import com.example.tuwaiqlab7.Model.Question;
import com.example.tuwaiqlab7.Model.Student;
import com.example.tuwaiqlab7.Service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;


    //BASIC CRUD ENDPOINTS
    @GetMapping("/get")
    public ResponseEntity<?> getQuestions(){
        return ResponseEntity.status(200).body(questionService.getQuestions());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addQuestion(@RequestBody @Valid Question question, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        boolean isDone = questionService.addQuestion(question);
        if(isDone)
            return ResponseEntity.status(200).body(new ApiResponse("Question added successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Question ID: " + question.getId() + " already used."));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateQuestion(@PathVariable String id, @RequestBody @Valid Question question, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        boolean isDone = questionService.updateQuestion(id, question);
        if (isDone)
            return ResponseEntity.status(200).body(new ApiResponse("Question updated successfully"));
        return ResponseEntity.status(404).body(new ApiResponse("No question with ID: " + id + " found."));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable String id){
        boolean isDone = questionService.deleteQuestion(id);
        if (isDone)
            return ResponseEntity.status(200).body(new ApiResponse("Question deleted successfully"));
        return ResponseEntity.status(404).body(new ApiResponse("No question with ID: " + id + " found."));
    }


    //EXTRA ENDPOINTS
    @GetMapping("/generate-quiz/{count}/{difficulty}")
    public ResponseEntity<?> generateQuiz(@PathVariable int count, @PathVariable String difficulty){
        ArrayList<Question> quiz = questionService.generateQuiz(count, difficulty);
        if(quiz == null)
            return ResponseEntity.status(400).body(new ApiResponse("Difficulty must be Easy, Medium or Hard"));
        if(quiz.size() < count)
            return ResponseEntity.status(404).body(new ApiResponse("No " + difficulty + " enough questions to generate the quiz found"));
        return ResponseEntity.status(200).body(quiz);
    }

    @GetMapping("/get-random")
    public ResponseEntity<?> getRandomQuestion(){
        Question randomQuestion = questionService.getRandomQuestion();
        if(randomQuestion == null)
            return ResponseEntity.status(404).body(new ApiResponse("No questions found"));
        return ResponseEntity.status(200).body(randomQuestion);
    }

    @GetMapping("/get-keyword/{keyword}")
    public ResponseEntity<?> filterByKeyword(@PathVariable String keyword){
        ArrayList<Question> filteredQuestions = questionService.filterByKeyword(keyword);
        if(filteredQuestions.isEmpty())
            return ResponseEntity.status(404).body(new ApiResponse("No questions with the keyword: " + keyword + " found"));
        return ResponseEntity.status(200).body(filteredQuestions);
    }

    @GetMapping("/get-true-false")
    public ResponseEntity<?> getTrueFalseQuestions(){
        ArrayList<Question> trueFalseQuestions = questionService.getTrueFalseQuestions();
        if(trueFalseQuestions.isEmpty())
            return ResponseEntity.status(404).body(new ApiResponse("No true false questions found"));
        return ResponseEntity.status(200).body(trueFalseQuestions);
    }

    @GetMapping("/get-difficulty/{difficulty}")
    public ResponseEntity<?> getByDifficulty(@PathVariable String difficulty){
        ArrayList<Question> difficultyQuestions = questionService.getByDifficulty(difficulty);
        if(difficultyQuestions == null)
            return ResponseEntity.status(400).body(new ApiResponse("Difficulty must be Easy, Medium or Hard"));
        if(difficultyQuestions.isEmpty())
            return ResponseEntity.status(404).body(new ApiResponse("No questions with difficulty: " + difficulty + " found"));
        return ResponseEntity.status(200).body(difficultyQuestions);
    }

    @GetMapping("/get-id/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        Question question = questionService.getById(id);
        if(question == null)
            return ResponseEntity.status(404).body("No question with ID: " + id + " found.");
        return ResponseEntity.status(200).body(question);
    }

}
