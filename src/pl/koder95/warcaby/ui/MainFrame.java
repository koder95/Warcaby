/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.koder95.warcaby.ui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 *
 * @author Kamil Mularski
 */
public class MainFrame extends JFrame {

    public MainFrame() throws HeadlessException {
        super("Warcaby");
        this.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        this.setType(Type.NORMAL);
        this.getContentPane().setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().add(new Chessboard());
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    public static void main(final String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            EventQueue.invokeAndWait(new Runnable() {

                @Override
                public void run() {
                    if (args.length != 0 && args[0].equalsIgnoreCase("-fs"))
                        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(new MainFrame());
                    else new MainFrame().setVisible(true);
                }
            });
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException | InterruptedException | InvocationTargetException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
