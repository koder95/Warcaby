/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.awt.Color;

/**
 *
 * @author Kamil
 */
public class BlackChessField extends ChessField {

    public BlackChessField(int x, int y, int width, int height, Color buttonPressedColor) {
        super(new Color(70, 70, 70), x, y, width, height, buttonPressedColor);
    }

    public BlackChessField(int x, int y, int width, int height) {
        this(x, y, width, height, null);
    }

    public BlackChessField(int x, int y) {
        this(x, y, getDefaultWidth(), getDefaultHeight());
    }

    public BlackChessField() {
        this(0, 0);
    }
    
}
