package backend;

public class ScoreManager {
    private int score;
    private int highScore;

    public ScoreManager() {
        score = 0;
        highScore = 0; 
    }

    public int getScore() {
        return score;
    }

    public int getHighScore() {
        return highScore;
    }

    public void addScore(int points) {
        score += points;
        if (score > highScore) {
            highScore = score;
        }
    }

    public void resetScore() {
        score = 0;
    }

    public void saveHighScore() {
        System.out.println("High score saved: " + highScore);
    }

}
