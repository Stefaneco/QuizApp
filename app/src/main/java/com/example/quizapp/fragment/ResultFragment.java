package com.example.quizapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.quizapp.QuizViewModel;
import com.example.quizapp.R;
import com.example.quizapp.ResultAdapter;

public class ResultFragment extends Fragment {

    QuizViewModel viewModel = null;
    ResultAdapter resultAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(QuizViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        Button btEnd = view.findViewById(R.id.resultFragment_btEnd);
        RecyclerView rvResults = view.findViewById(R.id.resultFragment_rvResults);
        rvResults.setLayoutManager(new LinearLayoutManager(requireActivity()));
        resultAdapter = new ResultAdapter(viewModel.getQuestions());
        rvResults.setAdapter(resultAdapter);

        btEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_resultFragment_to_fileFragment);
            }
        });
    }
}