package uz.jaxadev.quizapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

public class FragmentTextEntryQuestion extends Fragment {

    private String question;
    private String rightAnswer;

    TextView questionText;

    Button nextButton;
    EditText authorNameEditText;

    int position;

    boolean isPassed = true;

    private QuestionsViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        question = getArguments().getString("question");
        rightAnswer = getArguments().getString("rightAnswer");
        position = getArguments().getInt("position");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View fragmentView = inflater.inflate(R.layout.fragment_text_entry_question, container, false);

        questionText = fragmentView.findViewById(R.id.questionTextEntry);
        authorNameEditText = fragmentView.findViewById(R.id.authorNameEditText);

        nextButton = fragmentView.findViewById(R.id.nextButtonTextEntry);

        viewModel = new ViewModelProvider(requireActivity()).get(QuestionsViewModel.class);

        questionText.setText(question);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String submittedAnswer = authorNameEditText.getText().toString();

                isPassed = rightAnswer.equals(submittedAnswer);
                ArrayList<Boolean> questionsScore = viewModel.questionsScore.getValue();
                questionsScore.set(position, isPassed);
                viewModel.questionsScore.setValue(questionsScore);

                Log.d("ans", String.format("isPassed: %b", isPassed));

                viewModel.scrollNext();


            }
        });

        return fragmentView;
    }

    @Override
    public void onPause() {
        super.onPause();

        String submittedAnswer = authorNameEditText.getText().toString();

        isPassed = rightAnswer.equals(submittedAnswer);
        ArrayList<Boolean> questionsScore = viewModel.questionsScore.getValue();
        questionsScore.set(position, isPassed);
        viewModel.questionsScore.setValue(questionsScore);

        Log.d("ans", String.format("isPassed: %b", isPassed));

    }
}
