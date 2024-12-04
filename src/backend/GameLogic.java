package backend;

import java.util.Random;

public final class GameLogic {
    private int[][] grid;
    private static final int SIZE = 4;
    private Random random;
    private int score;

    public GameLogic() {
        grid = new int[SIZE][SIZE];
        random = new Random();
        addRandomTile();
        addRandomTile();
        score = 0;
    }

    public int[][] getGrid() {
        return grid;
    }

    public int getScore() {
        return score;
    }

    public boolean move(String direction) {
        System.out.println("Before move (" + direction + "):");
        printGrid();
        boolean moved = switch (direction) {
            case "UP" -> moveUp();
            case "DOWN" -> moveDown();
            case "LEFT" -> moveLeft();
            case "RIGHT" -> moveRight();
            default -> false;
        };
        if (moved) {
            addRandomTile();
            System.out.println("After move (" + direction + "):");
            printGrid();
        }
        return moved;
    }

    private void printGrid() {
        for (int[] row : grid) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private boolean moveUp() {
        return slideAndMerge(0, 1, 0, SIZE);
    }

    private boolean moveDown() {
        return slideAndMerge(SIZE - 1, -1, 0, SIZE);
    }

    private boolean moveLeft() {
        return slideAndMerge(0, SIZE, 0, 1);
    }

    private boolean moveRight() {
        return slideAndMerge(0, SIZE, SIZE - 1, -1);
    }

    private boolean slideAndMerge(int rowStart, int rowInc, int colStart, int colInc) {
        boolean changed = false;
        for (int i = 0; i < SIZE; i++) { // Iterate over rows/columns
            int[] line = new int[SIZE];
            int index = 0;
    
            // Extract line from grid
            for (int j = 0; j < SIZE; j++) {
                int row = rowStart + (rowInc == 0 ? i : j); // Ensure row index is valid
                int col = colStart + (colInc == 0 ? i : j); // Ensure col index is valid
                if (row >= 0 && row < SIZE && col >= 0 && col < SIZE) {
                    if (grid[row][col] != 0) {
                        line[index++] = grid[row][col];
                    }
                }
            }
    
            // Merge tiles
            index = 0;
            int[] mergedLine = new int[SIZE];
            for (int j = 0; j < SIZE; j++) {
                if (line[j] == 0) break;
                if (j < SIZE - 1 && line[j] == line[j + 1]) {
                    mergedLine[index++] = line[j] * 2;
                    score += line[j] * 2;
                    j++; // Skip next tile
                } else {
                    mergedLine[index++] = line[j];
                }
            }
    
            // Update the grid
            index = 0;
            for (int j = 0; j < SIZE; j++) {
                int row = rowStart + (rowInc == 0 ? i : j);
                int col = colStart + (colInc == 0 ? i : j);
                if (row >= 0 && row < SIZE && col >= 0 && col < SIZE) {
                    if (grid[row][col] != mergedLine[index]) {
                        grid[row][col] = mergedLine[index++];
                        changed = true;
                    } else {
                        index++;
                    }
                }
            }
        }
        return changed;
    }    

    public void addRandomTile() {
        int emptyCount = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 0) emptyCount++;
            }
        }
        if (emptyCount == 0) return;
        int target = random.nextInt(emptyCount);
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 0) {
                    if (count == target) {
                        grid[i][j] = random.nextInt(10) < 9 ? 2 : 4;
                        return;
                    }
                    count++;
                }
            }
        }
    }

    public boolean isGameOver() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 0) return false;
                if (i > 0 && grid[i][j] == grid[i - 1][j]) return false;
                if (j > 0 && grid[i][j] == grid[i][j - 1]) return false;
            }
        }
        return true;
    }
}