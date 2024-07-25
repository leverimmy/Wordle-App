package com.example.wordle.data;

import java.util.Arrays;
import java.util.Objects;

import static com.example.wordle.utils.WordleGame.*;

public class State {
    public Color[] wordState;                 // The guess state of word
    public Color[] alphabetState;             // The guess state of the alphabet
    public int chancesLeft;                   // The chances left
    public String answer;
    public String word;               // The final answer, and the current guess word
    public GameStatus status;                 // The current game status

    public State() {
        wordState = new Color[WORD_LENGTH];
        Arrays.fill(wordState, Color.GRAY);
        alphabetState = new Color[ALPHABET_SIZE];
        Arrays.fill(alphabetState, Color.GRAY);
        chancesLeft = TOTAL_CHANCES;
        this.answer = WordSet.randomAnswer();
        word = "";
        status = GameStatus.RUNNING;
    }
    /*
    public State(JSONObject json) {
        this.wordState = new Color[WORD_LENGTH];
        String word_state_string = json.getString("word_state");
        for (int i = 0; i < WORD_LENGTH; i++) {
            this.wordState[i] = Color.getColorByChar(word_state_string.charAt(i));
        }
        this.alphabetState = new Color[ALPHABET_SIZE];
        String alphabet_state_string = json.getString("alphabet_state");
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            this.alphabetState[i] = Color.getColorByChar(alphabet_state_string.charAt(i));
        }
        this.chancesLeft = json.getInt("chance_left");
        this.answer = json.getString("answer").toUpperCase();
        this.word = json.getString("word").toUpperCase();
        this.status = GameStatus.valueOf(json.getString("status"));
    }
    */
    @Override
    public String toString() {
        return Arrays.toString(wordState) + "$" + Arrays.toString(alphabetState) + "$"
                + chancesLeft + "$" + answer + "$" + word + "$" + status;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(this.toString(), o.toString());
    }
    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(wordState),
                Arrays.hashCode(alphabetState), chancesLeft, answer, word, status);
    }

    public void clear() {
        Arrays.fill(wordState, Color.GRAY);
        Arrays.fill(alphabetState, Color.GRAY);
        chancesLeft = TOTAL_CHANCES;
        this.answer = WordSet.randomAnswer();
        word = "";
        status = GameStatus.RUNNING;
    }
}
