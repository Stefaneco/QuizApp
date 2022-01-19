package com.example.quizapp;

import java.io.IOException;
import java.util.List;

public interface ITextFileManager {
    boolean isCorrectFile(String filePath, int nofQuestions);
    List<QuizQuestion> getQuestions(String filePath, int nofQuestions);
    void saveAnswers(List<QuizQuestion> answers);
}
