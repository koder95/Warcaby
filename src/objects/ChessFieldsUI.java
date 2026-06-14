/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.AbstractButton;
import javax.swing.plaf.metal.MetalButtonUI;

/**
 *
 * @author Kamil
 */
class ChessFieldsUI extends MetalButtonUI {

    private Color buttonPressedColor = new Color(255, 255, 125, 125);
    
    public ChessFieldsUI() {
    }
    
    public ChessFieldsUI(Color buttonPressedColor) {
        this.buttonPressedColor = buttonPressedColor;
    }

    @Override
    protected void paintButtonPressed(Graphics g, AbstractButton b) {
        super.paintButtonPressed(g, b);
        g.setColor(buttonPressedColor);
        g.fillRect(1, 1, b.getWidth()-2, b.getHeight()-2);
    }

    @Override
    protected Color getDisabledTextColor() {
        return super.getDisabledTextColor();
    }
}
