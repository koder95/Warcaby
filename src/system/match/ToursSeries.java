/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system.match;

import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Chessboard;
import objects.Pawn;
import objects.Player;
import system.Beat;
import system.GameMatch;
import system.Movement;
import system.Rolles;
import system.swing.MainGameFrame;
import system.swing.MainMenuPanel;
import ai.PlayerBrain;

/**
 *
 * @author Kamil
 */
public class ToursSeries {
    
    private static Tour humanTour;
    private static Tour computerTour;

    private ToursSeries(TourImplement humanTurn) {
        humanTour = new Tour(humanTurn);
    }
    
    public void play(){
        humanTour.play();
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(ToursSeries.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(GameMatch.getComputerPlayer().getPawns().length == 0 ||
                (!GameMatch.getComputerPlayer().hasBeats() && !GameMatch.getComputerPlayer().hasMovements())){
            System.out.println("Koniec gry! Wygrywasz partie. Gratulacje!");
            GameMatch.finallyGameMatch();
            
        } else if(GameMatch.getHumanPlayer().getPawns().length == 0 ||
                (!GameMatch.getHumanPlayer().hasBeats() && !GameMatch.getHumanPlayer().hasMovements())){
            System.out.println("Koniec gry! Wygrywa kompurer!");
            GameMatch.finallyGameMatch();
        }
        else {
            if(humanTour.turn == null){
                computerTour = new Tour(GameMatch.getComputerPlayer().getPlayerBrain().getTheBestTurn());
                computerTour.play();
            }
        }
        humanTour = null;
        computerTour = null;
        scanSecurity();
    }
    
    public void scanSecurity(){
        for(Pawn p : GameMatch.getHumanPlayer().getPawns()){
            if(p.getLocation().getPawnInChessField() == null) p.destroy();
        }
        for(Pawn p : GameMatch.getComputerPlayer().getPawns()){
            if(p.getLocation().getPawnInChessField() == null) p.destroy();
        }
    }
    
    public static ToursSeries create(Beat humanBeat){
        return new ToursSeries(humanBeat);
    }
    
    public static ToursSeries create(Movement humanMove){
        return new ToursSeries(humanMove);
    }
}
