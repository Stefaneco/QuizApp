package com.example.quizapp;

import java.util.List;

public interface TextFileManager {
    boolean isCorrectFile(String filePath, Integer nofQuestions);
    List<QuizQuestion> getQuestions(String filePath, Integer nofQuestions);
    void saveAnswers(List<QuizQuestion> answers);
}
