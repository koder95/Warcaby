/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system.swing;

import java.awt.Color;
import java.awt.LayoutManager;
import javax.swing.JPanel;

/**
 *
 * @author Kamil
 */
public class JMainPanel extends JPanel {

    public JMainPanel() {
        setLayout(null);
        setOpaque(false);
        setLocation(0, 0);
        setSize(800, 800);
        setBackground(new Color(0, 0, 0, 153));
    }

    JMainPanel(LayoutManager mgr) {
        this();
        setLayout(mgr);
    }

    public void show(JMainPanel p){
        MainGameFrame.setMainPanel(p);
    }
}
