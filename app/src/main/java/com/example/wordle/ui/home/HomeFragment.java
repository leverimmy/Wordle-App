package com.example.wordle.ui.home;

import static com.example.wordle.utils.WordleGame.TOTAL_CHANCES;
import static com.example.wordle.utils.WordleGame.WORD_LENGTH;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.wordle.R;
import com.example.wordle.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Fill GridLayout
        GridLayout inputLayout = binding.InputLayout;

        inputLayout.setRowCount(TOTAL_CHANCES);
        inputLayout.setColumnCount(WORD_LENGTH);
        for (int row = 0; row < inputLayout.getRowCount(); row++) {
            for (int col = 0; col < inputLayout.getColumnCount(); col++) {
                TextView textView = new TextView(getContext());
                textView.setText("A");
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(50); // 设置文本大小

                textView.setBackgroundResource(R.drawable.green_border);
//                textView.setBackgroundColor(Color.GRAY);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                // 设置 TextView 占据一个整列
                params.columnSpec = GridLayout.spec(col, 1);
                // 设置 TextView 占据一个整行
                params.rowSpec = GridLayout.spec(row, 1);

                int screenWidth = getResources().getDisplayMetrics().widthPixels;

                params.width = screenWidth / inputLayout.getColumnCount();
                params.height = params.width;

                // 将 TextView 添加到 GridLayout
                inputLayout.addView(textView, params);
            }
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}