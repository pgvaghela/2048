package backend;

import java.io.Serializable;

public class GameState implements Serializable {
    private final int[][] grid;
    private final int score;

    public GameState(int[][] grid, int score) {
        this.grid = copyGrid(grid);
        this.score = score;
    }

    public int[][] getGrid() {
        return copyGrid(grid);
    }

    public int getScore() {
        return score;
    }

    private int[][] copyGrid(int[][] source) {
        int[][] copy = new int[source.length][source[0].length];
        for (int i = 0; i < source.length; i++) {
            System.arraycopy(source[i], 0, copy[i], 0, source[i].length);
        }
        return copy;
    }
}
