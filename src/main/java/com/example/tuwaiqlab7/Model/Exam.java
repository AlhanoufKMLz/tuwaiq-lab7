package com.example.tuwaiqlab7.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Exam {

    @NotEmpty(message = "ID must not be empty")
    @Size(min = 2, message = "ID must be at least 2 character")
    private String id;

    @NotEmpty(message = "Subject must not be empty")
    @Size(min = 3, message = "Subject must be at least 3 character")
    private String subject;

    @NotNull(message = "Questions can't be null")
    @Size(min = 1, message = "Questions must have at least 1 question")
    private ArrayList<Question> questions;

    @NotNull(message = "Attempts must not be null")
    @PositiveOrZero(message = "Attempts must be positive")
    private int attempts;

    @NotNull(message = "Correct Attempts must not be null")
    @PositiveOrZero(message = "Correct Attempts must be positive")
    private int correctAttempts;
}
