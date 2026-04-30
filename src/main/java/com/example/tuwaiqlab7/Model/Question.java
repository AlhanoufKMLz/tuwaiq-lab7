package com.example.tuwaiqlab7.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Question {

    @NotEmpty(message = "ID must not be empty")
    @Size(min = 2, message = "ID must be at least 2 character")
    private String id;

    @NotEmpty(message = "Question must not be empty")
    @Size(min = 5, message = "Question must be at least 5 character")
    private String question;

    @NotEmpty(message = "Answer must not be empty")
    @Size(min = 4, message = "Answer must be at least 4 character")
    private String answer;

    @NotEmpty(message = "Difficulty must not be empty")
    @Pattern(regexp = "(?i)^(Easy|Medium|Hard)$", message = "Difficulty must be Easy, Medium or Hard")
    private String difficulty;
}
