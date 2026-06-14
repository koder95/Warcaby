/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system.match;

import objects.Chessboard;
import objects.Pawn;
import system.Beat;
import system.GameMatch;
import system.Rolles;

/**
 *
 * @author Kamil
 */
class Tour {
    
    TourImplement turn;

    public Tour(TourImplement turn) {
        this.turn = turn;
    }
    
    public void play(){
        Chessboard.reset();
        Pawn sp = turn.getPawnWhichStartTour();
        turn.turn();
        if(turn instanceof Beat && Rolles.hasBeat(turn.getPawnWhichStartTour())){
            if(turn.getPawnWhichStartTour().getPlayer().isHuman()){
                Pawn p = turn.getPawnWhichStartTour();
                GameMatch.enabledPawnToBeat(p, true);
            }
            else {
                Pawn p = turn.getPawnWhichStartTour();
                destroy();
                turn = p.getPlayer().getPlayerBrain().getTheBestBeat(p);
                play();
            }
        } else destroy();
        if(Rolles.isInLastField(sp)) sp.transformToKing();
        GameMatch.enabledChessboard(false);
    }

    private void destroy() {
        turn = null;
        System.out.println();
    }
}
