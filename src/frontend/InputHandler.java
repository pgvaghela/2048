/**
 * Author's names: Ashutosh Dayal, Apurbo Barua, Jacob Dority, Priyansh Vaghela 
 */

 package frontend;

 import backend.GameLogic;
 import java.awt.event.KeyAdapter;
 import java.awt.event.KeyEvent;
 
 /**
  * this class handles keyboard input for controlling the game detects arrow key
  * presses and communicates with the game logic
  */
 public class InputHandler extends KeyAdapter {
     private final GameLogic gameLogic;
     private final Runnable onMove;
     private final Runnable playMoveSound;
 
     /**
      * constructor for the InputHandler class sets up the game logic and actions to
      * execute after a move
      *
      * @param gameLogic     the game logic instance to handle moves
      * @param onMove        a callback to update the UI after a move
      * @param playMoveSound a callback to play a sound after a move
      */
 
     public InputHandler(GameLogic gameLogic, Runnable onMove, Runnable playMoveSound) {
         this.gameLogic = gameLogic;
         this.onMove = onMove;
         this.playMoveSound = playMoveSound;
     }
 
     /**
      * handles key presses to control the game moves the game board in the direction
      * of the arrow key pressed
      *
      * @param e the KeyEvent triggered by a key press
      */
     @Override
     public void keyPressed(KeyEvent e) {
         boolean moved = false; // tracks whether the move was successful
 
         // check which arrow key was pressed and execute the corresponding move
         switch (e.getKeyCode()) {
         case KeyEvent.VK_UP -> moved = gameLogic.move("UP");
         case KeyEvent.VK_DOWN -> moved = gameLogic.move("DOWN");
         case KeyEvent.VK_LEFT -> moved = gameLogic.move("LEFT");
         case KeyEvent.VK_RIGHT -> moved = gameLogic.move("RIGHT");
         }
         // if a move was successful, play a sound and update the UI
         if (moved) {
             playMoveSound.run();
             onMove.run();
         }
     }
 }