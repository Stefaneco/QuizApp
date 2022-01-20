package com.example.quizapp;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class ParsedFile {
    //All lines in parsed file, even with no value.
    public List<LineProperties> Lines = new ArrayList<LineProperties>();

    public ParsedFile(Uri uri, Context mContext) {

        String fileContent = "";
        try {
            fileContent = readTextFromUri(uri, mContext);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        int lineNumber = 0;
        for (String line : fileContent.split("\n")) {
            int indentLevel = 0;
            while (fileContent.startsWith("    ")) {
                indentLevel++;
                fileContent.replaceFirst(" {4}", "");
            }
            int colonPosition = line.indexOf(':');
            String Name;
            String Value;
            if (colonPosition > -1) {
                Name = line.substring(0, colonPosition);
                Value = line.substring(colonPosition + 1);
            } else {
                Name = line;
                Value = null;
            }
            //Gets parent to maintain file structure
            LineProperties parent = GetParent(lineNumber, indentLevel);
            LineProperties newLine = new LineProperties(lineNumber, Name, Value, indentLevel, parent);

            Lines.add(newLine);
            lineNumber++;
        }
        Log.e("ParasedFile", String.valueOf(lineNumber));
    }

    public LineProperties GetParent(int LineNumber, int ChildIndentLevel) {
        if (Lines.size() <= 1) return null;

        for (int i = LineNumber - 1; i >= 0; i--) {
            if (Lines.get(i).Level < ChildIndentLevel) return Lines.get(i);
        }
        return null;
    }

    public List<QuizQuestion> GenerateQuestions(int nOfQuestions)
    {
        List<QuizQuestion> output = new ArrayList<>();

        for (int i = 0; i < nOfQuestions; i++)
        {
            Random random = new Random();
            LineProperties line = null;
            while (line == null || line.Value == null)
            {
                line = this.Lines.get(random.nextInt(this.Lines.size()));
            }
            String question = "What is the value of " + line.GetLineParents() + "?";
            output.add(new QuizQuestion("What is the value of " + line.GetLineParents() + "?", line.Value));
        }
        return output;
    }

    private String readTextFromUri(Uri uri, Context mContext) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream =
                     mContext.getContentResolver().openInputStream(uri);
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(Objects.requireNonNull(inputStream)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + '\n');
            }
        }
        return stringBuilder.toString();
    }
}
