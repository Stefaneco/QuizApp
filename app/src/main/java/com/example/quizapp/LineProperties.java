package com.example.quizapp;

import android.util.Log;

import java.util.List;

public class LineProperties {
    public Integer LineNumber;
    public String Name;
    public String Value;
    public Integer Level;
    public LineProperties Parent;
    LineProperties(Integer LineNumber, String name, Integer level, LineProperties parent) {
        this.LineNumber = LineNumber;
        this.Name = name;
        this.Level = level;
        this.Parent = parent;
    }

    public LineProperties(Integer lineNumber, String name, String value, Integer level, LineProperties parent) {
        LineNumber = lineNumber;
        Name = name;
        Value = value;
        Level = level;
        Parent = parent;
    }

    //Parses single line
    public static LineProperties returnLineProperties(Integer lineNumber, String line, LineProperties parent) {
        int level = 0;
        if (line.length() >= 4){
            while (line.substring(0, 4).equals("    ")){
                line = line.substring(4);
                level++;
            }
        }
        return new LineProperties(lineNumber, line, level, parent);
    }

    public String GetLineParents()
    {
        StringBuilder output = new StringBuilder(this.Name.trim());
        LineProperties parent = this.Parent;
        while (parent != null)
        {
            output.insert(0, parent.Name.trim() + ":\n");
            parent = parent.Parent;
        }
        return output.toString();
    }

    //Draws structure as in file
    public static void drawStructure(List<LineProperties> lines) {
        for (LineProperties line : lines){
            String tabs = "";
            for (int i = 0; i < line.Level; i++) {
                tabs += "    ";
            }
            System.out.println(tabs + line.Name);
        }
    }
    public int getLineNumber() {
        return this.LineNumber;
    }
}
