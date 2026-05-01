package com.example.tuwaiqlab7.Controller;

import com.example.tuwaiqlab7.ApiResponse.ApiResponse;
import com.example.tuwaiqlab7.Model.Course;
import com.example.tuwaiqlab7.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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

    //EXTRA ENDPOINTS
    @PutMapping("/watch/{id}")
    public ResponseEntity<?> watchCourse(@PathVariable String id){
        boolean isDone = courseService.watchCourse(id);
        if (isDone)
            return ResponseEntity.status(200).body(new ApiResponse("Course watched successfully"));
        return ResponseEntity.status(404).body(new ApiResponse("No course with ID: " + id + " found."));
    }

    @PutMapping("/like/{id}")
    public ResponseEntity<?> likeCourse(@PathVariable String id){
        boolean isDone = courseService.likeCourse(id);
        if (isDone)
            return ResponseEntity.status(200).body(new ApiResponse("Course liked successfully"));
        return ResponseEntity.status(404).body(new ApiResponse("No course with ID: " + id + " found."));
    }

    @GetMapping("/get-duration/{min}/{max}")
    public ResponseEntity<?> getCoursesByDurationRange(@PathVariable int min, @PathVariable int max){
        ArrayList<Course> durationCourses = courseService.getCoursesByDurationRange(min, max);
        if(durationCourses.isEmpty())
            return ResponseEntity.status(404).body(new ApiResponse("No courses with duration in the rage: (" + min + ", " + max + ") found"));
        return ResponseEntity.status(200).body(durationCourses);
    }

    @GetMapping("/get-most-viewed")
    public ResponseEntity<?> getMostViewedCourse(){
        Course mostViewed = courseService.getMostViewedCourse();
        if(mostViewed == null)
            ResponseEntity.status(404).body(new ApiResponse("No Courses found."));
        return ResponseEntity.status(200).body(mostViewed);
    }

    @GetMapping("/get-most-liked")
    public ResponseEntity<?> getMostLikedCourse(){
        Course mostLiked = courseService.getMostLikedCourse();
        if(mostLiked == null)
            ResponseEntity.status(404).body(new ApiResponse("No Courses found."));
        return ResponseEntity.status(200).body(mostLiked);
    }
}
