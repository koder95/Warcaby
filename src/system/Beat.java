/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import objects.ChessLocationPoint;
import objects.Chessboard;
import objects.Pawn;
import system.match.TourImplement;

/**
 *
 * @author Kamil
 */
public class Beat implements TourImplement {

    private ChessLocationPoint startPoint, endPoint;
    private Pawn pawn;

    public void setStartPoint(ChessLocationPoint startPoint) {
        this.startPoint = startPoint;
    }

    public void setEndPoint(ChessLocationPoint endPoint) {
        this.endPoint = endPoint;
    }

    public ChessLocationPoint getStartPoint() {
        return startPoint;
    }

    public ChessLocationPoint getEndPoint() {
        return endPoint;
    }
    
    public Beat(ChessLocationPoint startPoint, ChessLocationPoint endPoint, Pawn pawn) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.pawn = pawn;
    }
    
    public synchronized void beat(){
        startPoint.getChessFieldFromChessboard().getPawn().move(endPoint);
        pawn.destroy();
    }

    public Pawn getPawn() {
        return pawn;
    }

    @Override
    public void turn() {
        beat();
    }

    @Override
    public String toString() {
        return getClass().getName() + "[" + startPoint + ", X" + pawn + "X, " + endPoint + "]";
    }
    
    public String toStringMessage(){
        return startPoint.toStringMessage() + " X" + pawn.getLocation().toStringMessage() + "X " + endPoint.toStringMessage();
    }

    @Override
    public Pawn getPawnWhichStartTour() {
        return startPoint.getPawnInChessField();
    }
}
