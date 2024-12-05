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

    public void up() {
        for (int col = 0; col < SIZE; col++) {
            moveColumn(col, -1);
        }
        finalizeMove();
    }

    public void down() {
        for (int col = 0; col < SIZE; col++) {
            moveColumn(col, 1);
        }
        finalizeMove();
    }

    public void left() {
        for (int row = 0; row < SIZE; row++) {
            moveRow(row, -1);
        }
        finalizeMove();
    }

    public void right() {
        for (int row = 0; row < SIZE; row++) {
            moveRow(row, 1);
        }
        finalizeMove();
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
        int[] temp = new int[SIZE];
        int index = direction == -1 ? 0 : SIZE - 1;
        int step = direction == -1 ? 1 : -1;

        for (int i = 0; i < SIZE; i++) {
            int value = line[i];
            if (value == 0) continue;
            if (temp[index] == value) {
                temp[index] += value;
                score += temp[index];
                index += step;
            } else if (temp[index] == 0) {
                temp[index] = value;
            } else {
                index += step;
                temp[index] = value;
            }
        }
        System.arraycopy(temp, 0, line, 0, SIZE);
    }

    private void finalizeMove() {
        addRandomTile();
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
}
