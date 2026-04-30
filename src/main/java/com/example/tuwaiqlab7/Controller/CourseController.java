package com.example.tuwaiqlab7.Controller;

import com.example.tuwaiqlab7.ApiResponse.ApiResponse;
import com.example.tuwaiqlab7.Model.Course;
import com.example.tuwaiqlab7.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;


    //BASIC CRUD ENDPOINTS
    @GetMapping("/get")
    public ResponseEntity<?> getCourses(){
        return ResponseEntity.status(200).body(courseService.getCourses());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCourse(@RequestBody @Valid Course course, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        boolean isDone = courseService.addCourse(course);
        if(isDone)
            return ResponseEntity.status(200).body(new ApiResponse("Course added successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Course ID: " + course.getId() + " already used."));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable String id, @RequestBody @Valid Course course, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        boolean isDone = courseService.updateCourse(id, course);
        if (isDone)
            return ResponseEntity.status(200).body(new ApiResponse("Course updated successfully"));
        return ResponseEntity.status(404).body(new ApiResponse("No Course with ID: " + id + " found."));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable String id){
        boolean isDone = courseService.deleteCourse(id);
        if (isDone)
            return ResponseEntity.status(200).body(new ApiResponse("Course deleted successfully"));
        return ResponseEntity.status(404).body(new ApiResponse("No course with ID: " + id + " found."));
    }

}
