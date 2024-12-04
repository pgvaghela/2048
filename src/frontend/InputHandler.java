package frontend;

import backend.GameLogic;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputHandler extends KeyAdapter {
    private final GameLogic gameLogic;
    private final Runnable onMove;

    public InputHandler(GameLogic gameLogic, Runnable onMove) {
        this.gameLogic = gameLogic;
        this.onMove = onMove;
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
            onMove.run();
        }
    }
}
