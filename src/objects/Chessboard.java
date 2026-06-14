/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.ArrayList;
import objects.defaultFields.DefaultBlackChessField;
import objects.defaultFields.DefaultBlackChessLabelField;
import objects.defaultFields.DefaultWhiteChessField;
import objects.defaultFields.DefaultWhiteChessLabelField;
import system.CheckersDirection;
import system.swing.JMainPanel;

/**
 *
 * @author Kamil
 */
public final class Chessboard extends JMainPanel {
    
    private static ChessField[][] chessFields;
    
    private Chessboard(int x, int y){
        setOpaque(true);
        setLocation(x, y);
        chessFields = new ChessField[8][8];
        for(int i = 0, row = 0, Y = y + ChessLabelField.getDefaultHeight(); row < 8; row++, i++, Y += ChessField.getDefaultHeight()){
            for(int column = 0, X = x + ChessLabelField.getDefaultHeight(); column < 8; column++, i++, X += ChessField.getDefaultWidth()){
                if(i % 2 == 0){
                    chessFields[row][column] = new DefaultWhiteChessField(X, Y);
                    chessFields[row][column].setChessLocationPoint(new ChessLocationPoint((char)(64+column+1), 8-row));
                } else {
                    chessFields[row][column] = new DefaultBlackChessField(X, Y);
                    chessFields[row][column].setChessLocationPoint(new ChessLocationPoint((char)(64+column+1), 8-row));
                }
            }
        }
    }

    public static ChessField[] getChessFields() {
        ChessField[] chessFields = new ChessField[64];
        for(int i = 0, j = 0; i < Chessboard.chessFields.length; j += Chessboard.chessFields[i].length, i++)
            System.arraycopy(Chessboard.chessFields[i], 0, chessFields, j, 8);
        return chessFields;
    }
    
    public static ChessField getChessField(char column, int row){
        if(column < 'A' || column > 'H'){
            System.err.println("Nieprawidłowa nazwa kolumny!");
            return null;
        }
        if(row < 1 || row > 8){
            System.err.println("Nieprawidłowa nazwa wiersza!");
            return null;
        }
        return chessFields[8-row][(int)column - 65];
    }
    
    public static ChessField getChessField(ChessLocationPoint clp){
        return getChessField(clp.getColumn(), clp.getRow());
    }
    
    public static Chessboard create(int x, int y){
        return new Chessboard(x, y);
    }
    
    public static Chessboard create(){
        return new Chessboard(0, 0);
    }
    
    public void addFields(){
        for(ChessField[] f : chessFields) for(ChessField chessField : f) add(chessField);
    }
    
    public void addLabels(){
        for(int row = 0, Y = getY(); row < 10; row++){
            boolean b1 = row == 0 || row == 9;
            for(int column = 0, X = getX(); column < 10; column++){
                if(b1){
                    if(column == 0 || column == 9)
                        X += ChessLabelField.getDefaultHeight();
                    else {
                        if(row == 0){
                            if(column % 2 == 0) add(new DefaultWhiteChessLabelField("" + (char)(64 + column), X, Y));
                            else add(new DefaultBlackChessLabelField("" + (char)(64 + column), X, Y));
                        }
                        else if(row == 9){
                            if(column % 2 == 0) add(new DefaultBlackChessLabelField("" + (char)(64 + column), X, Y));
                            else add(new DefaultWhiteChessLabelField("" + (char)(64 + column), X, Y));
                        }
                        if(column % 2 == 0) add(new DefaultWhiteChessLabelField("" + (char)(64 + column), X, Y));
                        else add(new DefaultBlackChessLabelField("" + (char)(64 + column), X, Y));
                        X += ChessLabelField.getDefaultWidth();
                    }
                } else {
                    if(column == 0){
                        int width = ChessLabelField.getDefaultHeight();
                        ChessLabelField.setDefaultHeight(ChessLabelField.getDefaultWidth());
                        ChessLabelField.setDefaultWidth(width);
                        if(row % 2 == 0) add(new DefaultWhiteChessLabelField("" + (9-row), X, Y));
                        else add(new DefaultBlackChessLabelField("" + (9-row), X, Y));
                        width = ChessLabelField.getDefaultHeight();
                        ChessLabelField.setDefaultHeight(ChessLabelField.getDefaultWidth());
                        ChessLabelField.setDefaultWidth(width);
                        X += ChessLabelField.getDefaultHeight();
                    }
                    else if(column == 9){
                        int width = ChessLabelField.getDefaultHeight();
                        ChessLabelField.setDefaultHeight(ChessLabelField.getDefaultWidth());
                        ChessLabelField.setDefaultWidth(width);
                        if(row % 2 == 0) add(new DefaultBlackChessLabelField("" + (9-row), X, Y));
                        else add(new DefaultWhiteChessLabelField("" + (9-row), X, Y));
                        width = ChessLabelField.getDefaultHeight();
                        ChessLabelField.setDefaultHeight(ChessLabelField.getDefaultWidth());
                        ChessLabelField.setDefaultWidth(width);
                        X += ChessLabelField.getDefaultHeight();
                    } else X += ChessLabelField.getDefaultWidth();
                }
            }
            if(b1) Y += ChessLabelField.getDefaultHeight();
            else Y += ChessField.getDefaultHeight();
        }
    }
    
    public static void resetCommands(){
        for(ChessField chessField : getChessFields()) chessField.setActionCommand("");
    }
    
    /**
     * @param field pole źródłowe
     * @param direction kierunek odliczania pól
     * @param length ilość pól, które mają być zliczone
     * @return pole szachowe odległe od pola <i>field</i> o <i>length</i> pól w kierunku <i>direction</i>.
     */
    public static ChessField getNextChessField(ChessField field, CheckersDirection direction, int length){
        return getChessField((char)(field.getChessLocationPoint().getColumn() + (length * direction.getTranslateX())),
                field.getChessLocationPoint().getRow() + (length * direction.getTranslateY()));
    }
    
    /**
     * @param field pole źródłowe
     * @param direction kierunek odliczania pól
     * @param length ilość pól, które mają być zliczone
     * @return true, jeśli pole szachowe odległe od pola <i>field</i> o <i>length</i> pól w kierunku <i>direction</i> istnieje.
     */
    public static boolean isNextChessField(ChessField field, CheckersDirection direction, int length){
        if((char)(field.getChessLocationPoint().getColumn() + (length * direction.getTranslateX())) >= 'A' &&
                (char)(field.getChessLocationPoint().getColumn() + (length * direction.getTranslateX())) <= 'H' &&
                field.getChessLocationPoint().getRow() + (length * direction.getTranslateY()) >= 1 &&
                field.getChessLocationPoint().getRow() + (length * direction.getTranslateY()) <= 8)
            return true;
        else return false;
    }
    
    /**
     * @param location pole źródłowe
     * @param direction kierunek odliczania pól
     * @param length ilość pól, które mają być zliczone
     * @return lokalizacje pola szachowego odległego od lokalizacji <i>location</i> o <i>length</i> pól w kierunku <i>direction</i>.
     */
    public static ChessLocationPoint getNextChessLocationPoint(ChessLocationPoint location, CheckersDirection direction, int length){
        return getChessField((char)(location.getColumn() + (direction.getTranslateX()*length)), location.getRow() + (direction.getTranslateY()*length)).getChessLocationPoint();
    }
    
    /**
     * @param location pole źródłowe
     * @param direction kierunek odliczania pól
     * @param length ilość pól, które mają być zliczone
     * @return true, jeśli lokalizacja pola szachowe odległego od lokalizacji <i>location</i> o <i>length</i> pól w kierunku <i>direction</i> istnieje.
     */
    public static boolean isNextChessLocationPoint(ChessLocationPoint location, CheckersDirection direction, int length){
        char column = (char)(location.getColumn() + length*direction.getTranslateX());
        int row = location.getRow() + length*direction.getTranslateY();
        return (column >= 'A' && column <= 'H') && (row >= 1 && row <= 8);
    }
    
    public static void reset(){
        for(ChessField cf : getChessFields()) cf.obliviate();
    }
    
    public static ChessLocationPoint[] getChessLocationPoints() {
        ArrayList<ChessLocationPoint> list = new ArrayList<>();
        for (ChessField f: getChessFields()) {
            list.add(f.getChessLocationPoint());
        }
        return list.toArray(new ChessLocationPoint[list.size()]);
    }
}
