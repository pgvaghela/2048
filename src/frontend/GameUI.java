package frontend;

import backend.GameLogic;
import java.awt.*;
import javax.swing.*;

public class GameUI extends JFrame {
    private final GameLogic gameLogic;
    private JPanel gamePanel;
    private JLabel scoreLabel;

    public GameUI() {
        gameLogic = new GameLogic();
        setTitle("2048 Game");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        gamePanel = createGamePanel();
        add(gamePanel, BorderLayout.CENTER);
        add(createScorePanel(), BorderLayout.NORTH);

        InputHandler inputHandler = new InputHandler(gameLogic, this::refresh);
        addKeyListener(inputHandler);

        setFocusable(true);
        setVisible(true);
    }

    private JPanel createGamePanel() {
        JPanel panel = new JPanel(new GridLayout(4, 4));
        int[][] grid = gameLogic.getGrid();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                panel.add(new Tile(grid[i][j]));
            }
        }
        return panel;
    }

    private JPanel createScorePanel() {
        JPanel panel = new JPanel();
        scoreLabel = new JLabel("Score: " + gameLogic.getScore());
        panel.add(scoreLabel);
        return panel;
    }

    private void refresh() {
        int[][] grid = gameLogic.getGrid();
        Component[] components = gamePanel.getComponents();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int componentIndex = i * grid.length + j;
                Tile tile = (Tile) components[componentIndex];
                tile.setValue(grid[i][j]);
            }
        }
        scoreLabel.setText("Score: " + gameLogic.getScore());
        gamePanel.repaint();
    }
}
