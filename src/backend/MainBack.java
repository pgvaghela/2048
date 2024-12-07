package backend;

import java.util.Scanner;

public class MainBack {
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        GameLogic game = new GameLogic(4);
        ScoreManager scoreManager = new ScoreManager();
        Scanner scanner = new Scanner(System.in);
        boolean gameRunning = true;

        System.out.println("Welcome to 2048!");
        System.out.println("Controls: W (Up), S (Down), A (Left), D (Right), Q (Quit)");
        System.out.println("Initial Board:");
        game.printGrid();

        while (gameRunning) {
            System.out.print("Enter your move: ");
            String input = scanner.nextLine().toUpperCase();

            boolean validMove = false;
            switch (input) {
                case "W" -> validMove = game.move("UP");
                case "S" -> validMove = game.move("DOWN");
                case "A" -> validMove = game.move("LEFT");
                case "D" -> validMove = game.move("RIGHT");
                case "Q" -> {
                    gameRunning = false;
                    System.out.println("Thanks for playing!");
                    continue;
                }
                default -> {
                    System.out.println("Invalid input. Use W, A, S, D to move or Q to quit.");
                    continue;
                }
            }

            if (validMove) {
                scoreManager.addScore(game.getScore());
                System.out.println("Current Board:");
                game.printGrid(); // Print grid after a valid move
                System.out.println("Current Score: " + scoreManager.getScore());
                System.out.println("High Score: " + scoreManager.getHighScore());

                if (game.isGameOver()) {
                    System.out.println("Game Over! No more moves are possible.");
                    gameRunning = false;
                }
            } else if (!input.equals("Q")) {
                System.out.println("No valid move. Try a different direction.");
            }
        }

        System.out.println("Final Score: " + scoreManager.getScore());
        System.out.println("High Score: " + scoreManager.getHighScore());
        scanner.close();
    }
}
