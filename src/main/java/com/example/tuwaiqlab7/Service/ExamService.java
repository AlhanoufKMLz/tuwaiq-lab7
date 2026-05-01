package com.example.tuwaiqlab7.Service;

import com.example.tuwaiqlab7.Model.Exam;
import com.example.tuwaiqlab7.Model.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ExamService {

    ArrayList<Exam> exams = new ArrayList<>();

    public ArrayList<Exam> getExams(){
        return exams;
    }

    public boolean addExam(Exam exam){
        for(Exam e: exams){
            if(e.getId().equalsIgnoreCase(exam.getId()))
                return false;
        }
        exams.add(exam);
        return true;
    }

    public boolean updateExam(String id, Exam exam){
        for(int i=0; i < exams.size(); i++){
            if(exams.get(i).getId().equalsIgnoreCase(id)){
                exam.setId(id); //make sure the user doesn't change the id
                exams.set(i, exam);
                return true;
            }
        }
        return false;
    }

    public boolean deleteExam(String id) {
        for (int i = 0; i < exams.size(); i++) {
            if (exams.get(i).getId().equalsIgnoreCase(id)) {
                exams.remove(i);
                return true;
            }
        }
        return false;
    }

    public double getPassRate(String id){
        for(Exam e: exams){
            if(e.getId().equalsIgnoreCase(id)){
                if(e.getAttempts() == 0)
                    return -2;
                return (double) e.getCorrectAttempts() / e.getAttempts() * 100;
            }
        }
        return -1;
    }

    public int calculateTotalPoints(String id){
        int totalPoints = 0;
        for(Exam e: exams){
            if(e.getId().equalsIgnoreCase(id)) {
                for (Question q : e.getQuestions()) {
                    if (q.getDifficulty().equalsIgnoreCase("Easy"))
                        totalPoints += 1;
                    else if (q.getDifficulty().equalsIgnoreCase("Medium"))
                        totalPoints += 2;
                    else
                        totalPoints += 3;
                }
                return totalPoints;
            }
        }
        return -1;
    }

    public String getDifficultyLevel(String id){
        int hardCount = 0;
        int mediumCount = 0;
        int easyCount = 0;

        for(Exam e: exams){
            if(e.getId().equalsIgnoreCase(id)) {
                for (Question q : e.getQuestions()) {
                    if (q.getDifficulty().equalsIgnoreCase("Easy"))
                        easyCount++;
                    else if (q.getDifficulty().equalsIgnoreCase("Medium"))
                        mediumCount++;
                    else
                        hardCount++;
                }
                if (hardCount >= mediumCount && hardCount >= easyCount)
                    return "Hard";
                if (mediumCount >= easyCount)
                    return "Medium";
                return "Easy";
            }
        }
        return "not found";

    }

    public Exam getHardestSubjectExam(String subject){
        //check the hardest based on pass rate
        Exam hardest = null;
        for(Exam e: exams){
            if(e.getSubject().equalsIgnoreCase(subject))
                if(hardest == null || getPassRate(e.getId()) < getPassRate(hardest.getId()))
                    hardest = e;
        }
        return hardest;
    }

    public ArrayList<Exam> getByNumberOfQuestions(int count){
        ArrayList<Exam> exams1 = new ArrayList<>();
        for(Exam e: exams){
            if(e.getQuestions().size() == count)
                exams1.add(e);
        }
        return exams1;
    }

    public Exam getById(String id){
        for(Exam e: exams){
            if(e.getId().equalsIgnoreCase(id))
                return e;
        }
        return null;
    }
}
