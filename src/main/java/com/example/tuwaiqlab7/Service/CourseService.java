package com.example.tuwaiqlab7.Service;

import com.example.tuwaiqlab7.Model.Course;
import com.example.tuwaiqlab7.Model.Exam;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService {

    ArrayList<Course> courses = new ArrayList<>();

    public ArrayList<Course> getCourses(){
        return courses;
    }

    public boolean addCourse(Course course){
        for(Course c: courses){
            if(c.getId().equalsIgnoreCase(course.getId()))
                return false;
        }
        courses.add(course);
        return true;
    }

    public boolean updateCourse(String id, Course course){
        for(int i=0; i < courses.size(); i++){
            if(courses.get(i).getId().equalsIgnoreCase(id)){
                course.setId(id); //make sure the user doesn't change the id
                courses.set(i, course);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCourse(String id) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equalsIgnoreCase(id)) {
                courses.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean watchCourse(String id){
        for(Course c: courses){
            if(c.getId().equalsIgnoreCase(id)){
                c.setViews(c.getViews()+1);
                return true;
            }
        }
        return false;
    }

    public boolean likeCourse(String id){
        for(Course c: courses){
            if(c.getId().equalsIgnoreCase(id)){
                c.setLikes(c.getLikes()+1);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Course> getCoursesByDurationRange(int min, int max){
        ArrayList<Course> durationCourses = new ArrayList<>();

        for(Course c: courses){
            if(c.getDuration() >= min && c.getDuration() <= max)
                durationCourses.add(c);
        }
        return durationCourses;
    }

    public Course getMostViewedCourse(){
        if(courses.isEmpty())
            return null;

        Course mostViewed = courses.get(0);
        for(Course c: courses){
            if(c.getViews() > mostViewed.getViews())
                mostViewed = c;
        }
        return mostViewed;
    }

    public Course getMostLikedCourse(){
        if(courses.isEmpty())
            return null;

        Course mostLiked = courses.get(0);
        for(Course c: courses){
            if(c.getLikes() > mostLiked.getLikes())
                mostLiked = c;
        }
        return mostLiked;
    }
}
