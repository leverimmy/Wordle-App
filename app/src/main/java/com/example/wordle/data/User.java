package com.example.wordle.data;

public class User {

    private int winRounds;
    private int totalRounds;
    private int minGuess;

    public User(int winRounds, int totalRounds, int minGuess) {
        this.winRounds = winRounds;
        this.totalRounds = totalRounds;
        this.minGuess = minGuess;
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

    public void setValues(int winRounds, int totalRounds, int minGuess) {
        this.winRounds = winRounds;
        this.totalRounds = totalRounds;
        this.minGuess = minGuess;
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

}