package com.ddoddo.balllog.balllog.model;

public enum MatchResult {
    WIN, LOSE;

    public static MatchResult fromScores(int scoreCheering, int scoreOpposing) {
        return (scoreCheering > scoreOpposing) ? WIN : LOSE;
    }
}
