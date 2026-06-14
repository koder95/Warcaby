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
public final class BlackPlayer extends Player {

    public BlackPlayer() {
        super("system/resources/pawnBlack.png");
        addPawn(this, 'B', 8);
        addPawn(this, 'D', 8);
        addPawn(this, 'F', 8);
        addPawn(this, 'H', 8);
        addPawn(this, 'A', 7);
        addPawn(this, 'C', 7);
        addPawn(this, 'E', 7);
        addPawn(this, 'G', 7);
        addPawn(this, 'B', 6);
        addPawn(this, 'D', 6);
        addPawn(this, 'F', 6);
        addPawn(this, 'H', 6);
    }
    
}
