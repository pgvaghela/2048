/**
 * Author's names: Ashutosh Dayal, Apurbo Barua, Jacob Dority, Priyansh Vaghela 
 */

 package frontend;

 import java.awt.*;
 import javax.swing.*;
 
 /**
  * this class representing a single tile in a grid based game like 2048 each
  * tile displays a value and changes its color based on the value
  */
 public class Tile extends JPanel {
     private int value; // the value displayed on the tile (e.g., 2, 4, 8, etc)
 
     /**
      * the constructor for the Tile class it sets the initial value, size,
      * background color, and border of the tile.
      *
      * @param value the value to display on the tile
      */
     public Tile(int value) {
         this.value = value;
         setPreferredSize(new Dimension(100, 100));
         setBackground(getTileColor(value));
         setBorder(BorderFactory.createLineBorder(new Color(0xCCC0B3)));
     }
 
     /**
      * updates the tile's value and refreshes its appearance
      *
      * @param value the new value to display on the tile
      */
     public void setValue(int value) {
         this.value = value; // updates the tile's value
         setBackground(getTileColor(value)); // updates the background color based on the new value
         repaint(); // re draw the tile to reflect the changes
     }
 
     /**
      * determines the background color of the tile based on its value. different
      * values are mapped to specific colors for visual differentiation.
      *
      * @param value the value of the tile
      * @return the corresponding color for the tile
      */
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
 
     /**
      * custom rendering for the tile draws the tile with its value centered and
      * styled appropriately
      *
      * @param g the Graphics object used for drawing
      */
     @Override
     protected void paintComponent(Graphics g) {
         super.paintComponent(g); // ensure proper rendering by calling the superclass's method
 
         // set the text color: white for high values, black for low values
         g.setColor(value > 4 ? Color.WHITE : Color.BLACK);
         g.setFont(new Font("Arial", Font.BOLD, 24));
 
         // Only draw the value if it's greater than 0
         if (value > 0) {
             String text = String.valueOf(value);
             FontMetrics fm = g.getFontMetrics();
             int x = (getWidth() - fm.stringWidth(text)) / 2;
             int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
             g.drawString(text, x, y);
         }
     }
 }