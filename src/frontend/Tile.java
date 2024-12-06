package frontend;

import java.awt.*;
import javax.swing.*;

public class Tile extends JPanel {
    private int value;

    public Tile(int value) {
        this.value = value;
        setPreferredSize(new Dimension(100, 100));
        setBackground(getTileColor(value));
        setBorder(BorderFactory.createLineBorder(new Color(0xCCC0B3)));
    }

    public void setValue(int value) {
        this.value = value;
        setBackground(getTileColor(value));
        repaint();
    }

    private Color getTileColor(int value) {
        return switch (value) {
            case 2 -> new Color(0xEEE4DA);
            case 4 -> new Color(0xEDE0C8);
            case 8 -> new Color(0xF2B179);
            case 16 -> new Color(0xF59563);
            case 32 -> new Color(0xF67C5F);
            case 64 -> new Color(0xF65E3B);
            case 128 -> new Color(0xEDCF72);
            case 256 -> new Color(0xEDCC61);
            case 512 -> new Color(0xEDC850);
            case 1024 -> new Color(0xEDC53F);
            case 2048 -> new Color(0xEDC22E);
            default -> new Color(0xCDC1B4);
        };
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(value > 4 ? Color.WHITE : Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        if (value > 0) {
            String text = String.valueOf(value);
            FontMetrics fm = g.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(text)) / 2;
            int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
            g.drawString(text, x, y);
        }
    }
}
