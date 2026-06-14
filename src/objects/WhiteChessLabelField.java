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
public class WhiteChessLabelField extends ChessLabelField {

    public WhiteChessLabelField(String text, int x, int y, int width, int height) {
        super(text, new Color(255, 255, 255, 125), Color.yellow, x, y, width, height);
    }

    public WhiteChessLabelField(String text, int x, int y) {
        this(text, x, y, getDefaultWidth(), getDefaultHeight());
    }

    public WhiteChessLabelField(String text) {
        this(text, 0, 0);
    }
    
}
