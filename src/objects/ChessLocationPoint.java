/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import system.CheckersDirection;
import system.Movement;

/**
 *
 * @author Kamil
 */
public class ChessLocationPoint {
    
    private char column;
    private int row;

    ChessLocationPoint(char column, int row) {
        setColumn(column);
        setRow(row);
    }

    public ChessLocationPoint() {
        this('A', 1);
    }

    public final char getColumn() {
        return this.column;
    }

    public final int getRow() {
        return this.row;
    }

    public final void setColumn(char column) {
        if(column < 'A' || column > 'H') throw new IllegalArgumentException("Column's name is bad!");
        this.column = column;
    }

    public final void setRow(int row) {
        if(row < 1 || row > 8) throw new IllegalArgumentException("Row's name is bad!");
        this.row = row;
    }
    
    public synchronized void move(char column, int row){
        setColumn(column);
        setRow(row);
    }
    
    public void move(ChessLocationPoint clp){
        move(clp.getColumn(), clp.getRow());
    }
    
    public ChessField getChessFieldFromChessboard(){
        return Chessboard.getChessField(this);
    }
    
    @Override
    public String toString() {
        return this.getClass().getName() + "(" + column + "," + row + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ChessLocationPoint)){
            return false;
        }
        ChessLocationPoint clp = (ChessLocationPoint)obj;
        if(clp.column != this.column) return false;
        if(clp.row != this.row) return false;
        return true;
    }
    
    public boolean isPawnInChessField(){
        return (getPawnInChessField() != null);
    }
    
    public Pawn getPawnInChessField(){
        return getChessFieldFromChessboard().getPawn();
    }
    /**
     * Metoda działa jak w <i><b>Chessboard.getNextChessLocation()</b></i>
     * @see Chessboard
     */
    public ChessLocationPoint getNextChessLocationPoint(CheckersDirection direction, int length){
        return Chessboard.getNextChessLocationPoint(this, direction, length);
    }
    
    /**
     * Metoda działa jak w <i><b>Chessboard.isNextChessLocation()</b></i>
     * @see Chessboard
     */
    public boolean isNextChessLocationPoint(CheckersDirection direction, int length){
        return Chessboard.isNextChessLocationPoint(this, direction, length);
    }
    
    public ChessLocationPoint getNextChessLocationPoint(CheckersDirection direction){
        return getNextChessLocationPoint(direction, 1);
    }
    
    public boolean isNextChessLocationPoint(CheckersDirection direction){
        return isNextChessLocationPoint(direction, 1);
    }
    
    public String toStringMessage(){
        return "[" + getColumn() + ", " + getRow() + "]";
    }
}
