package frontend;

import backend.GameLogic;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputHandler extends KeyAdapter {
    private final GameLogic gameLogic;
    private final Runnable onMove;
    private final Runnable playMoveSound;

    public InputHandler(GameLogic gameLogic, Runnable onMove, Runnable playMoveSound) {
        this.gameLogic = gameLogic;
        this.onMove = onMove;
        this.playMoveSound = playMoveSound;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        boolean moved = false;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> moved = gameLogic.move("UP");
            case KeyEvent.VK_DOWN -> moved = gameLogic.move("DOWN");
            case KeyEvent.VK_LEFT -> moved = gameLogic.move("LEFT");
            case KeyEvent.VK_RIGHT -> moved = gameLogic.move("RIGHT");
        }
        if (moved) {
            playMoveSound.run();
            onMove.run();
        }
    }
}