package uz.jaxadev.quizapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

public class FragmentSingleChoice extends Fragment {

    private String question;
    private String[] choices;
    private String rightAnswer;

    TextView questionText;

    RadioGroup radioGroup;

    RadioButton radioButtonFirst;
    RadioButton radioButtonSecond;
    RadioButton radioButtonThird;
    RadioButton radioButtonFourth;

    Button nextButton;

    int position;

    boolean isPassed = true;

    private QuestionsViewModel viewModel;
    private String chosenAnswer = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        question = getArguments().getString("question");
        choices = getArguments().getStringArray("choices");
        rightAnswer = getArguments().getString("rightAnswer");
        position = getArguments().getInt("position");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View fragmentView = inflater.inflate(R.layout.fragment_singlechoice, container, false);

        questionText = fragmentView.findViewById(R.id.singleQuestionText);

        radioGroup = fragmentView.findViewById(R.id.radioGroup);

        radioButtonFirst = fragmentView.findViewById(R.id.radioButtonFirst);
        radioButtonSecond = fragmentView.findViewById(R.id.radioButtonSecond);
        radioButtonThird = fragmentView.findViewById(R.id.radioButtonThird);
        radioButtonFourth = fragmentView.findViewById(R.id.radioButtonFourth);

        nextButton = fragmentView.findViewById(R.id.nextButtonSingle);

        questionText.setText(question);



        radioButtonFirst.setText(choices[0]);
        radioButtonSecond.setText(choices[1]);
        radioButtonThird.setText(choices[2]);
        radioButtonFourth.setText(choices[3]);

        viewModel = new ViewModelProvider(requireActivity()).get(QuestionsViewModel.class);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedRadioButton = fragmentView.findViewById(checkedId);
                chosenAnswer = selectedRadioButton.getText().toString();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (chosenAnswer.isEmpty()) {
                    isPassed = false;
                } else {
                    isPassed = chosenAnswer.equals(rightAnswer);
                }

                ArrayList<Boolean> questionsScore = viewModel.questionsScore.getValue();
                questionsScore.set(position, isPassed);
                viewModel.questionsScore.setValue(questionsScore);

                Log.d("ans", String.format("isPassed: %b", isPassed));


                viewModel.scrollNext();


            }
        });

        return  fragmentView;
    }

    @Override
    public void onPause() {
        super.onPause();

        if (chosenAnswer.isEmpty()) {
            isPassed = false;
        } else {
            isPassed = rightAnswer.equals(chosenAnswer);
        }

        ArrayList<Boolean> questionsScore = viewModel.questionsScore.getValue();
        questionsScore.set(position, isPassed);
        viewModel.questionsScore.setValue(questionsScore);

        Log.d("ans", String.format("isPassed: %b", isPassed));

    }
}
