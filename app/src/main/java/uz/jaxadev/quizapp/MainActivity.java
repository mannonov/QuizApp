package uz.jaxadev.quizapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 questionsPager;

    private FragmentStateAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final QuestionsViewModel viewModel = new ViewModelProvider(this).get(QuestionsViewModel.class);
        viewModel.initializeQuestionsScore();

        questionsPager = findViewById(R.id.viewPager);
        pagerAdapter = new QuestionsAdapter(this);
        questionsPager.setAdapter(pagerAdapter);

        questionsPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);

            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                Log.d("scrolll", String.format("Gesture scrolled to position: %d", position));

                if (position != viewModel.pageIndex.getValue()) {
                    viewModel.pageIndex.setValue(position);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });


        viewModel.pageIndex.observe(this, new androidx.lifecycle.Observer<Integer>() {
            @Override
            public void onChanged(Integer pageIndex) {
                if (pageIndex != questionsPager.getScrollState()) {
                    Log.d("scrolll", String.format("pressed next position: %d", pageIndex));
                    questionsPager.setCurrentItem(pageIndex, true);
                }
            }
        });

    }
}