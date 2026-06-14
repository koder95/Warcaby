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
public class Movement implements TourImplement {

    private ChessLocationPoint startPoint, endPoint;
    
    public Movement() {
    }

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

    public Movement(ChessLocationPoint startPoint, ChessLocationPoint endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }
    
    public synchronized void move(){
        GameMatch.enabledChessboard(false);
        if(startPoint == null || endPoint == null) return;
        startPoint.getChessFieldFromChessboard().getPawn().move(endPoint);
        if(Rolles.isInLastField(endPoint.getPawnInChessField())) endPoint.getPawnInChessField().transformToKing();
        GameMatch.enabledPlayer(GameMatch.getHumanPlayer(), true);
        Chessboard.resetCommands();
        startPoint = null;
        endPoint = null;
    }

    @Override
    public void turn() {
        move();
    }

    @Override
    public Pawn getPawnWhichStartTour() {
        return startPoint.getPawnInChessField();
    }
}
