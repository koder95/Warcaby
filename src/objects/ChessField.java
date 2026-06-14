/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import ai.PlayerBrain;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import system.Beat;
import system.GameMatch;
import system.Movement;
import system.Rolles;
import system.match.ToursSeries;

/**
 *
 * @author Kamil
 */
public class ChessField extends JButton {
    
    private static int defaultWidth = 80;
    private static int defaultHeight = 80;
    private Pawn pawn;
    private ChessLocationPoint chessLocationPoint;
    private Movement move;
    private Beat beat;
    private boolean important;
    
    public static int getDefaultHeight() {
        return defaultHeight;
    }

    public static int getDefaultWidth() {
        return defaultWidth;
    }
    
    protected static void setDefaultHeight(int defaultHeight) {
        ChessField.defaultHeight = defaultHeight;
    }

    protected static void setDefaultWidth(int defaultWidth) {
        ChessField.defaultWidth = defaultWidth;
    }

    public ChessField(Color background, int x, int y, int width, int height, Color buttonPressedColor) {
        setBackground(background);
        setLocation(x, y);
        setSize(width, height);
        if(buttonPressedColor == null)
            setUI(new ChessFieldsUI());
        else setUI(new ChessFieldsUI(buttonPressedColor));
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent me) {
                super.mouseEntered(me);
                System.out.println(PlayerBrain.Scoring.giveScoresToField(new Pawn(0, GameMatch.getHumanPlayer(), null), chessLocationPoint));
            }
            
        });
        addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if(command.isEmpty()) command = "Empty";
                System.out.println(command);
                if(command.equalsIgnoreCase("Empty")){
                    GameMatch.enabledChessboard(false);
                    if(Rolles.hasBeat(pawn)){
                        Beat[] beats = Rolles.getBeatsForPawn(pawn);
                        System.out.println("length = " + beats.length);
                        for(Beat beat : beats){
                            beat.getEndPoint().getChessFieldFromChessboard().setImportantEnabled(true);
                            beat.getEndPoint().getChessFieldFromChessboard().setActionCommand("beat");
                            beat.getEndPoint().getChessFieldFromChessboard().beat = beat;
                        }
                    } else {
                        Movement[] moves = Rolles.getMovementsForPawn(pawn);
                        System.out.println("length = " + moves.length);
                        for(Movement move : moves){
                            ChessField cf = move.getEndPoint().getChessFieldFromChessboard();
                            cf.setEnabled(true);
                            cf.setActionCommand("move");
                            cf.move = move;
                        }
                    }
                }
                else if(command.equals("move")){
                    setActionCommand("Empty");
                    GameMatch.enabledChessboard(false);
                    if(move.getEndPoint().equals(getChessLocationPoint())){
                        ToursSeries ts = ToursSeries.create(move);
                        ts.play();
                        repaint();
                    }
                    Chessboard.reset();
                    GameMatch.enabledHumanPlayer(true);
                }
                else if(command.equals("beat")){
                    setActionCommand("Empty");
                    GameMatch.enabledChessboard(false);
                    if(beat.getEndPoint().equals(getChessLocationPoint())){
                        ToursSeries ts = ToursSeries.create(beat);
                        ts.play();
                        repaint();
                    }
                    Chessboard.reset();
                    GameMatch.enabledHumanPlayer(true);
                }
            }
        });
    }

    public ChessField(Color background, int x, int y, int width, int height) {
        this(background, x, y, width, height, null);
    }

    public ChessField(Color background, int x, int y) {
        this(background, x, y, getDefaultWidth(), getDefaultHeight());
    }

    public ChessField(Color background) {
        this(background, 0, 0);
    }

    public ChessField() {
        this(Color.green);
    }

    public ChessLocationPoint getChessLocationPoint() {
        return chessLocationPoint;
    }

    public void setChessLocationPoint(ChessLocationPoint chessLocationPoint) {
        this.chessLocationPoint = chessLocationPoint;
    }

    public void setPawn(Pawn pawn) {
        this.pawn = pawn;
        this.repaint();
    }

    public void removePawn() {
        this.setPawn(null);
        this.repaint();
    }

    public Pawn getPawn() {
        return pawn;
    }

    private void setImportant(boolean important) {
        this.important = important;
    }

    @Override
    public void setEnabled(boolean b) {
        super.setEnabled(b);
        setImportant(false);
    }
    
    public void setImportantEnabled(boolean b) {
        super.setEnabled(b);
        setImportant(true);
        System.out.println("Włączono z ważnego powodu: " + this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(important){
            g.setColor(Color.red);
            g.drawRoundRect(2, 2, getWidth()-4, getHeight()-4, 10, 10);
        }
        if(getPawn() != null){
            g.drawImage(getPawn().getImage(), (getWidth() - getPawn().getImage().getWidth(this))/2,
                    (getHeight() - getPawn().getImage().getHeight(this))/2, this);
        }
    }

    public void obliviate(){
        move = null;
        beat = null;
        important = false;
        setActionCommand("Empty");
    }
    
    @Override
    public String toString() {
        return getClass().getName() + "("+ getLocation() + "," + getChessLocationPoint() + "," + getPawn() + ")";
    }
}
