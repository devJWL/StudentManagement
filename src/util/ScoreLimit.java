package util;

public enum ScoreLimit {
    SCORE_LIMIT_ERROR(-1),
    SCORE_LIMIT_MIN(0),
    SCORE_LIMIT_MAX(100);

    private final int score;
    ScoreLimit(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }
}
