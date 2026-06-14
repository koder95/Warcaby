/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects.players;

import objects.Pawn;
import objects.Player;

/**
 *
 * @author Kamil
 */
public final class WhitePlayer extends Player {

    public WhitePlayer() {
        super("system/resources/pawnWhite.png");
        addPawn(this, 'A', 1);
        addPawn(this, 'C', 1);
        addPawn(this, 'E', 1);
        addPawn(this, 'G', 1);
        addPawn(this, 'B', 2);
        addPawn(this, 'D', 2);
        addPawn(this, 'F', 2);
        addPawn(this, 'H', 2);
        addPawn(this, 'A', 3);
        addPawn(this, 'C', 3);
        addPawn(this, 'E', 3);
        addPawn(this, 'G', 3);
    }
    
}
