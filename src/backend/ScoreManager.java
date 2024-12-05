package backend;

public class ScoreManager {
    private int score;
    private int highScore;

    public ScoreManager() {
        score = 0;
        highScore = 0; // Optionally load from file
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
        // Placeholder for file saving logic
        System.out.println("High score saved: " + highScore);
    }

    public void loadHighScore() {
        // Placeholder for file loading logic
        // e.g., highScore = loadFromFile();
        System.out.println("High score loaded: " + highScore);
    }

    public void displayScore() {
        System.out.println("Current Score: " + score);
        System.out.println("High Score: " + highScore);
    }
}
