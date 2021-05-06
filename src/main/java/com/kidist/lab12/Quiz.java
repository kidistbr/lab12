package com.kidist.lab12;

public class Quiz {
    private int score;
    private int current;
    private final String[] questions = {
            "3,1,4,1,5",
            "1,1,2,3,5",
            "1,4,9,16,25",
            "2,3,5,7,11",
            "1,2,4,8,16",
    };
    private final int[] answers = {9,8,36,13,32};

    public Quiz() {
        this.score = 0;
        this.current = 0;
    }

    public void next(int ans, int i) {
        score += ans == answers[i] ? 1 : 0;
        current++;
    }

    public int getCurrent() {
        return current;
    }

    public int getScore() {
        return score;
    }

    public String getQuestion(int i) {
        return questions[i];
    }
}
