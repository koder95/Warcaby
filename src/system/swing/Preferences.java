/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system.swing;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Kamil
 */
public class Preferences extends MenuPanel {

    private static String[] menu = {
        "Ustaw kolory graczy",
        "Ustaw poziom trudności",
        "Wstecz"
    };
    
    public Preferences(JLabel title, JButton[] menu) {
        super(title, menu);
        setOpaque(true);
        this.title.setForeground(Color.white);
    }

    public Preferences(String title, String[] menuLabels) {
        super(title, menuLabels);
        setOpaque(true);
        this.title.setForeground(Color.white);
    }
    
    public Preferences(JButton[] menu) {
        this(new JLabel("Ustawienia"), menu);
    }
    
    public Preferences(String[] labels) {
        this("Ustawienia", labels);
    }
    
    public Preferences() {
        this(menu);
    }

    @Override
    public void addActionListeners() {
        getMenuButton(0).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Preferences.this, "Wersja demonstracyjna nie posiada tej opcji", "Demo", JOptionPane.OK_OPTION);
            }
        });
        getMenuButton(1).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Preferences.this, "Wersja demonstracyjna nie posiada tej opcji", "Demo", JOptionPane.OK_OPTION);
            }
        });
        getMenuButton(2).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                show(new MainMenuPanel());
            }
        });
    }
}
