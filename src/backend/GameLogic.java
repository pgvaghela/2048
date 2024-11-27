package backend;

import java.util.Random;

public class GameLogic {
    private int[][] grid;
    private static final int SIZE = 4;
    private Random random;

    public GameLogic() {
        grid = new int[SIZE][SIZE];
        random = new Random();
        addRandomTile();
        addRandomTile();
    }

    public int[][] getGrid() {
        return grid;
    }

    public boolean move(String direction) {
        // Handle moves (up, down, left, right).
        // Return true if the grid changed; otherwise, false.
        return false;
    }

    public void addRandomTile() {
        // Add a new tile (2 or 4) to a random empty spot.
    }

    public boolean isGameOver() {
        // Check if no moves are possible.
        return false;
    }
}
