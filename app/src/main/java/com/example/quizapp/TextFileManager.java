package com.example.quizapp;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TextFileManager implements ITextFileManager {
    @Override
    public boolean isCorrectFile(Uri uri, int nofQuestions, Context mContext) {

        try {
            ParsedFile file = new ParsedFile(uri, mContext);

            if (file.Lines.size() == 0) return false;

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public List<QuizQuestion> getQuestions(Uri uri, int nofQuestions, Context mContext) {
        List<QuizQuestion> quizQuestions = new ArrayList<QuizQuestion>();

        ParsedFile file = new ParsedFile(uri, mContext);

        return file.GenerateQuestions(nofQuestions);
    }

    @Override
    public void saveAnswers(List<QuizQuestion> answers, Context mContext) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String sFileName = "answers" + formatter.format(date) + ".txt";
        StringBuilder textToSave = new StringBuilder();
        FileOutputStream fos = null;
        File outputFile = new File(Environment.getExternalStorageDirectory(), sFileName);
        for (QuizQuestion answer : answers)
        {
            textToSave.append( "\t" + answer.question + "\nCorrect answer: "
                    + answer.correctAnswer + "\nYour answer:" + answer.userAnswer + "");
        }
        try {
            fos = new FileOutputStream(outputFile);
            fos.write(textToSave.toString().getBytes(StandardCharsets.UTF_8));
            Log.e("TextFileManager", "Saved to " + Environment.getExternalStorageDirectory() + '/' + sFileName);
            Toast.makeText(mContext, "Saved to " + Environment.getExternalStorageDirectory() + '/' + sFileName,
                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("TextFileManager", e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("TextFileManager", e.toString());
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("TextFileManager", e.toString());
                }
            }
        }

    }
}
