/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import javax.swing.JFrame;
import objects.*;
import objects.players.BlackPlayer;
import objects.players.WhitePlayer;
import system.swing.MainGameFrame;
import system.swing.MainMenuPanel;

/**
 *
 * @author Kamil
 */
public final class GameMatch {
    
    protected Chessboard chessboard;
    public static WhitePlayer WHITE_PLAYER = new WhitePlayer();
    public static BlackPlayer BLACK_PLAYER = new BlackPlayer();
    
    private GameMatch(Player humanPlayer){
        chessboard = Chessboard.create();
        chessboard.addLabels();
        chessboard.addFields();
        setHuman(humanPlayer);
    }
    
    public static void startMatchWithHumansWhite(){
        WHITE_PLAYER = new WhitePlayer();
        BLACK_PLAYER = new BlackPlayer();
        new GameMatch(WHITE_PLAYER).start();
    }
    
    public static void startMatchWithHumansBlack(JFrame w){
        new GameMatch(BLACK_PLAYER).start();
    }
    
    public static void enabledPawnToMove(Pawn pawn, boolean enabled){
        if(Rolles.hasMove(pawn)){
            if(pawn == null) System.err.println("Pawn is null!");
            pawn.getLocation().getChessFieldFromChessboard().setEnabled(enabled);
        }
    }
    
    public static void enabledPawnToBeat(Pawn pawn, boolean enabled){
        if(Rolles.hasBeat(pawn)){
            if(pawn instanceof King)
                pawn.getLocation().getChessFieldFromChessboard().setImportantEnabled(enabled);
            else if(!pawn.getPlayer().isKingBeat()){
                pawn.getLocation().getChessFieldFromChessboard().setImportantEnabled(enabled);
            }
        }
    }
    
    public static void enabledChessboard(boolean enabled){
        for(ChessField field : Chessboard.getChessFields()) field.setEnabled(enabled);
    }
    
    public static void enabledPlayer(Player player, boolean enabled){
        if(hasBeats(player)){
            for(Pawn p : player.getPawns()) enabledPawnToBeat(p, enabled);
        }
        else for(Pawn p : player.getPawns()) enabledPawnToMove(p, enabled);
    }
    
    public static void enabledHumanPlayer(boolean enabled){
        enabledPlayer(getHumanPlayer(), enabled);
    }
    
    public static void startDefaultMatch(){
        startMatchWithHumansWhite();
    }
    
    private static boolean hasBeats(Player p){
        return p.hasBeats();
    }
    
    public static Player getHumanPlayer(){
        if(WHITE_PLAYER.isHuman()) return WHITE_PLAYER;
        else if(BLACK_PLAYER.isHuman()) return BLACK_PLAYER;
        return null;
    }
    
    public static Player getComputerPlayer(){
        if(!WHITE_PLAYER.isHuman()) return WHITE_PLAYER;
        else if(!BLACK_PLAYER.isHuman()) return BLACK_PLAYER;
        return null;
    }
    
    public void start() {
        MainGameFrame.setMainPanel(chessboard);
        WHITE_PLAYER.addPawnsToChessboard();
        BLACK_PLAYER.addPawnsToChessboard();
        enabledChessboard(false);
        enabledPlayer(getHumanPlayer(), true);
    }

    public void setComputer(Player computer) {
        if(getComputerPlayer() != null) getComputerPlayer().setHumanPlayer();
        computer.setComputerPlayer();
    }

    public void setHuman(Player human) {
        if(getHumanPlayer() != null) getHumanPlayer().setComputerPlayer();
        human.setHumanPlayer();
    }
    
    public static void finallyGameMatch(){
        MainGameFrame.setMainPanel(new MainMenuPanel());
    }
}
