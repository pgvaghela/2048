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
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> gameLogic.up();
            case KeyEvent.VK_DOWN -> gameLogic.down();
            case KeyEvent.VK_LEFT -> gameLogic.left();
            case KeyEvent.VK_RIGHT -> gameLogic.right();
        }
        onMove.run();
    }
}
