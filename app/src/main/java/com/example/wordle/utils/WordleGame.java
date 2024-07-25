package com.example.wordle.utils;

import com.example.wordle.data.*;

import java.util.*;

public class WordleGame {
    public static final int ALPHABET_SIZE = 26;            // The size of the alphabet
    public static final int WORD_LENGTH = 5;               // The length of words
    public static final int TOTAL_CHANCES = 6;             // The chances in total

    // Guess `word` at state `s`
    public static State guess(State s) {
        Arrays.fill(s.wordState, Color.GRAY);
        int[] guessAlphabetCount = new int[ALPHABET_SIZE];
        int[] answerAlphabetCount = new int[ALPHABET_SIZE];

        for (int i = 0; i < WORD_LENGTH; i++) {
            int answer_index = s.answer.charAt(i) - 'A';
            answerAlphabetCount[answer_index]++;
        }

        for (int i = 0; i < WORD_LENGTH; i++) {
            if (s.word.charAt(i) == s.answer.charAt(i)) {
                int word_index = s.word.charAt(i) - 'A';
                guessAlphabetCount[word_index]++;
            }
        }

        for (int i = 0; i < WORD_LENGTH; i++) {
            int word_index = s.word.charAt(i) - 'A';
            if (s.word.charAt(i) == s.answer.charAt(i)) {
                s.wordState[i] = Color.GREEN;
                s.alphabetState[word_index] = Color.GREEN;
            } else {
                if (s.answer.contains(String.valueOf(s.word.charAt(i)))
                        && guessAlphabetCount[word_index] < answerAlphabetCount[word_index]) {
                    s.wordState[i] = Color.YELLOW;
                } else {
                    s.wordState[i] = Color.RED;
                }
                if (s.alphabetState[word_index] == Color.GRAY || s.alphabetState[word_index] == Color.RED) {
                    if (s.answer.contains(String.valueOf(s.word.charAt(i)))) {
                        s.alphabetState[word_index] = Color.YELLOW;
                    } else {
                        s.alphabetState[word_index] = Color.RED;
                    }
                }
                guessAlphabetCount[word_index]++;
            }
        }

        if (Objects.equals(s.word, s.answer)) {
            s.status = GameStatus.WON;
        } else {
            if (s.chancesLeft == 1) {
                s.status = GameStatus.LOST;
            }
            s.chancesLeft--;
        }
        return s;
    }
}
