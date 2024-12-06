package frontend;

import backend.GameLogic;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class GameUI extends JFrame {
    private final GameLogic gameLogic;
    private JLabel scoreLabel;
    private JLabel bestScoreLabel;
    private JPanel gamePanel;
    private int bestScore;

    public GameUI() {
        gameLogic = new GameLogic();
        bestScore = 0;

        setTitle("2048 Game");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create score and control panels
        add(createScorePanel(), BorderLayout.NORTH);
        gamePanel = createGamePanel();
        add(gamePanel, BorderLayout.CENTER);

        // Add Input Handling
        InputHandler inputHandler = new InputHandler(gameLogic, this::refresh);
        addKeyListener(inputHandler);

        setFocusable(true);
        setVisible(true);
    }

    private JPanel createGamePanel() {
        JPanel panel = new JPanel(new GridLayout(4, 4, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(0xBBADA0));
        int[][] grid = gameLogic.getGrid();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                panel.add(new Tile(grid[i][j]));
            }
        }
        return panel;
    }

    private JPanel createScorePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0xF9F6F2));

        // Score display
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Best score display
        bestScoreLabel = new JLabel("Best: 0");
        bestScoreLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // New Game button
        JButton newGameButton = new JButton("New Game");
        newGameButton.setFocusPainted(false);
        newGameButton.addActionListener(this::resetGame);

        // Layout
        JPanel scoresPanel = new JPanel();
        scoresPanel.setBackground(new Color(0xF9F6F2));
        scoresPanel.add(scoreLabel);
        scoresPanel.add(Box.createHorizontalStrut(20));
        scoresPanel.add(bestScoreLabel);

        panel.add(scoresPanel, BorderLayout.WEST);
        panel.add(newGameButton, BorderLayout.EAST);

        return panel;
    }

    private void refresh() {
        // Update game grid
        int[][] grid = gameLogic.getGrid();
        Component[] components = gamePanel.getComponents();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int componentIndex = i * grid.length + j;
                Tile tile = (Tile) components[componentIndex];
                tile.setValue(grid[i][j]);
            }
        }
    
        // Update score and best score
        scoreLabel.setText("Score: " + gameLogic.getScore());
        if (gameLogic.getScore() > bestScore) {
            bestScore = gameLogic.getScore();
        }
        bestScoreLabel.setText("Best: " + bestScore);
    
        // Repaint game panel
        gamePanel.repaint();
        this.requestFocusInWindow(); // Ensure the window receives key events
    }
    

    private void resetGame(ActionEvent event) {
        gameLogic.reset(); // Reset the backend state
        refresh();         // Refresh the UI to reflect the new state
    }
    
}
