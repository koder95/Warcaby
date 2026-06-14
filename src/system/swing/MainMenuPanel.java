/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import system.GameMatch;

/**
 *
 * @author Kamil
 */
public class MainMenuPanel extends MenuPanel {
    
    private static String[] menu = {
        "Rozpocznij grę",
        "Ustawienia",
        "O grze",
        "Zakończ"
    };
    
    public MainMenuPanel() {
        super("Warcaby", menu);
    }
    
    public void addActionListeners(){
        getMenuButton(0).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Nowa gra została rozpoczęta.");
                GameMatch.startDefaultMatch();
            }
        });
        getMenuButton(1).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                show(new Preferences());
            }
        });
        getMenuButton(2).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                show(new AboutPanel());
            }
        });
        getMenuButton(3).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}