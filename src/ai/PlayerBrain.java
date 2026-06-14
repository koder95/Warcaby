/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import java.util.ArrayList;
import objects.*;
import system.Beat;
import system.CheckersDirection;
import system.Movement;
import system.Rolles;
import system.match.TourImplement;

    
/**
 *
 * @author Kamil
 */
public class PlayerBrain {
    
    private Player player;

    public PlayerBrain(Player player) {
        this.player = player;
    }
    
    public Pawn getTheBestPawnToBeat(){
        Pawn[] canBeat = getPawnsThatCanBeat();
        Pawn theBest = canBeat[0];
        for(Pawn p : canBeat)
            if(Scoring.giveScoresForBeats(p) >= Scoring.giveScoresForBeats(theBest))
                theBest = p;
        return theBest;
    }
    
    public Pawn getTheBestPawnToMove(){
        Pawn[] canMove = getPawnsThatCanMove();
        Pawn theBest = canMove[0];
        for(Pawn p : canMove)
            if(Scoring.giveScoresForMoves(p) >= Scoring.giveScoresForMoves(theBest))
                theBest = p;
        return theBest;
    }
    
    public Pawn[] getPawnsThatCanBeat(){
        ArrayList<Pawn> canBeat = new ArrayList<>();
        for(Pawn p : player.getPawns()) if(Rolles.hasBeat(p)) canBeat.add(p);
        return canBeat.toArray(new Pawn[canBeat.size()]);
    }
    
    public Pawn[] getPawnsThatCanMove(){
        ArrayList<Pawn> canMove = new ArrayList<>();
        for(Pawn p : player.getPawns()) if(Rolles.hasMove(p)) canMove.add(p);
        return canMove.toArray(new Pawn[canMove.size()]);
    }

    public TourImplement getTheBestTurn() {
        if(getPawnsThatCanBeat() != null && getPawnsThatCanBeat().length > 0){
            return getTheBestBeat(getTheBestPawnToBeat());
        } else {
            return getTheBestMove(getTheBestPawnToMove());
        }
    }

    public TourImplement getTheBestBeat(Pawn p) {
        Beat[] beats = Rolles.getBeatsForPawn(p);
        Beat theBest = beats[0];
        for(Beat b : beats)
            if(Scoring.giveScoresToField(p, b.getEndPoint()) >= Scoring.giveScoresToField(p, theBest.getEndPoint()))
                theBest = b;
        return theBest;
    }

    public TourImplement getTheBestMove(Pawn p) {
        Movement[] moves = Rolles.getMovementsForPawn(p);
        Movement theBest = moves[0];
        for(Movement m : moves)
            if(Scoring.giveScoresToField(p, m.getEndPoint()) >= Scoring.giveScoresToField(p, theBest.getEndPoint()))
                theBest = m;
        return theBest;
    }
    
    public static class Scoring {

        private Scoring() {
        }

        public static final int SCORE_FOR_BEAT_NOW = 3;
        public static final int SCORE_FOR_BEAT_IN_NEXT_TOUR = 2;
        public static final int SCORE_FOR_BEAT_IN_TWO_TOUR = 1;
        public static final int SCORE_FOR_MOVE_TO_CLEAR_AREA_NOW = 3;
        public static final int SCORE_FOR_MOVE_TO_CLEAR_AREA_IN_NEXT_TOUR = 2;
        public static final int SCORE_FOR_MOVE_TO_BEATING_FIELD = 1;
        public static final int SCORE_FOR_MOVE_TO_BLOCKING_PAWN = 1;
        public static final int SCORE_FOR_MOVE_TO_SURRENDER = 3;

        public static int giveScoresForBeats(Pawn p){
            Beat[] beats = Rolles.getBeatsForPawn(p);
            int scores = beats.length * SCORE_FOR_BEAT_NOW;
            for(Beat b : beats) scores += giveScoresToField(p, b.getEndPoint());
            System.out.println("Scores for " + p.getLocation() + ": " + scores);
            return scores;
        }

        private static int giveScoresForBeatsFinally(Pawn p){
            int scores = 0;
            Beat[] beats = Rolles.getBeatsForPawn(p);
            scores += beats.length*SCORE_FOR_BEAT_NOW;
            return scores;
        }

        public static int giveScoresForMoves(Pawn p){
            Movement[] moves = Rolles.getMovementsForPawn(p);
            int scores = moves.length;
            for(Movement m : moves) scores += giveScoresToField(p, m.getEndPoint());
            System.out.println("Scores for " + p.getLocation() + ": " + scores);
            return scores;
        }

        private static int giveScoresForMovesFinally(Pawn p, ChessLocationPoint clp){
            int scores = 0;
            Pawn test = new Pawn(0, null, clp);
            test.setPlayer(p.getPlayer());
            for(CheckersDirection cd : CheckersDirection.CHECKERS_DIRECTIONS){
                if(Chessboard.isNextChessLocationPoint(clp, cd, 1))
                    if(test.getNextPawn(cd) != null){
                        if(test.isOpponentOf(test.getNextPawn(cd)))
                            scores -= SCORE_FOR_BEAT_NOW;
                    } else
                        scores += SCORE_FOR_MOVE_TO_CLEAR_AREA_IN_NEXT_TOUR;
            }
            return scores;
        }

        public static int giveScoresToField(Pawn p, ChessLocationPoint clp){
            int scores = 0;
            Pawn test = new Pawn(0, null, clp);
            test.setPlayer(p.getPlayer());
            for(CheckersDirection cd : CheckersDirection.CHECKERS_DIRECTIONS){
                if(Chessboard.isNextChessLocationPoint(clp, cd, 1))
                    if(test.getNextPawn(cd) != null){
                        if(test.isOpponentOf(test.getNextPawn(cd)))
                            scores -= SCORE_FOR_BEAT_NOW;
                    } else {
                        scores += SCORE_FOR_MOVE_TO_CLEAR_AREA_NOW;
                        scores += giveScoresForMovesFinally(test, Chessboard.getNextChessLocationPoint(clp, cd, 1));
                    }
            }
            return scores;
        }
    }
}
