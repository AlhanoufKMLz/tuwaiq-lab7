package com.example.tuwaiqlab7.Controller;

import com.example.tuwaiqlab7.ApiResponse.ApiResponse;
import com.example.tuwaiqlab7.Model.Exam;
import com.example.tuwaiqlab7.Model.Student;
import com.example.tuwaiqlab7.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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

}
