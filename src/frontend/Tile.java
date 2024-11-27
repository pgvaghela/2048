package frontend;

import java.awt.*;
import javax.swing.*;

public class Tile extends JPanel {
    private int value;

    public Tile(int value) {
        this.value = value;
        setPreferredSize(new Dimension(80, 80));
        setBackground(value == 0 ? Color.LIGHT_GRAY : Color.ORANGE);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        if (value > 0) {
            g.drawString(String.valueOf(value), getWidth() / 2, getHeight() / 2);
        }
    }
}
