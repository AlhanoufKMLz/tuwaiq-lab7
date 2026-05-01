package com.example.tuwaiqlab7.Service;

import com.example.tuwaiqlab7.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {

    ArrayList<Student> students = new ArrayList<>();

    public ArrayList<Student> getStudents(){
        return students;
    }

    public boolean addStudent(Student student){
        for(Student s: students){
            if(s.getId().equalsIgnoreCase(student.getId()))
                return false;
        }
        students.add(student);
        return true;
    }

    public boolean updateStudent(String id, Student student){
        for(int i=0; i < students.size(); i++){
            if(students.get(i).getId().equalsIgnoreCase(id)){
                student.setId(id); //make sure the user doesn't change the id
                students.set(i, student);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(String id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equalsIgnoreCase(id)) {
                students.remove(i);
                return true;
            }
        }
        return false;
    }

}
