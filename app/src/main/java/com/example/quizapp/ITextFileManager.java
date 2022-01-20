package com.example.quizapp;

import android.content.Context;
import android.net.Uri;

import java.io.IOException;
import java.util.List;

public interface ITextFileManager {
    boolean isCorrectFile(Uri uri, int nofQuestions, Context mContext);
    List<QuizQuestion> getQuestions(Uri uri, int nofQuestions, Context mContext);
    void saveAnswers(List<QuizQuestion> answers, Context mContext);
}
