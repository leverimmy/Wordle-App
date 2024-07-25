package com.example.wordle.data;

public enum Color {
    GRAY, RED, YELLOW, GREEN;
    public static Color getColorByChar(char ch) {
        switch (ch) {
            case 'R': return RED;
            case 'Y': return YELLOW;
            case 'G': return GREEN;
            default: return GRAY;
        }
    }
}
