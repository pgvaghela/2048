package frontend;

import java.awt.*;
import javax.swing.*;

public class Tile extends JPanel {
    private int value;

    public Tile(int value) {
        this.value = value;
        setPreferredSize(new Dimension(100, 100));
        setBackground(getTileColor(value));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }

    public void setValue(int value) {
        System.out.println("Updating tile value to: " + value);
        this.value = value;
        setBackground(getTileColor(value));
        repaint();
    }
    

    private Color getTileColor(int value) {
        switch (value) {
            case 2: return new Color(0xEEE4DA);
            case 4: return new Color(0xEDE0C8);
            case 8: return new Color(0xF2B179);
            case 16: return new Color(0xF59563);
            case 32: return new Color(0xF67C5F);
            case 64: return new Color(0xF65E3B);
            case 128: return new Color(0xEDCF72);
            case 256: return new Color(0xEDCC61);
            case 512: return new Color(0xEDC850);
            case 1024: return new Color(0xEDC53F);
            case 2048: return new Color(0xEDC22E);
            default: return Color.LIGHT_GRAY;
        }
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
