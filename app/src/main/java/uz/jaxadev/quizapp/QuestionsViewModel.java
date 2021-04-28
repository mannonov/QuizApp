package uz.jaxadev.quizapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class QuestionsViewModel extends ViewModel {

    public MutableLiveData<Integer> pageIndex = new MutableLiveData<>(0);

    public MutableLiveData<ArrayList<Boolean>> questionsScore = new MutableLiveData<>();

    public void initializeQuestionsScore() {

        ArrayList<Boolean> arrayList = new ArrayList<Boolean>();
        arrayList.add(true);
        arrayList.add(true);
        arrayList.add(true);
        arrayList.add(true);
        arrayList.add(true);

        questionsScore.postValue(arrayList);
    }

    public void scrollNext() {
        if (pageIndex.getValue() < 4) {
            pageIndex.setValue(pageIndex.getValue() + 1);
        }
    }

}
