package com.example.wordle.data;

import static com.example.wordle.utils.WordleGame.TOTAL_CHANCES;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class User {
    private final String name = "DemoUser";

    private int winRounds;
    private int totalRounds;
    private int minGuess;

    private final Context context;

    public User(Context context) {
        this.context = context;
        readDataFromPreference();
    }

    void readDataFromPreference() {
        Log.d("preferenceTest", "read");
        SharedPreferences preferences_winRounds =
                context.getSharedPreferences("winRounds", Context.MODE_PRIVATE);
        SharedPreferences preferences_totalRounds =
                context.getSharedPreferences("totalRounds", Context.MODE_PRIVATE);
        SharedPreferences preferences_minGuess =
                context.getSharedPreferences("minGuess", Context.MODE_PRIVATE);

        winRounds = preferences_winRounds.getInt("winRounds", 0);
        totalRounds = preferences_totalRounds.getInt("totalRounds", 0);
        minGuess = preferences_minGuess.getInt("minGuess", TOTAL_CHANCES);

        Log.d("preferenceTest", "Read [winRounds = " + winRounds + ", totalRounds = "
                + totalRounds + ", minGuess = " + minGuess + "]");
    }

    void writeDataToPreference() {
        Log.d("preferenceTest", "write");
        SharedPreferences preferences_winRounds =
                context.getSharedPreferences("winRounds", Context.MODE_PRIVATE);
        SharedPreferences preferences_totalRounds =
                context.getSharedPreferences("totalRounds", Context.MODE_PRIVATE);
        SharedPreferences preferences_minGuess =
                context.getSharedPreferences("minGuess", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor_winRounds = preferences_winRounds.edit();
        SharedPreferences.Editor editor_totalRounds = preferences_totalRounds.edit();
        SharedPreferences.Editor editor_minGuess = preferences_minGuess.edit();

        editor_winRounds.putInt("winRounds", winRounds).apply();
        editor_totalRounds.putInt("totalRounds", totalRounds).apply();
        editor_minGuess.putInt("minGuess", minGuess).apply();
    }

    public int getWinRounds() {
        return winRounds;
    }

    public int getTotalRounds() {
        return totalRounds;
    }

    public int getMinGuess() {
        return minGuess;
    }

    public void setWinRounds(int winRounds) {
        this.winRounds = winRounds;
    }

    public void setTotalRounds(int totalRounds) {
        this.totalRounds = totalRounds;
    }

    public void setMinGuess(int minGuess) {
        this.minGuess = minGuess;
    }

    protected void finalize() {
        writeDataToPreference();
    }
}