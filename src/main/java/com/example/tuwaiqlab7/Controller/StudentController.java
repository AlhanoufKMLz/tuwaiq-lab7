package com.example.tuwaiqlab7.Controller;

import com.example.tuwaiqlab7.ApiResponse.ApiResponse;
import com.example.tuwaiqlab7.Model.Student;
import com.example.tuwaiqlab7.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;


    //BASIC CRUD ENDPOINTS
    @GetMapping("/get")
    public ResponseEntity<?> getStudents(){
        return ResponseEntity.status(200).body(studentService.getStudents());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@RequestBody @Valid Student student, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        boolean isDone = studentService.addStudent(student);
        if(isDone)
            return ResponseEntity.status(200).body(new ApiResponse("Student added successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Student ID: " + student.getId() + " already used."));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable String id, @RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        boolean isDone = studentService.updateStudent(id, student);
        if (isDone)
            return ResponseEntity.status(200).body(new ApiResponse("Student updated successfully"));
        return ResponseEntity.status(404).body(new ApiResponse("No student with ID: " + id + " found."));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable String id){
        boolean isDone = studentService.deleteStudent(id);
        if (isDone)
            return ResponseEntity.status(200).body(new ApiResponse("Student deleted successfully"));
        return ResponseEntity.status(404).body(new ApiResponse("No student with ID: " + id + " found."));
    }


    //EXTRA ENDPOINTS
    @GetMapping("/pass-rate/{id}")
    public ResponseEntity<?> getPassRate(@PathVariable String id){
        double rate = studentService.getPassRate(id);

        if(rate == -1)
            return ResponseEntity.status(404).body(new ApiResponse("No student with ID: " + id + " found."));
        if(rate == -2)
            return ResponseEntity.status(400).body(new ApiResponse("No attempts for student with ID: " + id + " found."));
        return ResponseEntity.status(200).body(new ApiResponse("Rate: " + rate + "%"));
    }

    @GetMapping("/get-major-top/{major}")
    public ResponseEntity<?> getMajorTopStudent(@PathVariable String major){
        Student mostLiked = studentService.getMajorTopStudent(major);
        if(mostLiked == null)
            return ResponseEntity.status(404).body(new ApiResponse("No student with major: " + major + " found."));
        return ResponseEntity.status(200).body(mostLiked);
    }

    @GetMapping("/get-major/{major}")
    public ResponseEntity<?> getStudentsByMajor(@PathVariable String major){
        ArrayList<Student> majorStudents = studentService.getStudentsByMajor(major);

        if(majorStudents.isEmpty())
            return ResponseEntity.status(404).body(new ApiResponse("Students with major:" + major + " not found"));
        return ResponseEntity.status(200).body(majorStudents);
    }

    @GetMapping("/get-rank/{id}")
    public ResponseEntity<?> getStudentRank(@PathVariable String id){
        int rank = studentService.getStudentRank(id);
        if(rank == -1)
            return ResponseEntity.status(404).body(new ApiResponse("No student with ID: " + id + " found."));
        return ResponseEntity.status(200).body(new ApiResponse("Student Rank: " + rank));
    }

    @GetMapping("/get-id/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        Student student = studentService.getById(id);
        if(student == null)
            return ResponseEntity.status(404).body("No student with ID: " + id + " found.");
        return ResponseEntity.status(200).body(student);
    }

}
