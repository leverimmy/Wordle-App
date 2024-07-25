package com.example.wordle.ui.home;

import static com.example.wordle.utils.WordleGame.TOTAL_CHANCES;
import static com.example.wordle.utils.WordleGame.WORD_LENGTH;
import static com.example.wordle.utils.WordleGame.guess;

import static java.lang.Math.min;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.wordle.MainActivity;
import com.example.wordle.R;
import com.example.wordle.data.State;
import com.example.wordle.data.User;
import com.example.wordle.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private StringBuffer guess = new StringBuffer();
    private State state;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // 完成 inputLayout
        GridLayout inputLayout = binding.InputLayout;

        inputLayout.setRowCount(TOTAL_CHANCES);
        inputLayout.setColumnCount(WORD_LENGTH);
        for (int row = 0; row < inputLayout.getRowCount(); row++) {
            for (int col = 0; col < inputLayout.getColumnCount(); col++) {
                TextView textView = new TextView(getContext());
                textView.setText(" ");
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(50); // 设置文本大小
                textView.setBackgroundResource(R.drawable.gray_border); // 设置背景，默认为灰色
                // 设置参数
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.columnSpec = GridLayout.spec(col, 1); // 设置 TextView 占据一个整列
                params.rowSpec = GridLayout.spec(row, 1); // 设置 TextView 占据一个整行

                int screenWidth = getResources().getDisplayMetrics().widthPixels;
                params.width = screenWidth / inputLayout.getColumnCount();
                params.height = params.width;

                // 将 TextView 添加到 GridLayout
                inputLayout.addView(textView, params);
            }
        }

        // 完成 keyboardLayout
        TableLayout keyboardLayout = binding.KeyboardLayout;
        int childCount = keyboardLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = keyboardLayout.getChildAt(i);
            if (child instanceof LinearLayout) {
                LinearLayout linearLayout = (LinearLayout) child;
                int buttonCount = linearLayout.getChildCount();
                for (int j = 0; j < buttonCount; j++) {
                    View buttonView = linearLayout.getChildAt(j);
                    if (buttonView instanceof Button) {
                        Button button = (Button) buttonView;
                        button.setOnClickListener(v -> handleButtonPress((Button) v));
                    }
                }
            }
        }
        return root;
    }

    private void handleButtonPress(Button button) {
        // 根据按钮的 ID 或文本执行不同的逻辑
        int buttonId = button.getId();
        if (buttonId == R.id.button_A) {
            processInput('A');
        } else if (buttonId == R.id.button_B) {
            processInput('B');
        } else if (buttonId == R.id.button_C) {
            processInput('C');
        } else if (buttonId == R.id.button_D) {
            processInput('D');
        } else if (buttonId == R.id.button_E) {
            processInput('E');
        } else if (buttonId == R.id.button_F) {
            processInput('F');
        } else if (buttonId == R.id.button_G) {
            processInput('G');
        } else if (buttonId == R.id.button_H) {
            processInput('H');
        } else if (buttonId == R.id.button_I) {
            processInput('I');
        } else if (buttonId == R.id.button_J) {
            processInput('J');
        } else if (buttonId == R.id.button_K) {
            processInput('K');
        } else if (buttonId == R.id.button_L) {
            processInput('L');
        } else if (buttonId == R.id.button_M) {
            processInput('M');
        } else if (buttonId == R.id.button_N) {
            processInput('N');
        } else if (buttonId == R.id.button_O) {
            processInput('O');
        } else if (buttonId == R.id.button_P) {
            processInput('P');
        } else if (buttonId == R.id.button_Q) {
            processInput('Q');
        } else if (buttonId == R.id.button_R) {
            processInput('R');
        } else if (buttonId == R.id.button_S) {
            processInput('S');
        } else if (buttonId == R.id.button_T) {
            processInput('T');
        } else if (buttonId == R.id.button_U) {
            processInput('U');
        } else if (buttonId == R.id.button_V) {
            processInput('V');
        } else if (buttonId == R.id.button_W) {
            processInput('W');
        } else if (buttonId == R.id.button_X) {
            processInput('X');
        } else if (buttonId == R.id.button_Y) {
            processInput('Y');
        } else if (buttonId == R.id.button_Z) {
            processInput('Z');
        } else if (buttonId == R.id.button_BACKSPACE) {
            processBackspace();
        } else if (buttonId == R.id.button_NEW_GAME) {
            processNewGame();
        } else if (buttonId == R.id.button_ENTER) {
            processEnter();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    void processInput(char ch) {
        if (guess.length() < WORD_LENGTH) {
            guess.append(ch);
        }
        displayWord();
    }

    void processBackspace() {
        if (guess.length() > 0) {
            guess.deleteCharAt(guess.length() - 1);
        }
        displayWord();
    }

    void processNewGame() {
        state.clear();
        // TODO: 清理所有 input
        displayState();
    }

    void processEnter() {
        state.word = String.valueOf(guess);
        state = guess(state);
        displayState();

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        User user = ((MainActivity)getActivity()).loadUser();
        switch (state.status) {
            case WON:
                dialogBuilder.setTitle("胜利");
                dialogBuilder.setMessage("你赢了！");
                dialogBuilder.setPositiveButton("再来一局", (dialogInterface, i) -> {
                    // 重新开始游戏
                    processNewGame();
                });
                dialogBuilder.create().show();
                user.setWinRounds(user.getWinRounds() + 1);
                user.setTotalRounds(user.getTotalRounds() + 1);
                user.setMinGuess(min(user.getMinGuess(), TOTAL_CHANCES - state.chancesLeft));
                break;
            case LOST:
                dialogBuilder.setTitle("失败");
                dialogBuilder.setMessage("你输了！答案为 [" + state.answer + "]。");
                dialogBuilder.setPositiveButton("再来一局", (dialogInterface, i) -> {
                    // 重新开始游戏
                    processNewGame();
                });
                dialogBuilder.create().show();
                user.setTotalRounds(user.getTotalRounds() + 1);
                break;
            default:
                break;
        }

    }

    void displayWord() {
        // TODO: 在对应行显示 guess
    }

    void displayState() {
        // TODO: 根据 state 更改按钮、当前行 guess 字母的颜色
    }
}