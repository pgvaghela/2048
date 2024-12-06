package backend;

import java.util.Random;

public final class GameLogic {
    private final int[][] grid;
    private static final int SIZE = 4;
    private final Random random;
    private int score;

    public GameLogic() {
        grid = new int[SIZE][SIZE];
        random = new Random();
        reset(); // Initialize the game
    }

    public int[][] getGrid() {
        return grid;
    }

    public int getScore() {
        return score;
    }

    public boolean move(String direction) {
        int[][] previousState = copyGrid(grid); // Save the current grid state
        int previousScore = score; // Save the current score

        boolean moved = switch (direction) {
            case "UP" -> moveUp();
            case "DOWN" -> moveDown();
            case "LEFT" -> moveLeft();
            case "RIGHT" -> moveRight();
            default -> false;
        };

        if (moved && !isGridEqual(previousState, grid)) { // Check if any movement or merging happened
            addRandomTile(); // Add a new random tile only if a valid move occurred
            return true;
        } else {
            // Revert the score if no new tile is generated
            score = previousScore;
            return false;
        }
    }

    private boolean moveUp() {
        for (int col = 0; col < SIZE; col++) {
            moveColumn(col, -1); // Move each column upward
        }
        return true;
    }

    private boolean moveDown() {
        for (int col = 0; col < SIZE; col++) {
            moveColumn(col, 1); // Move each column downward
        }
        return true;
    }

    private boolean moveLeft() {
        for (int row = 0; row < SIZE; row++) {
            moveRow(row, -1); // Move each row left
        }
        return true;
    }

    private boolean moveRight() {
        for (int row = 0; row < SIZE; row++) {
            moveRow(row, 1); // Move each row right
        }
        return true;
    }

    private void moveColumn(int col, int direction) {
        int[] column = new int[SIZE];
        for (int row = 0; row < SIZE; row++) {
            column[row] = grid[row][col];
        }
        merge(column, direction);
        for (int row = 0; row < SIZE; row++) {
            grid[row][col] = column[row];
        }
    }

    private void moveRow(int row, int direction) {
        int[] line = grid[row];
        merge(line, direction);
        grid[row] = line;
    }

    private void merge(int[] line, int direction) {
        // Step 1: Slide non-zero tiles
        int[] temp = new int[SIZE];
        int index = direction == -1 ? 0 : SIZE - 1;
        int step = direction == -1 ? 1 : -1;

        for (int i = 0; i < SIZE; i++) {
            int value = line[i];
            if (value != 0) {
                temp[index] = value;
                index += step;
            }
        }

        // Step 2: Merge tiles with the same value
        index = direction == -1 ? 0 : SIZE - 1;
        step = direction == -1 ? 1 : -1;

        for (int i = 0; i < SIZE - 1; i++) {
            int current = temp[index];
            int next = temp[index + step];

            if (current != 0 && current == next) {
                temp[index] = current + next;
                score += temp[index]; // Update score
                temp[index + step] = 0; // Clear the merged tile
                index += step * 2; // Move index to skip the merged tile
                i++; // Skip the next tile since it has been merged
            } else {
                index += step;
            }
        }

        // Step 3: Slide again to fill gaps after merging
        int[] finalTemp = new int[SIZE];
        index = direction == -1 ? 0 : SIZE - 1;

        for (int i = 0; i < SIZE; i++) {
            int value = temp[i];
            if (value != 0) {
                finalTemp[index] = value;
                index += step;
            }
        }

        System.arraycopy(finalTemp, 0, line, 0, SIZE);
    }

    private int[][] copyGrid(int[][] source) {
        int[][] copy = new int[source.length][source[0].length];
        for (int i = 0; i < source.length; i++) {
            System.arraycopy(source[i], 0, copy[i], 0, source[i].length);
        }
        return copy;
    }

    private boolean isGridEqual(int[][] grid1, int[][] grid2) {
        for (int i = 0; i < grid1.length; i++) {
            for (int j = 0; j < grid1[i].length; j++) {
                if (grid1[i][j] != grid2[i][j]) {
                    return false; // Grids are not the same
                }
            }
        }
        return true; // Grids are identical
    }

    public void addRandomTile() {
        int emptyCount = 0;

        // Count empty tiles
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 0) emptyCount++;
            }
        }

        // If no empty tiles are available, return
        if (emptyCount == 0) return;

        // Select a random empty position
        int target = random.nextInt(emptyCount);
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 0) {
                    if (count == target) {
                        // Assign new tile value: 2 with 90% probability, 4 with 10% probability
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : grid) {
            for (int val : row) {
                sb.append(val).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void printGrid() {
        System.out.println(this.toString());
    }

    public void reset() {
        // Clear the grid
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = 0;
            }
        }

        // Reset score
        score = 0;

        // Add two random tiles to start the game
        addRandomTile();
        addRandomTile();
    }
}
