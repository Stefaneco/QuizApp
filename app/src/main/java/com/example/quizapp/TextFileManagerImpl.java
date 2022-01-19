package com.example.quizapp;

import java.util.ArrayList;
import java.util.List;

public class TextFileManagerImpl implements TextFileManager {
    @Override
    public boolean isCorrectFile(String filePath, Integer nofQuestions) {
        return true;
    }

    @Override
    public List<QuizQuestion> getQuestions(String filePath, Integer nofQuestions) {
        List<QuizQuestion> quizQuestions = new ArrayList<QuizQuestion>();
        quizQuestions.add(
                new QuizQuestion("Question 1", "Answer1")
        );
        quizQuestions.add(
                new QuizQuestion("Question 2", "Answer2")
        );
        quizQuestions.add(
                new QuizQuestion("Question 3", "Answer3")
        );
        quizQuestions.add(
                new QuizQuestion("Question 4", "Answer4")
        );
        quizQuestions.add(
                new QuizQuestion("Question 5", "Answer5")
        );
        return quizQuestions;
    }

    @Override
    public void saveAnswers(List<QuizQuestion> answers) {

    }
}
