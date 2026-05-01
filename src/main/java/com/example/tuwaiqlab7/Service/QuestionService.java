package com.example.tuwaiqlab7.Service;

import com.example.tuwaiqlab7.Model.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
public class QuestionService {

    ArrayList<Question> questions = new ArrayList<>();

    public ArrayList<Question> getQuestions(){
        return questions;
    }

    public boolean addQuestion(Question question){
        for(Question q: questions){
            if(q.getId().equalsIgnoreCase(question.getId()))
                return false;
        }
        questions.add(question);
        return true;
    }

    public boolean updateQuestion(String id, Question question){
        for(int i=0; i < questions.size(); i++){
            if(questions.get(i).getId().equalsIgnoreCase(id)){
                question.setId(id); //make sure the user doesn't change the id
                questions.set(i, question);
                return true;
            }
        }
        return false;
    }

    public boolean deleteQuestion(String id) {
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getId().equalsIgnoreCase(id)) {
                questions.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Question> generateQuiz(int count, String difficulty){
        ArrayList<Question> quiz = new ArrayList<>();

        if(!difficulty.equalsIgnoreCase("Easy") && !difficulty.equalsIgnoreCase("Medium") && !difficulty.equalsIgnoreCase("Hard"))
            return null;

        for(int i = 0; i < questions.size() && count > quiz.size(); i++){
            if(questions.get(i).getDifficulty().equalsIgnoreCase(difficulty))
                quiz.add(questions.get(i));
        }
        return quiz;
    }

    public Question getRandomQuestion(){
        if(questions.isEmpty())
            return null;
        Random random = new Random();
        int index = random.nextInt(0, questions.size());
        return questions.get(index);
    }

    public ArrayList<Question> filterByKeyword(String keyword){
        ArrayList<Question> filteredQuestions = new ArrayList<>();

        for(Question q: questions){
            if(q.getQuestion().toLowerCase().contains(keyword.toLowerCase()))
                filteredQuestions.add(q);
        }
        return filteredQuestions;
    }

    public ArrayList<Question> getTrueFalseQuestions(){
        ArrayList<Question> trueFalseQuestions = new ArrayList<>();

        for(Question q: questions){
            if(q.getAnswer().equalsIgnoreCase("true") || q.getAnswer().equalsIgnoreCase("false"))
                trueFalseQuestions.add(q);
        }
        return trueFalseQuestions;
    }

    public ArrayList<Question> getByDifficulty(String difficulty){
        if(!difficulty.equalsIgnoreCase("Easy") && !difficulty.equalsIgnoreCase("Medium") && !difficulty.equalsIgnoreCase("Hard") )
            return null;

        ArrayList<Question> difficultyQuestions = new ArrayList<>();
        for(Question q: questions){
            if(q.getDifficulty().equalsIgnoreCase(difficulty))
                difficultyQuestions.add(q);
        }
        return difficultyQuestions;
    }

    public Question getById(String id){
        for(Question q: questions){
            if(q.getId().equalsIgnoreCase(id))
                return q;
        }
        return null;
    }

}
