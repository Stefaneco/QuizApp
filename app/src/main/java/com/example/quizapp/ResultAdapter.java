package com.example.quizapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<QuizQuestion> quizQuestions;
    private final int INCORRECT = 2;
    private final int CORRECT = 1;

    public ResultAdapter(List<QuizQuestion> quizQuestions){
        this.quizQuestions = quizQuestions;
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView tvQuestion;
        TextView tvCorrectAnswer;
        TextView tvUserAnswer;

        public CustomViewHolder(View view){
            super(view);
            tvQuestion = view.findViewById(R.id.resultCard_tvQuestion);
            tvCorrectAnswer = view.findViewById(R.id.resultCard_tvCorrectAnswer);
            tvUserAnswer = view.findViewById(R.id.resultCard_tvUserAnswer);
        }

        public void setDetailsCorrect(QuizQuestion quizQuestion){
            setUniversalDetails(quizQuestion);
            tvCorrectAnswer.setVisibility(TextView.GONE);
            tvUserAnswer.setTextColor(Color.GREEN);
        }

        public void setDetailsIncorrect(QuizQuestion quizQuestion){
            setUniversalDetails(quizQuestion);
            tvCorrectAnswer.setText(quizQuestion.correctAnswer);
            tvCorrectAnswer.setTextColor(Color.GREEN);
            tvUserAnswer.setTextColor(Color.RED);
        }

        private void setUniversalDetails(QuizQuestion quizQuestion){
            tvQuestion.setText(quizQuestion.question);
            tvUserAnswer.setText(quizQuestion.userAnswer);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_result,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CustomViewHolder customHolder = (CustomViewHolder) holder;
        if(getItemViewType(position) == CORRECT){
            customHolder.setDetailsCorrect(quizQuestions.get(position));
        }
        else {
            customHolder.setDetailsIncorrect(quizQuestions.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return quizQuestions.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(quizQuestions.get(position).userAnswer.equals(
                quizQuestions.get(position).correctAnswer)
        ) return CORRECT;
        else return INCORRECT;
    }
}
