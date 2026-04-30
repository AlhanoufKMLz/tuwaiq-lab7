package com.example.tuwaiqlab7.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {

    @NotEmpty(message = "ID must not be empty")
    @Size(min = 2, message = "ID must be at least 2 character")
    private String id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 3, message = "Name must be at least 3 character")
    private String name;

    @NotNull(message = "Duration must not be empty")
    @Positive(message = "Duration must be positive")
    private Integer duration;

    @NotNull(message = "Views must not be empty")
    @PositiveOrZero(message = "Views must be zero or positive")
    private Integer views;

    @NotNull(message = "Likes must not be empty")
    @PositiveOrZero(message = "Likes must be zero or positive")
    private Integer likes;
}
