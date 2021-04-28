package uz.jaxadev.quizapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class QuestionsAdapter extends FragmentStateAdapter {

    public QuestionsAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0 : {

                String[] choices = {"Anna Karenina", "Master and Margarita", "War and Peace", "Martin Eden"};
                String[] rightAnswers = {"War and Peace", "Anna Karenina"};

                FragmentMultipleChoice fragmentMultipleChoice = new FragmentMultipleChoice();
                Bundle args = new Bundle();
                args.putString("question", "Choose novels of Leo Tolstoy");
                args.putStringArray("choices", choices);
                args.putStringArray("rightAnswers", rightAnswers);
                args.putInt("position", position);
                fragmentMultipleChoice.setArguments(args);
                return fragmentMultipleChoice;
            }
            case 1: {

                String[] choices = {"Crime and Punishment",  "Animal farm", "Master and Margarita", "Atlas Shrugged"};
                String rightAnswer = "Master and Margarita";


                FragmentSingleChoice fragmentSingleChoice = new FragmentSingleChoice();


                Bundle args = new Bundle();
                args.putString("question", "Choose a novel of Mikhail Bulgakov");
                args.putStringArray("choices", choices);
                args.putString("rightAnswer", rightAnswer);
                args.putInt("position", position);
                fragmentSingleChoice.setArguments(args);

                return fragmentSingleChoice;
            }
            case 2: {

                String question = "Who was written by Martin Eden";
                String rightAnswer = "Jack London";


                FragmentTextEntryQuestion fragmentTextEntryQuestion = new FragmentTextEntryQuestion();

                Bundle args = new Bundle();
                args.putString("question", question);
                args.putString("rightAnswer", rightAnswer);
                args.putInt("position", position);
                fragmentTextEntryQuestion.setArguments(args);

                return fragmentTextEntryQuestion;
            }
            case 3: {

                String[] choices = {"The Count of Monte Cristo",  "Lolita", "The Kite runner", "Old man and the Sea"};
                String rightAnswer = "Lolita";


                FragmentSingleChoice fragmentSingleChoice = new FragmentSingleChoice();


                Bundle args = new Bundle();
                args.putString("question", "Choose a novel of Vladimir Nabokov");
                args.putStringArray("choices", choices);
                args.putString("rightAnswer", rightAnswer);
                args.putInt("position", position);
                fragmentSingleChoice.setArguments(args);

                return fragmentSingleChoice;
            }
            case 4: {

                String[] choices = {"The idiot",  "The Myth of Sisyphus", "Demons", "The Stranger"};
                String[] rightAnswers = {"The Myth of Sisyphus", "The Stranger"};

                FragmentMultipleChoice fragmentMultipleChoice = new FragmentMultipleChoice();

                Bundle args = new Bundle();
                args.putString("question", "Choose a novel of Albert Camus");
                args.putStringArray("choices", choices);
                args.putStringArray("rightAnswers", rightAnswers);
                args.putInt("position", position);
                fragmentMultipleChoice.setArguments(args);

                return fragmentMultipleChoice;
            }
            default: {
                return null;
            }
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
