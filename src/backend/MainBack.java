package backend;

import java.util.Scanner;

public class MainBack {
    public static void main(String[] args) {
        GameLogic game = new GameLogic();
        ScoreManager scoreManager = new ScoreManager();
        Scanner scanner = new Scanner(System.in);
        boolean gameRunning = true;

        System.out.println("Welcome to 2048!");
        System.out.println("Controls: W (Up), S (Down), A (Left), D (Right), Q (Quit)");
        game.printGrid();

        while (gameRunning) {
            System.out.print("Enter your move: ");
            String input = scanner.nextLine().toUpperCase();

            switch (input) {
                case "W" -> {
                    game.up();
                    scoreManager.addScore(game.getScore());
                }
                case "S" -> {
                    game.down();
                    scoreManager.addScore(game.getScore());
                }
                case "A" -> {
                    game.left();
                    scoreManager.addScore(game.getScore());
                }
                case "D" -> {
                    game.right();
                    scoreManager.addScore(game.getScore());
                }
                case "Q" -> {
                    gameRunning = false;
                    System.out.println("Thanks for playing!");
                    break;
                }
                default -> {
                    System.out.println("Invalid input. Use W, A, S, D to move or Q to quit.");
                    continue;
                }
            }

            if (!input.equals("Q")) {
                game.printGrid();
                System.out.println("Current Score: " + scoreManager.getScore());
                System.out.println("High Score: " + scoreManager.getHighScore());

                if (game.isGameOver()) {
                    System.out.println("Game Over! No more moves are possible.");
                    gameRunning = false;
                }
            }
        }

        System.out.println("Final Score: " + scoreManager.getScore());
        System.out.println("High Score: " + scoreManager.getHighScore());
        scoreManager.saveHighScore();
        scanner.close();
    }
}
