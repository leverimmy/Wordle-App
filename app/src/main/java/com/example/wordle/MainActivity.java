package com.example.wordle;

import static com.example.wordle.utils.WordleGame.TOTAL_CHANCES;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.wordle.data.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.wordle.databinding.ActivityMainBinding;

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



        // Deal with Back button
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

    public User loadUser() {
        Log.d("preferenceTest", "read");
        SharedPreferences preferences_winRounds =
                getSharedPreferences("winRounds", Context.MODE_PRIVATE);
        SharedPreferences preferences_totalRounds =
                getSharedPreferences("totalRounds", Context.MODE_PRIVATE);
        SharedPreferences preferences_minGuess =
                getSharedPreferences("minGuess", Context.MODE_PRIVATE);

        int winRounds = preferences_winRounds.getInt("winRounds", 0);
        int totalRounds = preferences_totalRounds.getInt("totalRounds", 0);
        int minGuess = preferences_minGuess.getInt("minGuess", TOTAL_CHANCES);

        Log.d("preferenceTest", "Read [winRounds = " + winRounds + ", totalRounds = "
                + totalRounds + ", minGuess = " + minGuess + "]");
        return new User(winRounds, totalRounds, minGuess);
    }

    public void saveUser(User user) {
        Log.d("preferenceTest", "write");
        SharedPreferences preferences_winRounds =
                getSharedPreferences("winRounds", Context.MODE_PRIVATE);
        SharedPreferences preferences_totalRounds =
                getSharedPreferences("totalRounds", Context.MODE_PRIVATE);
        SharedPreferences preferences_minGuess =
               getSharedPreferences("minGuess", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor_winRounds = preferences_winRounds.edit();
        SharedPreferences.Editor editor_totalRounds = preferences_totalRounds.edit();
        SharedPreferences.Editor editor_minGuess = preferences_minGuess.edit();

        editor_winRounds.putInt("winRounds", user.getWinRounds()).apply();
        editor_totalRounds.putInt("totalRounds", user.getTotalRounds()).apply();
        editor_minGuess.putInt("minGuess", user.getMinGuess()).apply();

        Log.d("preferenceTest", "Write [winRounds = " + user.getWinRounds() +
                ", totalRounds = " + user.getTotalRounds() +
                ", minGuess = " + user.getMinGuess() + "]");
    }

}