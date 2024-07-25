package com.example.wordle.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wordle.data.User;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
    }

    public void updateText(User user) {
        mText.setValue("Win Rounds: " + user.getWinRounds() + "\n"
                + "Total Rounds: " + user.getTotalRounds() + "\n"
                + "Minimum Guesses: " + user.getMinGuess());
    }

    public LiveData<String> getText() {
        return mText;
    }
}