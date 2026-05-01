package com.example.tuwaiqlab7.Controller;


import com.example.tuwaiqlab7.ApiResponse.ApiResponse;
import com.example.tuwaiqlab7.Model.Exam;
import com.example.tuwaiqlab7.Service.ExamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;


    //BASIC CRUD ENDPOINTS
    @GetMapping("/get")
    public ResponseEntity<?> getExams(){
        return ResponseEntity.status(200).body(examService.getExams());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addExam(@RequestBody @Valid Exam exam, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        boolean isDone = examService.addExam(exam);
        if(isDone)
            return ResponseEntity.status(200).body(new ApiResponse("Exam added successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Exam ID: " + exam.getId() + " already used."));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateExam(@PathVariable String id, @RequestBody @Valid Exam exam, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        boolean isDone = examService.updateExam(id, exam);
        if (isDone)
            return ResponseEntity.status(200).body(new ApiResponse("Exam updated successfully"));
        return ResponseEntity.status(404).body(new ApiResponse("No exam with ID: " + id + " found."));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteExam(@PathVariable String id){
        boolean isDone = examService.deleteExam(id);
        if (isDone)
            return ResponseEntity.status(200).body(new ApiResponse("Exam deleted successfully"));
        return ResponseEntity.status(404).body(new ApiResponse("No exam with ID: " + id + " found."));
    }


    //EXTRA ENDPOINTS
    @GetMapping("/pass-rate/{id}")
    public ResponseEntity<?> getPassRate(@PathVariable String id){
        double rate = examService.getPassRate(id);

        if(rate == -1)
            return ResponseEntity.status(404).body(new ApiResponse("No exam with ID: " + id + " found."));
        if(rate == 0)
            return ResponseEntity.status(400).body(new ApiResponse("No attempts for exam with ID: " + id + " found."));
        return ResponseEntity.status(200).body(new ApiResponse("Rate: " + rate + "%"));
    }

    @GetMapping("/total-points/{id}")
    public ResponseEntity<?> calculateTotalPoints(@PathVariable String id){
        int totalPoints = examService.calculateTotalPoints(id);

        if(totalPoints == -1)
            return ResponseEntity.status(404).body(new ApiResponse("No exam with ID: " + id + " found."));
        return ResponseEntity.status(200).body(new ApiResponse("Total points: " + totalPoints));
    }

    @GetMapping("/get-difficulty/{id}")
    public ResponseEntity<?> getDifficultyLevel(@PathVariable String id){
        String difficulty = examService.getDifficultyLevel(id);

        if(difficulty.equalsIgnoreCase("not found"))
            return ResponseEntity.status(404).body(new ApiResponse("No exam with ID: " + id + " found."));
        return ResponseEntity.status(200).body(new ApiResponse("Difficulty: " + difficulty));
    }

    @GetMapping("/get-hardest/{subject}")
    public ResponseEntity<?> getHardestSubjectExam(@PathVariable String subject){
        Exam hardestExam = examService.getHardestSubjectExam(subject);

        if(hardestExam == null)
            return ResponseEntity.status(404).body(new ApiResponse("No exam with subject: " + subject + " found."));
        return ResponseEntity.status(200).body(hardestExam);
    }

    @GetMapping("/get-questions/{count}")
    public ResponseEntity<?> getByNumberOfQuestions(@PathVariable int count){
        ArrayList<Exam> exams = examService.getByNumberOfQuestions(count);
        if(exams.isEmpty())
            return ResponseEntity.status(404).body(new ApiResponse("No exams with number of questions: " + count + " found"));
        return ResponseEntity.status(200).body(exams);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        Exam exam = examService.getById(id);
        if(exam == null)
            return ResponseEntity.status(404).body("No exam with ID: " + id + " found.");
        return ResponseEntity.status(200).body(exam);
    }

}
