/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import objects.players.BlackPlayer;
import objects.players.WhitePlayer;
import system.CheckersDirection;

/**
 *
 * @author Kamil
 */
public class Pawn {
    
    private Image image;
    private ChessLocationPoint location;
    private Player player;
    private int index;

    public Pawn(int index, Image image) {
        this(index, image, new ChessLocationPoint());
    }

    public Pawn(int index, Image image, ChessLocationPoint location) {
        this.index = index;
        this.image = image;
        this.location = location;
    }

    public Pawn(int index, Image image, char column, int row) {
        this.index = index;
        this.image = image;
        this.location = new ChessLocationPoint(column, row);
    }

    public Pawn(int index, Player player, Image image) {
        this(index, player, image, new ChessLocationPoint());
    }

    public Pawn(int index, Player player, Image image, ChessLocationPoint location) {
        this.player = player;
        this.image = image;
        this.location = location;
    }

    public Pawn(int index, Player player, Image image, char column, int row) {
        this(index, player, image, new ChessLocationPoint(column, row));
    }

    public Image getImage() {
        return image;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setLocation(ChessLocationPoint location) {
        this.location = location;
    }

    public ChessLocationPoint getLocation() {
        return location;
    }
    
    public void move(ChessLocationPoint clp){
        if(clp == null){ System.err.println("ChessLocationPoint nie może być null!"); return; }
        move(clp.getColumn(), clp.getRow());
    }
    
    public void move(char column, int row){
        Chessboard.getChessField(location.getColumn(), location.getRow()).removePawn();
        this.location.move(column, row);
        Chessboard.getChessField(column, row).setPawn(this);
    }

    public void destroy(){
        if(this.player.removePawn(this));
        this.image = null;
        this.index = 0;
        this.location.getChessFieldFromChessboard().setPawn(null);
        this.location = null;
        this.player = null;
    }
    
    public int getIndex() {
        return index;
    }
    
    public Pawn getNextPawn(CheckersDirection direction, int length){
        return Chessboard.getNextChessLocationPoint(location, direction, length).getChessFieldFromChessboard().getPawn();
    }
    
    public Pawn getNextPawn(CheckersDirection direction){
        return getNextPawn(direction, 1);
    }
    

    @Override
    public String toString() {
        return this.getClass().getName() + "(index=" + index + ", player=" + player
                + ", chessLocationPoint=" + location + ", image=" + image + ")";
    }
    
    public boolean isOpponentOf(Pawn p){
        return (getPlayer().isHuman() && !p.getPlayer().isHuman()) || (!getPlayer().isHuman() && p.getPlayer().isHuman());
    }
    
    public King toKing(){
        if(player instanceof WhitePlayer) return new King(this, "system/resources/kingWhite.png");
        return new King(this, "system/resources/kingBlack.png");
    }
    
    public void transformToKing(){
        King k = toKing();
        ArrayList<Pawn> pawns = new ArrayList<>();
        pawns.addAll(Arrays.asList(k.getPlayer().getPawns()));
        pawns.add(pawns.indexOf(this), k);
        Player pl = k.getPlayer();
        k.getLocation().getChessFieldFromChessboard().setPawn(k);
        pl.setPawns(pawns.toArray(new Pawn[pawns.size()]));
    }
}