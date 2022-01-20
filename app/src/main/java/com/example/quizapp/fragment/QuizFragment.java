package com.example.quizapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quizapp.QuizViewModel;
import com.example.quizapp.R;

public class QuizFragment extends Fragment {

    private QuizViewModel viewModel = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(QuizViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        Button btSubmit = view.findViewById(R.id.quizFragment_btSubmit);
        TextView tvQuestion = view.findViewById(R.id.quizFragment_tvQuestion);
        EditText etAnswer = view.findViewById(R.id.quizFragment_etAnswer);

        tvQuestion.setText(viewModel.getNextQuestion());

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.setAnswer(etAnswer.getText().toString());
                if(viewModel.moreQuestions(getContext())){
                    tvQuestion.setText(viewModel.getNextQuestion());
                    etAnswer.setText("");
                }
                else
                    navController.navigate(R.id.action_quizFragment_to_resultFragment);
            }
        });
    }
}