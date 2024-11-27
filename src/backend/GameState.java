package backend;

import java.io.Serializable;

public class GameState implements Serializable {
    private int[][] grid;
    private int score;

    public GameState(int[][] grid, int score) {
        this.grid = grid;
        this.score = score;
    }

    public int[][] getGrid() {
        return grid;
    }

    public int getScore() {
        return score;
    }
}
