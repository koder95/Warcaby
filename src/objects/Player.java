/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import objects.players.WhitePlayer;
import system.Beat;
import system.Rolles;
import ai.PlayerBrain;

/**
 *
 * @author Kamil
 */
public class Player {
    
    private String path;
    private Pawn[] pawns;
    private boolean human = false;
    private PlayerBrain brain;

    public Player(String path) {
        this(path, new Pawn[0]);
    }

    public Player(String path, Pawn[] pawns) {
        this.path = path;
        this.pawns = pawns;
        this.brain = new PlayerBrain(this);
    }
    
    protected void addPawn(Player player, char column, int row){
        int index = pawns.length;
        Pawn[] pawns = this.pawns;
        this.pawns = new Pawn[index+1];
        for(int i = 0; i < pawns.length; i++) this.pawns[i] = pawns[i];
        this.pawns[index] = new Pawn(index, player, new ImageIcon(ClassLoader.getSystemResource(path)).getImage(), new ChessLocationPoint(column, row));
    }

    public Pawn[] getPawns() {
        return pawns;
    }

    public void setPawns(Pawn[] pawns) {
        this.pawns = pawns;
    }
    
    public void addPawnsToChessboard(){
        for(Pawn p : this.getPawns()) Chessboard.getChessField(p.getLocation().getColumn(), p.getLocation().getRow()).setPawn(p);
    }

    public void setHumanPlayer() {
        this.human = true;
    }

    public void setComputerPlayer() {
        this.human = false;
    }

    public boolean isHuman() {
        return human;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "(path=" + path + ", pawns=" + pawns + ")";
    }

    public String toStringMessage() {
        if(this instanceof WhitePlayer) return isHuman() ? "Gracz biały(człowiek)" : "Gracz biały(komputer)";
        else return isHuman() ? "Gracz czarny(człowiek)" : "Gracz czarny(komputer)";
    }

    public PlayerBrain getPlayerBrain() {
        return this.brain;
    }
    
    public boolean removePawn(Pawn p){
        ArrayList<Pawn> pawns = new ArrayList<>();
        pawns.addAll(Arrays.asList(getPawns()));
        if(!pawns.contains(p)) return false;
        if(pawns.remove(p)){
            System.out.println(toStringMessage() + " traci pionka");
            setPawns(pawns.toArray(new Pawn[pawns.size()]));
            return true;
        }
        else return false;
    }
    
    public boolean hasBeats(){
        for(Pawn pawn : getPawns()) if(Rolles.hasBeat(pawn))
            return true;
        return false;
    }
    
    public boolean hasMovements(){
        for(Pawn pawn : getPawns()) if(Rolles.hasMove(pawn))
            return true;
        return false;
    }
    
    public boolean isKingBeat(){
        for(Pawn pawn : pawns){
            if(pawn instanceof King)
                if(Rolles.hasBeat(pawn)) return true;
        }
        return false;
    }
}
