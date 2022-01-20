package com.example.quizapp;

import android.content.Context;
import android.net.Uri;

import androidx.lifecycle.ViewModel;

import java.util.List;

public class QuizViewModel extends ViewModel {

    private final ITextFileManager fileManager = new TextFileManager();
    private List<QuizQuestion> quizQuestions;
    private final Integer nofQuestions = 5;
    private Integer currentQuestionNumber = 0;

    public boolean fetchQuestions(Uri uri, Context mContext){
        if (fileManager.isCorrectFile(uri, nofQuestions, mContext)) {
            quizQuestions = fileManager.getQuestions(uri, nofQuestions, mContext);
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

    public boolean moreQuestions(Context mContext){
        if (nofQuestions - currentQuestionNumber != 0) return true;
        else {
            fileManager.saveAnswers(quizQuestions, mContext);
            return  false;
        }
    }
}
