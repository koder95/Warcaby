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
public class BlackChessLabelField extends ChessLabelField {

    public BlackChessLabelField(String text, int x, int y, int width, int height) {
        super(text, new Color(0, 0, 0, 125), Color.yellow, x, y, width, height);
    }

    public BlackChessLabelField(String text, int x, int y) {
        this(text, x, y, getDefaultWidth(), getDefaultHeight());
    }

    public BlackChessLabelField(String text) {
        this(text, 0, 0);
    }
    
}
