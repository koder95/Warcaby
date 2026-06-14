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
public class WhiteChessField extends ChessField {

    public WhiteChessField(int x, int y, int width, int height, Color buttonPressedColor) {
        super(Color.white, x, y, width, height, buttonPressedColor);
        setEnabled(false);
    }

    public WhiteChessField(int x, int y, int width, int height) {
        this(x, y, width, height, null);
    }

    public WhiteChessField(int x, int y) {
        this(x, y, getDefaultWidth(), getDefaultHeight());
    }

    public WhiteChessField() {
        this(0, 0);
    }
    
}
