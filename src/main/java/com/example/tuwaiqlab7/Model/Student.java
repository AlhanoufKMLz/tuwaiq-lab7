package com.example.tuwaiqlab7.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {

    @NotEmpty(message = "ID must not be empty")
    @Size(min = 2, message = "ID must be at least 2 character")
    private String id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 3, message = "Name must be at least 3 character")
    private String name;

    @NotNull(message = "Age must not be empty")
    @Min(value = 12, message = "Age must be at least 12")
    private Integer age;

    @NotEmpty(message = "Major must not be empty")
    @Size(min = 3, message = "Major must be at least 3 character")
    private String major;

    @NotNull(message = "Email must not be empty")
    @Email(message = "Email must be valid")
    private String email;

    @NotNull(message = "Total Attempts must not be empty")
    @PositiveOrZero(message = "Total Attempts must be zero or positive")
    private Integer totalAttempts;

    @NotNull(message = "Passed Exams must not be empty")
    @PositiveOrZero(message = "Passed Exams must be zero or positive")
    private Integer passedExams;
}
