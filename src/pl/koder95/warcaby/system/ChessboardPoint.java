/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.koder95.warcaby.system;

/**
 *
 * @author Kamil Mularski
 */
public final class ChessboardPoint {
    
    private static final boolean[][] BLACK = {
        { true, false, true, false, true, false, true, false },
        { false, true, false, true, false, true, false, true },
        { true, false, true, false, true, false, true, false },
        { false, true, false, true, false, true, false, true },
        { true, false, true, false, true, false, true, false },
        { false, true, false, true, false, true, false, true },
        { true, false, true, false, true, false, true, false },
        { false, true, false, true, false, true, false, true },
    };
    
    private final char column;
    private final int row;

    private ChessboardPoint(char column, int row) {
        this.column = column;
        this.row = row;
    }

    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
    
    public boolean isBlack() {
        return BLACK[(int)(column - 'A')][row - 1];
    }
    
    public static ChessboardPoint get(char column, int row) {
        return POINTS[(int)(column - 'A')][row - 1];
    }
    
    private static final ChessboardPoint[][] POINTS = new ChessboardPoint[8][8];
    static {
        for (char column = 'A'; column <= 'H'; column++) {
            for (int row = 1; row <= 8; row++) {
                POINTS[(int)(column - 'A')][row-1] = new ChessboardPoint(column, row);
            }
        }
    };
}
