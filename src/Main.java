
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import pl.koder95.warcaby.ui.Chessboard;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kamil Mularski
 */
public class Main {
    private JFrame frame = new JFrame();
    private JLayeredPane lpane = new JLayeredPane();
    private JPanel panelBlue = new Chessboard.Fields();
    private JPanel panelGreen = new Chessboard.Pawns();
    public Main()
    {
        frame.setDefaultCloseOperation(3);
        frame.setPreferredSize(new Dimension(600, 400));
        frame.setLayout(new BorderLayout());
        frame.add(lpane, BorderLayout.CENTER);
        //lpane.setBounds(0, 0, 600, 400);
        panelBlue.setBackground(Color.BLUE);
        panelBlue.setBounds(0, 0, 900, 800);
        panelBlue.setOpaque(true);
        panelGreen.setBackground(Color.GREEN);
        panelGreen.setBounds(0, 0, 900, 800);
        panelGreen.setOpaque(false);
        lpane.add(panelBlue, new Integer(0), 0);
        lpane.add(panelGreen, new Integer(1), 0);
        frame.pack();
        frame.setVisible(true);
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Main();
    }

}
