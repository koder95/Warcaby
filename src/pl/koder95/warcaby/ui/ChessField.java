/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.koder95.warcaby.ui;

import pl.koder95.warcaby.system.ChessboardPoint;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 *
 * @author Kamil Mularski
 */
public class ChessField extends JButton {
    
    private Image pawnImage;
    private ChessField(Color background, int size) {
        this.setSize(size, size);
        this.setMinimumSize(new Dimension(size, size));
        this.setBackground(background);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
    }

    public ChessField(Chessboard.Fields board, ChessboardPoint point) {
        this (point.isBlack()? board.getFirstFieldColor() : board.getSecondFieldColor(), board.getFieldSize());
    }
    
}
