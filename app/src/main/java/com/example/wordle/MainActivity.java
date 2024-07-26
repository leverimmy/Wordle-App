package com.example.wordle;

import static com.example.wordle.utils.WordleGame.TOTAL_CHANCES;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.wordle.data.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.wordle.databinding.ActivityMainBinding;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private long firstTime = 0;

    private OnBackPressedCallback onBackPressedCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        // 处理返回键
        onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime >= 2000) {
                    Toast.makeText(MainActivity.this, "再按一次返回键退出", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;
                } else {
                    finish();
                }
            }
        };
        OnBackPressedDispatcher dispatcher = getOnBackPressedDispatcher();
        dispatcher.addCallback(this, onBackPressedCallback);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onBackPressedCallback.remove();
    }

    // 读取用户数据
    public User loadUser() {
        Log.d("preferenceTest", "read");
        SharedPreferences preferences_winRounds =
                getSharedPreferences("winRounds", MODE_PRIVATE);
        SharedPreferences preferences_totalRounds =
                getSharedPreferences("totalRounds", MODE_PRIVATE);
        SharedPreferences preferences_guesses =
                getSharedPreferences("guesses", MODE_PRIVATE);

        int winRounds = preferences_winRounds.getInt("winRounds", 0);
        int totalRounds = preferences_totalRounds.getInt("totalRounds", 0);
        int[] guesses = new int[6];
        for (int i = 0; i < TOTAL_CHANCES; i++)
            guesses[i] = preferences_guesses.getInt("guesses[" + i + "]", 0);

        Log.d("preferenceTest", "Read [winRounds = " + winRounds +
                ", totalRounds = " + totalRounds +
                ", guesses = " + Arrays.toString(guesses) + "]");
        return new User(winRounds, totalRounds, guesses);
    }

    // 存用户数据
    public void saveUser(User user) {
        Log.d("preferenceTest", "write");
        SharedPreferences preferences_winRounds =
                getSharedPreferences("winRounds", MODE_PRIVATE);
        SharedPreferences preferences_totalRounds =
                getSharedPreferences("totalRounds", MODE_PRIVATE);
        SharedPreferences preferences_guesses =
               getSharedPreferences("guesses", MODE_PRIVATE);

        SharedPreferences.Editor editor_winRounds = preferences_winRounds.edit();
        SharedPreferences.Editor editor_totalRounds = preferences_totalRounds.edit();
        SharedPreferences.Editor editor_guesses = preferences_guesses.edit();

        editor_winRounds.putInt("winRounds", user.winRounds).apply();
        editor_totalRounds.putInt("totalRounds", user.totalRounds).apply();
        for (int i = 0; i < TOTAL_CHANCES; i++)
            editor_guesses.putInt("guesses[" + i + "]", user.guesses[i]).apply();

        Log.d("preferenceTest", "Write [winRounds = " + user.winRounds +
                ", totalRounds = " + user.totalRounds +
                ", guesses = " + Arrays.toString(user.guesses) + "]");
    }

}