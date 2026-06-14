/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.util.ArrayList;
import objects.*;
import objects.players.BlackPlayer;
import objects.players.WhitePlayer;

/**
 *
 * @author Kamil
 */
public final class Rolles {

    private Rolles() {
    }
    
    public static Movement[] getMovementsForPawn(Pawn p){
        if(!hasMove(p)) return new Movement[0];
        ArrayList<Movement> moves = new ArrayList<>();
        for(CheckersDirection cd : CheckersDirection.CHECKERS_DIRECTIONS){
            if(p instanceof King){
                for(int length = 1; length < 8; length++){
                    if(Chessboard.isNextChessLocationPoint(p.getLocation(), cd, length)){
                        ChessLocationPoint end = Chessboard.getNextChessLocationPoint(p.getLocation(), cd, length);
                        if(!end.isPawnInChessField()) moves.add(new Movement(p.getLocation(), end));
                        else break;
                    }
                }
            }
            else {
                if(Chessboard.isNextChessLocationPoint(p.getLocation(), cd, 1)){
                    ChessLocationPoint clp = Chessboard.getNextChessLocationPoint(p.getLocation(), cd, 1);
                    if(!clp.isPawnInChessField()){
                        if(p.getPlayer().isHuman() && cd.getTranslateY() == 1) moves.add(new Movement(p.getLocation(), clp));
                        if(!p.getPlayer().isHuman() && cd.getTranslateY() == -1) moves.add(new Movement(p.getLocation(), clp));
                    }
                }
            }
        }
        return moves.toArray(new Movement[moves.size()]);
    }
    
    public static Beat[] getBeatsForPawn(Pawn p){
        if(!hasBeat(p)) return new Beat[0];
        ArrayList<Beat> beats = new ArrayList<>();
        for(CheckersDirection cd : CheckersDirection.CHECKERS_DIRECTIONS){
            if(p instanceof King){
                for(int length = 1; length < 8; length++)
                    if(Chessboard.isNextChessLocationPoint(p.getLocation(), cd, length))
                        if(Chessboard.getNextChessLocationPoint(p.getLocation(), cd, length).isPawnInChessField()){
                            Pawn pawn = Chessboard.getNextChessLocationPoint(p.getLocation(), cd, length).getPawnInChessField();
                            if(pawn.isOpponentOf(p))
                                for(int length2 = 1; length2 < 8; length2++)
                                    if(Chessboard.isNextChessLocationPoint(p.getLocation(), cd, length+length2)){
                                        ChessLocationPoint clp = Chessboard.getNextChessLocationPoint(p.getLocation(), cd, length+length2);
                                        if(!clp.isPawnInChessField()) beats.add(new Beat(p.getLocation(), clp, pawn));
                                        else break;
                                    }
                            else break;
                        }
            }
            else {
                if(Chessboard.isNextChessLocationPoint(p.getLocation(), cd, 2)){
                    ChessLocationPoint clp = Chessboard.getNextChessLocationPoint(p.getLocation(), cd, 2);
                    Pawn beating = p.getNextPawn(cd);
                    if(beating != null) if(beating.isOpponentOf(p))
                        if(clp.getChessFieldFromChessboard().getPawn() == null){
                            Beat b = new Beat(p.getLocation(), clp, beating);
                            beats.add(b);
                        }
                }
            }
        }
        return beats.toArray(new Beat[beats.size()]);
    }
    
    public static boolean hasMove(Pawn p){
        if(p.getPlayer() == null) System.out.println("Player is null!");
        for(CheckersDirection cd = CheckersDirection.getFirst(); cd != null; cd = cd.getNext()){
            if(p instanceof King){
                for(int length = 1; length < 8; length++){
                    if(Chessboard.isNextChessLocationPoint(p.getLocation(), cd, length)){
                        if(!Chessboard.getNextChessLocationPoint(p.getLocation(), cd, length).isPawnInChessField()) return true;
                        else break;
                    }
                }
            }
            else {
                if(p.getPlayer().isHuman() && cd.getDirection() < CheckersDirection.LEFT_BOTTOM.getDirection()){
                    if(Chessboard.isNextChessLocationPoint(p.getLocation(), cd, 1)) if(p.getNextPawn(cd) == null) return true;
                }
                else if(!p.getPlayer().isHuman() && cd.getDirection() >= CheckersDirection.LEFT_BOTTOM.getDirection()){
                    if(Chessboard.isNextChessLocationPoint(p.getLocation(), cd, 1)) if(p.getNextPawn(cd) == null) return true;
                }
            }
        }
        return false;
    }
    
    public static boolean hasBeat(Pawn p){
        for(CheckersDirection cd = CheckersDirection.getFirst(); cd != null; cd = cd.getNext()){
            if(p instanceof King)
                for(int length = 1; length < 8; length++){
                    if(Chessboard.isNextChessLocationPoint(p.getLocation(), cd, length))
                        if(Chessboard.getNextChessLocationPoint(p.getLocation(), cd, length).isPawnInChessField()){
                            if(Chessboard.getNextChessLocationPoint(p.getLocation(), cd, length).getPawnInChessField().isOpponentOf(p))
                                if(Chessboard.isNextChessLocationPoint(p.getLocation(), cd, length+1))
                                    if(!Chessboard.getNextChessLocationPoint(p.getLocation(), cd, length+1).isPawnInChessField()){
                                        System.out.println("Damka " + p.getLocation().toStringMessage() + " ma bicie.");
                                        return true;
                                    }
                            break;
                        }
                }
            else
                if(Chessboard.isNextChessLocationPoint(p.getLocation(), cd, 2)) if(p.getNextPawn(cd, 2) == null) if(p.getNextPawn(cd) != null)
                    if(p.isOpponentOf(p.getNextPawn(cd))){
                        System.out.println("Pionek " + p.getLocation().toStringMessage() + " ma bicie.");
                        return true;
                    }
        }
        return false;
    }
    
    public static boolean isInLastField(Pawn p){
        if(p.getPlayer() instanceof WhitePlayer){
            return (p.getLocation().getRow() == 8);
        }
        else if(p.getPlayer() instanceof BlackPlayer){
            return (p.getLocation().getRow() == 1);
        }
        return false;
    }
}
