package com.example.tuwaiqlab7.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {
    private String id;
    private String name;
    private int duration;
    private int views;
    private int likes;
}
