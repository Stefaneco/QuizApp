package com.example.quizapp;

import androidx.lifecycle.ViewModel;

import java.util.List;

public class QuizViewModel extends ViewModel {

    private final ITextFileManager fileManager = new TextFileManager();
    private List<QuizQuestion> quizQuestions;
    private final Integer nofQuestions = 5;
    private Integer currentQuestionNumber = 0;

    public boolean fetchQuestions(String path){
        if (fileManager.isCorrectFile(path, nofQuestions)) {
            quizQuestions = fileManager.getQuestions(path, nofQuestions);
            return true;
        }
        return false;
    }

    public List<QuizQuestion> getQuestions(){
        return quizQuestions;
    }

    public String getNextQuestion(){
        return quizQuestions.get(currentQuestionNumber).question;
    }

    public void setAnswer(String answer){
        quizQuestions.get(currentQuestionNumber).userAnswer = answer;
        currentQuestionNumber += 1;
    }

    public boolean moreQuestions(){
        if (nofQuestions - currentQuestionNumber != 0) return true;
        else {
            fileManager.saveAnswers(quizQuestions);
            return  false;
        }
    }
}
