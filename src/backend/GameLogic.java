/**
 * Author's names: Ashutosh Dayal, Apurbo Barua, Jacob Dority, Priyansh Vaghela 
 */

 package backend;

 import java.util.Random;
 
 /**
  * Represents the logic for a 2048-like game. Manages the game grid, score, tile
  * movement, and random tile generation.
  */
 public final class GameLogic {
     private final int[][] grid;
     private final int size; // Dynamic grid size
     private final Random random;
     private int score;
 
     /**
      * Initializes the game with a given grid size.
      * 
      * @param size the size of the grid (e.g., 4 for a 4x4 grid)
      */
     public GameLogic(int size) {
         this.size = size; // Set the grid size dynamically
         grid = new int[size][size];
         random = new Random();
         reset(); // Initialize the game
     }
 
     /**
      * Returns the current game grid.
      *
      * @return the game grid as a 2D array
      */
     public int[][] getGrid() {
         return grid;
     }
 
     /**
      * Returns the current score.
      *
      * @return the current score
      */
     public int getScore() {
         return score;
     }
 
     /**
      * Handles tile movement based on the specified direction.
      * 
      * @param direction the direction to move ("UP", "DOWN", "LEFT", "RIGHT")
      * @return true if the move was successful, false otherwise
      */
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
 
     // Handles moving tiles up
     private boolean moveUp() {
         for (int col = 0; col < size; col++) {
             moveColumn(col, -1); // Move each column upward
         }
         return true;
     }
 
     // Handles moving tiles down
 
     private boolean moveDown() {
         for (int col = 0; col < size; col++) {
             moveColumn(col, 1); // Move each column downward
         }
         return true;
     }
 
     // Handles moving tiles left
     private boolean moveLeft() {
         for (int row = 0; row < size; row++) {
             moveRow(row, -1); // Move each row left
         }
         return true;
     }
 
     // Handles moving tiles right
     private boolean moveRight() {
         for (int row = 0; row < size; row++) {
             moveRow(row, 1); // Move each row right
         }
         return true;
     }
 
     // Moves a specific column in a given direction
     private void moveColumn(int col, int direction) {
         int[] column = new int[size];
         for (int row = 0; row < size; row++) {
             column[row] = grid[row][col];
         }
         merge(column, direction);
         for (int row = 0; row < size; row++) {
             grid[row][col] = column[row];
         }
     }
 
     // Moves a specific row in a given direction
     private void moveRow(int row, int direction) {
         int[] line = grid[row];
         merge(line, direction);
         grid[row] = line;
     }
 
     // Merges tiles in a row or column
     private void merge(int[] line, int direction) {
         // Step 1: Slide non-zero tiles
         int[] temp = new int[size];
         int index = direction == -1 ? 0 : size - 1;
         int step = direction == -1 ? 1 : -1;
 
         for (int i = 0; i < size; i++) {
             int value = line[i];
             if (value != 0) {
                 temp[index] = value;
                 index += step;
             }
         }
 
         // Step 2: Merge tiles with the same value
         index = direction == -1 ? 0 : size - 1;
         step = direction == -1 ? 1 : -1;
 
         for (int i = 0; i < size - 1; i++) {
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
         int[] finalTemp = new int[size];
         index = direction == -1 ? 0 : size - 1;
 
         for (int i = 0; i < size; i++) {
             int value = temp[i];
             if (value != 0) {
                 finalTemp[index] = value;
                 index += step;
             }
         }
 
         System.arraycopy(finalTemp, 0, line, 0, size);
     }
 
     // Creates a copy of the game grid
     private int[][] copyGrid(int[][] source) {
         int[][] copy = new int[source.length][source[0].length];
         for (int i = 0; i < source.length; i++) {
             System.arraycopy(source[i], 0, copy[i], 0, source[i].length);
         }
         return copy;
     }
 
     // Checks if two grids are equal
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
 
     /**
      * Adds a random tile (2 or 4) to an empty cell in the grid.
      */
     public void addRandomTile() {
         int emptyCount = 0;
 
         // Count empty tiles
         for (int i = 0; i < size; i++) {
             for (int j = 0; j < size; j++) {
                 if (grid[i][j] == 0)
                     emptyCount++;
             }
         }
 
         // If no empty tiles are available, return
         if (emptyCount == 0)
             return;
 
         // Select a random empty position
         int target = random.nextInt(emptyCount);
         int count = 0;
         for (int i = 0; i < size; i++) {
             for (int j = 0; j < size; j++) {
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
 
     /**
      * Checks if the game is over (no moves left).
      *
      * @return true if the game is over, false otherwise
      */
     public boolean isGameOver() {
         for (int i = 0; i < size; i++) {
             for (int j = 0; j < size; j++) {
                 if (grid[i][j] == 0)
                     return false;
                 if (i > 0 && grid[i][j] == grid[i - 1][j])
                     return false;
                 if (j > 0 && grid[i][j] == grid[i][j - 1])
                     return false;
             }
         }
         return true;
     }
 
     /**
      * Returns the game grid as a formatted string.
      *
      * @return the grid as a string
      */
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
         for (int i = 0; i < size; i++) {
             for (int j = 0; j < size; j++) {
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