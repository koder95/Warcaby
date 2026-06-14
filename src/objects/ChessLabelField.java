/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.awt.Color;
import javax.swing.JLabel;

/**
 *
 * @author Kamil
 */
public class ChessLabelField extends JLabel {
    
    private static int defaultWidth = 80;
    private static int defaultHeight = 50;
    
    public ChessLabelField(String text, Color background, Color foreground, int x, int y, int width, int height) {
        setBackground(background);
        setForeground(foreground);
        setLocation(x, y);
        setSize(width, height);
        setText(text);
        setHorizontalAlignment(JLabel.CENTER);
        
    }

    public ChessLabelField(String text, Color background, Color foreground, int x, int y) {
        this(text, background, foreground, x, y, getDefaultWidth(), getDefaultHeight());
    }

    public ChessLabelField(String text, Color background, Color foreground) {
        this(text, background, foreground, 0, 0);
    }

    public ChessLabelField(String text) {
        this(text, Color.green, Color.yellow);
    }
    
    public static int getDefaultWidth(){
        return ChessLabelField.defaultWidth;
    }
    
    public static int getDefaultHeight(){
        return ChessLabelField.defaultHeight;
    }
    
    protected static void setDefaultHeight(int defaultHeight) {
        ChessLabelField.defaultHeight = defaultHeight;
    }

    protected static void setDefaultWidth(int defaultWidth) {
        ChessLabelField.defaultWidth = defaultWidth;
    }
}
