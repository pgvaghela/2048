/**
 * GameUI Class
 * Contributors: Apurbo, Ashutosh Dayal, Pri Vaghela, Jacob
 * 
 * Description:
 * This class provides the graphical user interface (GUI) for the 2048 game.
 * It includes the title screen, game board, score panel, and game over screen.
 * 
 */

 package frontend;

 import backend.AudioManager;
 import backend.GameLogic;
 import java.awt.*;
 import javax.swing.*;
 
 public class GameUI extends JFrame {
     private GameLogic gameLogic;
     private JLabel scoreLabel;
     private JLabel bestScoreLabel;
     private JPanel gamePanel;
     private int bestScore;
     private final AudioManager audioManager;
 
     /**
      * Constructor for GameUI.
      * Initializes the main window and starts the title screen.
      */
     public GameUI() {
         audioManager = new AudioManager();
         audioManager.playBackgroundMusic("/resources/spark-of-inspiration.wav");
 
         setTitle("2048 Game");
         setSize(500, 600);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setLayout(new BorderLayout());
 
         // Show the title screen initially
         showTitleScreen();
     }
 
     /**
      * Displays the title screen with options to select the grid size and start the game.
      */
     private void showTitleScreen() {
         JPanel titlePanel = new JPanel();
         titlePanel.setLayout(new BorderLayout());
         titlePanel.setBackground(new Color(0x1E1E1E)); // Dark background
 
         // Title Label
         JLabel titleLabel = new JLabel("2048", SwingConstants.CENTER);
         titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
         titleLabel.setForeground(Color.WHITE);
 
         // Grid Size Options
         JPanel optionsPanel = new JPanel();
         optionsPanel.setBackground(new Color(0x1E1E1E));
         optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
 
         JLabel gridSizeLabel = new JLabel("Select Grid Size:", SwingConstants.CENTER);
         gridSizeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
         gridSizeLabel.setForeground(Color.WHITE);
         gridSizeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
 
         String[] gridSizes = {"4x4", "5x5", "6x6"};
         JComboBox<String> gridSizeDropdown = new JComboBox<>(gridSizes);
         gridSizeDropdown.setFont(new Font("Arial", Font.PLAIN, 18));
         gridSizeDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);
 
         JButton startButton = new JButton("Start Game");
         startButton.setFont(new Font("Arial", Font.BOLD, 20));
         startButton.setFocusPainted(false);
         startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
         startButton.addActionListener(event -> {
             String selectedSize = (String) gridSizeDropdown.getSelectedItem();
             int gridSize = Integer.parseInt(selectedSize.split("x")[0]);
             startGame(gridSize);
         });
 
         // Adding components to the options panel
         optionsPanel.add(Box.createVerticalStrut(20)); // Spacer
         optionsPanel.add(gridSizeLabel);
         optionsPanel.add(Box.createVerticalStrut(10)); // Spacer
         optionsPanel.add(gridSizeDropdown);
         optionsPanel.add(Box.createVerticalStrut(20)); // Spacer
         optionsPanel.add(startButton);
 
         // Adding components to the title panel
         titlePanel.add(titleLabel, BorderLayout.NORTH);
         titlePanel.add(optionsPanel, BorderLayout.CENTER);
 
         // Show title screen
         getContentPane().removeAll();
         getContentPane().add(titlePanel, BorderLayout.CENTER);
         revalidate();
         repaint();
     }
 
     /**
      * Starts the game with the selected grid size.
      * @param gridSize The size of the game grid.
      */
     private void startGame(int gridSize) {
         gameLogic = new GameLogic(gridSize); // Pass the grid size to the GameLogic
         bestScore = 0;
 
         // Set up game UI components
         getContentPane().removeAll();
         add(createScorePanel(), BorderLayout.NORTH);
         gamePanel = createGamePanel(gridSize);
         add(gamePanel, BorderLayout.CENTER);
 
         // Input handling
         InputHandler inputHandler = new InputHandler(
             gameLogic,
             this::onMove,
             () -> audioManager.playSoundEffect("/resources/mixkit-modern-technology-select-3124.wav")
         );
         addKeyListener(inputHandler);
 
         // Update background music
         audioManager.stopBackgroundMusic();
         audioManager.playBackgroundMusic("/resources/spark-of-inspiration.wav");
 
         setFocusable(true);
         revalidate();
         repaint();
     }
 
     /**
      * Creates the main game grid panel.
      * @param gridSize The size of the grid.
      * @return The game grid panel.
      */
     private JPanel createGamePanel(int gridSize) {
         JPanel panel = new JPanel(new GridLayout(gridSize, gridSize, 10, 10));
         panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
         panel.setBackground(new Color(0xBBADA0));
         int[][] grid = gameLogic.getGrid();
 
         // Populate grid with tiles
         for (int i = 0; i < grid.length; i++) {
             for (int j = 0; j < grid[i].length; j++) {
                 panel.add(new Tile(grid[i][j]));
             }
         }
         return panel;
     }
 
     /**
      * Creates the score panel with current score, best score, and a new game button.
      * @return The score panel.
      */
     private JPanel createScorePanel() {
         JPanel panel = new JPanel(new BorderLayout());
         panel.setBackground(new Color(0xF9F6F2));
 
         scoreLabel = new JLabel("Score: 0");
         scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
 
         bestScoreLabel = new JLabel("Best: 0");
         bestScoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
 
         JButton newGameButton = new JButton("New Game");
         newGameButton.setFocusPainted(false);
         newGameButton.addActionListener(event -> showTitleScreen());
 
         JPanel scoresPanel = new JPanel();
         scoresPanel.setBackground(new Color(0xF9F6F2));
         scoresPanel.add(scoreLabel);
         scoresPanel.add(Box.createHorizontalStrut(20));
         scoresPanel.add(bestScoreLabel);
 
         panel.add(scoresPanel, BorderLayout.WEST);
         panel.add(newGameButton, BorderLayout.EAST);
 
         return panel;
     }
 
     /**
      * Updates the game state after a move and checks for game over.
      */
     private void onMove() {
         refresh();
         if (gameLogic.isGameOver()) {
             audioManager.playSoundEffect("/resources/game-over-38511.wav");
             audioManager.stopBackgroundMusic();
 
             // Display game over overlay
             JPanel overlayPanel = new JPanel();
             overlayPanel.setBackground(new Color(0, 0, 0, 150));
             overlayPanel.setLayout(new GridBagLayout());
 
             JPanel contentPanel = new JPanel();
             contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
             contentPanel.setOpaque(false);
 
             JLabel gameOverLabel = new JLabel("Game Over!", SwingConstants.CENTER);
             gameOverLabel.setFont(new Font("Arial", Font.BOLD, 36));
             gameOverLabel.setForeground(Color.WHITE);
             gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
 
             JButton restartButton = new JButton("Restart");
             restartButton.setFont(new Font("Arial", Font.BOLD, 20));
             restartButton.setFocusPainted(false);
             restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
             restartButton.addActionListener(event -> {
                 showTitleScreen();
                 getLayeredPane().remove(overlayPanel);
                 getLayeredPane().repaint();
             });
 
             contentPanel.add(gameOverLabel);
             contentPanel.add(Box.createVerticalStrut(20));
             contentPanel.add(restartButton);
 
             overlayPanel.add(contentPanel);
 
             overlayPanel.setBounds(0, 0, getWidth(), getHeight());
             getLayeredPane().add(overlayPanel, JLayeredPane.DRAG_LAYER);
             overlayPanel.setVisible(true);
 
             JOptionPane.showMessageDialog(this, "Game Over!");
         }
     }
 
     /**
      * Refreshes the UI to match the current game state.
      */
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
         if (gameLogic.getScore() > bestScore) {
             bestScore = gameLogic.getScore();
         }
         bestScoreLabel.setText("Best: " + bestScore);
 
         gamePanel.repaint();
         this.requestFocusInWindow();
     }
 }