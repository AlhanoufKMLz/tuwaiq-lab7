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

    public double getPassRate(String id){
        for(Student e: students){
            if(e.getId().equalsIgnoreCase(id)){
                if(e.getTotalAttempts() == 0)
                    return 0;
                return (double) e.getPassedExams() / e.getTotalAttempts() * 100;
            }
        }
        return -1;
    }

    public Student getMajorTopStudent(String major){
        if(students.isEmpty())
            return null;

        Student topStudent = null;
        for(Student s: students){
            if(s.getMajor().equalsIgnoreCase(major))
                if(topStudent == null || s.getPassedExams() > topStudent.getPassedExams())
                    topStudent = s;
        }
        return topStudent;
    }

    public ArrayList<Student> getStudentsByMajor(String major){
        ArrayList<Student> majorStudents = new ArrayList<>();
        for(Student s: students){
            if(s.getMajor().equalsIgnoreCase(major))
                majorStudents.add(s);
        }
        return majorStudents;
    }

    public int getStudentRank(String id){
        int studentPassedExams = -1;
        //find the student
        for(Student s: students){
            if(s.getId().equalsIgnoreCase(id))
                studentPassedExams = s.getPassedExams();
        }

        if(studentPassedExams == -1)
            return -1; //not found

        int rank = 1;
        //find rank
        for(Student s: students){
            if(s.getPassedExams() > studentPassedExams)
                rank++;
        }
        return rank;
    }

    public Student getById(String id){
        for(Student s: students){
            if(s.getId().equalsIgnoreCase(id))
                return s;
        }
        return null;
    }

}
