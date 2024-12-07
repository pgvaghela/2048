package backend;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameLogicTest {

    @Test

    void testConstructorInitialization() {

        GameLogic gameLog = new GameLogic(4);

        assertEquals(0, gameLog.getScore());

        assertEquals(4, gameLog.getGrid().length);

        assertEquals(4, gameLog.getGrid()[0].length);

        int[][] testGrid = gameLog.getGrid();

        int count = 0;

        for (int[] row : testGrid) {

            for (int val : row) {

                if (val == 2 || val == 4) {
                    count++;
                }

            }

        }

        assertEquals(2, count); // Initial board should have exactly two tiles

    }

    @Test

    void testAddRandomTile() {

        GameLogic gameLog = new GameLogic(4);

        gameLog.addRandomTile(); // Should add one more tile

        int[][] testGrid = gameLog.getGrid();

        int count = 0;

        for (int[] row : testGrid) {

            for (int val : row) {

                if (val == 2 || val == 4) {
                    count++;
                }

            }

        }

        assertEquals(3, count); // Now there should be three tiles

    }

    @Test

    void testAddRandomTileWithNoOpenSpaces() {

        GameLogic gameLog = new GameLogic(4);

        // Fill the grid completely with values
        int[][] testGrid = gameLog.getGrid();

        for (int i = 0; i < testGrid.length; i++) {

            for (int j = 0; j < testGrid[i].length; j++) {

                testGrid[i][j] = 2;

            }

        }

        gameLog.addRandomTile(); // Adding a tile to a full grid should do nothing

        int count = 0;

        for (int[] row : testGrid) {

            for (int val : row) {

                if (val == 2 || val == 4) {
                    count++;
                }

            }

        }

        assertEquals(16, count); // All positions are already filled

    }

    @Test

    void testIsGameOver() {

        GameLogic gameLog = new GameLogic(4);

        assertFalse(gameLog.isGameOver()); // Initially, the game should not be over

        // Fill the grid with values that prevent merging
        int[][] testGrid = gameLog.getGrid();

        int value = 1;

        for (int i = 0; i < testGrid.length; i++) {

            for (int j = 0; j < testGrid[i].length; j++) {

                testGrid[i][j] = value++;

            }

        }

        assertTrue(gameLog.isGameOver()); // No empty tiles and no merges possible

    }

    @Test
    void testMoveUpWithMerge() {

        GameLogic gameLog = new GameLogic(4);

        int[][] testGrid = gameLog.getGrid();

        clearGrid(testGrid);

        for (int col = 0; col < testGrid[0].length; col++) {

            testGrid[0][col] = 2; // Top row

            testGrid[1][col] = 2; // Second row

        }

        gameLog.move("UP");

        for (int col = 0; col < testGrid[0].length; col++) {

            assertEquals(4, testGrid[0][col]); // Tiles should merge into 4

        }

    }

    @Test

    void testMoveRightWithMerge() {

        GameLogic gameLog = new GameLogic(4);

        int[][] testGrid = gameLog.getGrid();

        clearGrid(testGrid);

        for (int row = 0; row < testGrid.length; row++) {

            testGrid[row][0] = 2;

            testGrid[row][1] = 2;

        }

        gameLog.move("RIGHT");

        for (int row = 0; row < testGrid.length; row++) {

            assertEquals(4, testGrid[row][3]); // Tiles should merge into 4 at the rightmost position

        }

    }

    @Test

    void testMoveLeftWithMerge() {

        GameLogic gameLog = new GameLogic(4);

        int[][] testGrid = gameLog.getGrid();

        clearGrid(testGrid);

        for (int row = 0; row < testGrid.length; row++) {

            testGrid[row][2] = 2;

            testGrid[row][3] = 2;

        }

        gameLog.move("LEFT");

        for (int row = 0; row < testGrid.length; row++) {

            assertEquals(4, testGrid[row][0]); // Tiles should merge into 4 at the leftmost position

        }

    }

    @Test

    void testMoveDownWithMerge() {

        GameLogic gameLog = new GameLogic(4);

        int[][] testGrid = gameLog.getGrid();

        clearGrid(testGrid);

        for (int col = 0; col < testGrid[0].length; col++) {

            testGrid[0][col] = 2; // Top row

            testGrid[2][col] = 2; // Third row

        }
        //System.out.println(gameLog);
        gameLog.move("DOWN");
        //System.out.println(gameLog);
        for (int col = 0; col < testGrid[0].length; col++) {

            assertEquals(4, testGrid[3][col]); // Tiles should merge into 4 at the bottommost position

        }

    }

    // The following are tests to ensure that moves performed where no tiles merge move to their correct positions
    @Test
    void testMoveUpWithNoMerge() {
        GameLogic gameLog = new GameLogic(4);
        int[][] testGrid = gameLog.getGrid();
        clearGrid(testGrid);
        for (int col = 0; col < testGrid[0].length; col++) {
            testGrid[1][col] = 4;
            testGrid[3][col] = 2;
        }
        //System.out.println(gameLog);
        gameLog.move("UP");
        //System.out.println(gameLog);

        for (int col = 0; col < testGrid[0].length; col++) {
            assertEquals(testGrid[0][col], 4);
            assertEquals(testGrid[1][col], 2);
        }
    }

    @Test
    void testMoveRightWithNoMerge() {
        GameLogic gameLog = new GameLogic(4);
        int[][] testGrid = gameLog.getGrid();
        clearGrid(testGrid);
        for (int col = 0; col < testGrid[0].length; col++) {
            testGrid[col][1] = 4;
            testGrid[col][3] = 2;
        }
        //System.out.println(gameLog);
        gameLog.move("RIGHT");
        //System.out.println(gameLog);

        for (int col = 0; col < testGrid[0].length; col++) {
            assertEquals(testGrid[col][2], 4);
            assertEquals(testGrid[col][3], 2);
        }
    }

    @Test
    void testMoveDownWithNoMerge() {
        GameLogic gameLog = new GameLogic(4);
        int[][] testGrid = gameLog.getGrid();
        clearGrid(testGrid);
        for (int col = 0; col < testGrid[0].length; col++) {
            testGrid[0][col] = 4;
            testGrid[2][col] = 2;
        }
        //System.out.println(gameLog);
        gameLog.move("DOWN");
        //System.out.println(gameLog);

        for (int col = 0; col < testGrid[0].length; col++) {
            assertEquals(testGrid[2][col], 4);
            assertEquals(testGrid[3][col], 2);
        }
    }

    @Test
    void testMoveLeftWithNoMerge() {
        GameLogic gameLog = new GameLogic(4);
        int[][] testGrid = gameLog.getGrid();
        clearGrid(testGrid);
        for (int col = 0; col < testGrid[0].length; col++) {
            testGrid[col][1] = 4;
            testGrid[col][3] = 2;
        }
        //System.out.println(gameLog);
        gameLog.move("LEFT");
        //System.out.println(gameLog);

        for (int col = 0; col < testGrid[0].length; col++) {
            assertEquals(testGrid[col][0], 4);
            assertEquals(testGrid[col][1], 2);
        }
    }
    
 // The following are tests to ensure that there are no new tiles added on invalid moves
    @Test
    void testMoveUpWithNoValidMove() {
    	
    	GameLogic gameLog = new GameLogic(4);
    	GameLogic gameLog2 = new GameLogic(4);
        int[][] testGrid = gameLog.getGrid();
        int[][] testGrid2 = gameLog2.getGrid();
        clearGrid(testGrid);
        clearGrid(testGrid2);
        for (int col = 0; col < testGrid[0].length; col++) { // create 2 identical grids
            testGrid[0][col] = 4;
            testGrid[1][col] = 2;
            testGrid2[0][col] = 4;
            testGrid2[1][col] = 2;
        }
        //System.out.println(gameLog);
        gameLog.move("UP"); // Only move one of the grids
        //System.out.println(gameLog);
        
        // Ensure that both grids are equal and an invalid move did not add new tiles.
        for (int i = 0; i < testGrid.length; i++) {
        	for (int j = 0; j < testGrid[0].length; j++) {
        		assertEquals(testGrid[i][j], testGrid2[i][j]);
        	}
        }
    }
    
    @Test
    void testMoveDownWithNoValidMove() {
    	GameLogic gameLog = new GameLogic(4);
    	GameLogic gameLog2 = new GameLogic(4);
        int[][] testGrid = gameLog.getGrid();
        int[][] testGrid2 = gameLog2.getGrid();
        clearGrid(testGrid);
        clearGrid(testGrid2);
        for (int col = 0; col < testGrid[0].length; col++) { // create 2 identical grids
            testGrid[2][col] = 4;
            testGrid[3][col] = 2;
            testGrid2[2][col] = 4;
            testGrid2[3][col] = 2;
        }
        //System.out.println(gameLog);
        gameLog.move("DOWN"); // Only move one of the grids
        //System.out.println(gameLog);
        
        // Ensure that both grids are equal and an invalid move did not add any new tiles.
        for (int i = 0; i < testGrid.length; i++) {
        	for (int j = 0; j < testGrid[0].length; j++) {
        		assertEquals(testGrid[i][j], testGrid2[i][j]);
        	}
        }
    }
    
    @Test
    void testMoveRightWithNoValidMove() {
    	GameLogic gameLog = new GameLogic(4);
    	GameLogic gameLog2 = new GameLogic(4);
        int[][] testGrid = gameLog.getGrid();
        int[][] testGrid2 = gameLog2.getGrid();
        clearGrid(testGrid);
        clearGrid(testGrid2);
        for (int col = 0; col < testGrid[0].length; col++) { // create 2 identical grids
            testGrid[col][2] = 4;
            testGrid[col][3] = 2;
            testGrid2[col][2] = 4;
            testGrid2[col][3] = 2;
        }
        System.out.println(gameLog);
        gameLog.move("RIGHT"); // Only move one of the grids
        System.out.println(gameLog);
        
        // Ensure that both grids are equal and an invalid move did not add any new tiles.
        for (int i = 0; i < testGrid.length; i++) {
        	for (int j = 0; j < testGrid[0].length; j++) {
        		assertEquals(testGrid[i][j], testGrid2[i][j]);
        	}
        }
    }
    
    @Test
    void testMoveLeftWithNoValidMove() {
    	GameLogic gameLog = new GameLogic(4);
    	GameLogic gameLog2 = new GameLogic(4);
        int[][] testGrid = gameLog.getGrid();
        int[][] testGrid2 = gameLog2.getGrid();
        clearGrid(testGrid);
        clearGrid(testGrid2);
        for (int col = 0; col < testGrid[0].length; col++) { // create 2 identical grids
            testGrid[col][0] = 4;
            testGrid[col][1] = 2;
            testGrid2[col][0] = 4;
            testGrid2[col][1] = 2;
        }
        System.out.println(gameLog);
        gameLog.move("LEFT"); // Only move one of the grids
        System.out.println(gameLog);
        
        // Ensure that both grids are equal and an invalid move did not add any new tiles.
        for (int i = 0; i < testGrid.length; i++) {
        	for (int j = 0; j < testGrid[0].length; j++) {
        		assertEquals(testGrid[i][j], testGrid2[i][j]);
        	}
        }
    }
    

    @Test
    void testToStringOutput() {

        GameLogic gameLog = new GameLogic(4);

        String boardString = gameLog.toString();

        assertNotNull(boardString);

        assertTrue(boardString.contains("\t")); // Ensure the grid is represented properly

    }

    void clearGrid(int[][] grid) {
    
for (int i = 0; i < grid.length; i++) {
    
			for (int j = 0; j < grid[i].length; j++) {
    
				grid[i][j] = 0;
        
			}
            
		}
                
	}
            
}            
