package com.example.wordle.data;

public class User {

    public int winRounds;
    public int totalRounds;
    public int[] guesses;

    public User(int winRounds, int totalRounds, int[] guesses) {
        this.winRounds = winRounds;
        this.totalRounds = totalRounds;
        this.guesses = guesses;
    }

}