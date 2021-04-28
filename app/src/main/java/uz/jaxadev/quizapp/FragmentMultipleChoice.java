package uz.jaxadev.quizapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentMultipleChoice extends Fragment {

    private String question;
    private String[] choices;
    private List<String> rightAnswers;

    TextView questionText;

    CheckBox choiceFirst;
    CheckBox choiceSecond;
    CheckBox choiceThird;
    CheckBox choiceFourth;

    Button nextButton;

    int position;

    boolean isPassed = true;

    private QuestionsViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        question = getArguments().getString("question");
        choices = getArguments().getStringArray("choices");
        rightAnswers = Arrays.asList(getArguments().getStringArray("rightAnswers"));
        position = getArguments().getInt("position");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_multiplechoice, container, false);
        
        questionText = fragmentView.findViewById(R.id.multipleQuestionText);
        choiceFirst = fragmentView.findViewById(R.id.choiceFirst);
        choiceSecond = fragmentView.findViewById(R.id.choiceSecond);
        choiceThird = fragmentView.findViewById(R.id.choiceThird);
        choiceFourth = fragmentView.findViewById(R.id.choiceFourth);

        nextButton = fragmentView.findViewById(R.id.nextButton);

        questionText.setText(question);

        choiceFirst.setText(choices[0]);
        choiceSecond.setText(choices[1]);
        choiceThird.setText(choices[2]);
        choiceFourth.setText(choices[3]);

        viewModel = new ViewModelProvider(requireActivity()).get(QuestionsViewModel.class);

        if (viewModel.questionsScore.getValue().size() == position + 1) {
            Log.d("ans", "it is last item");
            nextButton.setText("Submit");

            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ArrayList<String> chosenAnswers = new ArrayList<>();

                    chosenAnswers.clear();

                    if (choiceFirst.isChecked()) {
                        chosenAnswers.add(choiceFirst.getText().toString());
                    }
                    if (choiceSecond.isChecked()) {
                        chosenAnswers.add(choiceSecond.getText().toString());
                    }
                    if (choiceThird.isChecked()) {
                        chosenAnswers.add(choiceThird.getText().toString());
                    }
                    if (choiceFourth.isChecked()) {
                        chosenAnswers.add(choiceFourth.getText().toString());
                    }

                    isPassed = chosenAnswers.containsAll(rightAnswers)
                            && chosenAnswers.size() == rightAnswers.size();


                    ArrayList<Boolean> questionsScore = viewModel.questionsScore.getValue();
                    questionsScore.set(position, isPassed);
                    viewModel.questionsScore.setValue(questionsScore);

                    Log.d("ans", String.format("isPassed: %b", isPassed));

                    int score = 0;

                    for (int i = 0; i < viewModel.questionsScore.getValue().size(); i++) {
                        if (viewModel.questionsScore.getValue().get(i)) {
                            score++;
                        }
                    }

                    Toast.makeText(requireActivity(), String.format("You have scored %d", score), Toast.LENGTH_SHORT).show();

                }
            });

        } else {
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<String> chosenAnswers = new ArrayList<>();

                    chosenAnswers.clear();

                    if (choiceFirst.isChecked()) {
                        chosenAnswers.add(choiceFirst.getText().toString());
                    }
                    if (choiceSecond.isChecked()) {
                        chosenAnswers.add(choiceSecond.getText().toString());
                    }
                    if (choiceThird.isChecked()) {
                        chosenAnswers.add(choiceThird.getText().toString());
                    }
                    if (choiceFourth.isChecked()) {
                        chosenAnswers.add(choiceFourth.getText().toString());
                    }

                    isPassed = chosenAnswers.containsAll(rightAnswers)
                            && chosenAnswers.size() == rightAnswers.size();


                    ArrayList<Boolean> questionsScore = viewModel.questionsScore.getValue();
                    questionsScore.set(position, isPassed);
                    viewModel.questionsScore.setValue(questionsScore);

                    Log.d("ans", String.format("isPassed: %b", isPassed));

                    viewModel.scrollNext();
                }
            });
        }


        return fragmentView;
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.d("state", "onPause");
        ArrayList<String> chosenAnswers = new ArrayList<>();

        chosenAnswers.clear();

        if (choiceFirst.isChecked()) {
            chosenAnswers.add(choiceFirst.getText().toString());
        }
        if (choiceSecond.isChecked()) {
            chosenAnswers.add(choiceSecond.getText().toString());
        }
        if (choiceThird.isChecked()) {
            chosenAnswers.add(choiceThird.getText().toString());
        }
        if (choiceFourth.isChecked()) {
            chosenAnswers.add(choiceFourth.getText().toString());
        }

        isPassed = chosenAnswers.containsAll(rightAnswers)
                && chosenAnswers.size() == rightAnswers.size();


        ArrayList<Boolean> questionsScore = viewModel.questionsScore.getValue();
        questionsScore.set(position, isPassed);
        viewModel.questionsScore.setValue(questionsScore);

        Log.d("ans", String.format("isPassed: %b", isPassed));
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("state", "onResume");
    }
}
