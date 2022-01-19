package com.example.quizapp;

import android.text.format.DateUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TextFileManager implements ITextFileManager {
    @Override
    public boolean isCorrectFile(String filePath, int nofQuestions) {

        try {
            ParsedFile file = new ParsedFile(filePath);

            if (file.Lines.size() == 0) return false;

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public List<QuizQuestion> getQuestions(String filePath, int nofQuestions) {
        List<QuizQuestion> quizQuestions = new ArrayList<QuizQuestion>();

        ParsedFile file = new ParsedFile(filePath);

        return file.GenerateQuestions(nofQuestions);
    }

    @Override
    public void saveAnswers(List<QuizQuestion> answers) {
        try {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String str = formatter.format(date);
            FileWriter writer = new FileWriter("answers" + str + ".txt");
            for (QuizQuestion answer : answers)
            {
                writer.write("\t" + answer.question + "\nCorrect answer: "
                        + answer.correctAnswer + "\nYour answer:" + answer.userAnswer + "");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
