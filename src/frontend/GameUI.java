package frontend;

import javax.swing.*;
import java.awt.*;
import backend.GameLogic;

public class GameUI extends JFrame {
    private GameLogic gameLogic;

    public GameUI() {
        gameLogic = new GameLogic();
        setTitle("2048 Game");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createGamePanel(), BorderLayout.CENTER);
        add(createScorePanel(), BorderLayout.NORTH);

        setVisible(true);
    }

    private JPanel createGamePanel() {
        JPanel panel = new JPanel(new GridLayout(4, 4));
        int[][] grid = gameLogic.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                panel.add(new Tile(grid[i][j]));
            }
        }
        return panel;
    }

    private JPanel createScorePanel() {
        JPanel panel = new JPanel();
        JLabel scoreLabel = new JLabel("Score: 0");
        panel.add(scoreLabel);
        return panel;
    }
}
