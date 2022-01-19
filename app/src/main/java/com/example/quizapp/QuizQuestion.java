package com.example.quizapp;

public class QuizQuestion {
    public String question;
    public String correctAnswer;
    public String userAnswer = "";

    public QuizQuestion(String question, String correctAnswer){
        this.question = question;
        this.correctAnswer = correctAnswer;
    }
}
