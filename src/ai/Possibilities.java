/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ai;

import java.util.ArrayList;
import objects.ChessLocationPoint;
import objects.Pawn;
import system.CheckersDirection;

/**
 *
 * @author Kamil Mularski
 */
public class Possibilities {
    
    private final ChessLocationPoint pawn;

    public Possibilities(ChessLocationPoint pawn) {
        this.pawn = pawn;
    }
    
    private ChessLocationPoint[] getNeighbouringFields() {
        ArrayList<ChessLocationPoint> neighbour = new ArrayList<>();
        neighbour.add(pawn.getNextChessLocationPoint(CheckersDirection.LEFT_TOP));
        neighbour.add(pawn.getNextChessLocationPoint(CheckersDirection.RIGHT_TOP));
        neighbour.add(pawn.getNextChessLocationPoint(CheckersDirection.RIGHT_BOTTOM));
        neighbour.add(pawn.getNextChessLocationPoint(CheckersDirection.LEFT_BOTTOM));
        return neighbour.toArray(new ChessLocationPoint[neighbour.size()]);
    }
    
    private ChessLocationPoint[] getNeightbouringEmptyFields() {
        ArrayList<ChessLocationPoint> neighbour = new ArrayList<>();
        for (ChessLocationPoint point: getNeighbouringFields()) if (!point.isPawnInChessField()) neighbour.add(point);
        return neighbour.toArray(new ChessLocationPoint[neighbour.size()]);
    }
    
    private Pawn[] getNeightbouringPawns() {
        ArrayList<Pawn> neighbour = new ArrayList<>();
        for (ChessLocationPoint point: getNeighbouringFields()) if (point.isPawnInChessField()) neighbour.add(point.getPawnInChessField());
        return neighbour.toArray(new Pawn[neighbour.size()]);
    }
    
    private Pawn[] getEmenyNeightbouringPawns() {
        ArrayList<Pawn> neighbour = new ArrayList<>();
        for (Pawn pawn: getNeightbouringPawns()) if (pawn.isOpponentOf(this.pawn.getPawnInChessField())) neighbour.add(pawn);
        return neighbour.toArray(new Pawn[neighbour.size()]);
    }
    
    
}
