package frontend;

import backend.GameLogic;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputHandler extends KeyAdapter {
    private GameLogic gameLogic;

    public InputHandler(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> gameLogic.move("UP");
            case KeyEvent.VK_DOWN -> gameLogic.move("DOWN");
            case KeyEvent.VK_LEFT -> gameLogic.move("LEFT");
            case KeyEvent.VK_RIGHT -> gameLogic.move("RIGHT");
        }
    }
}
