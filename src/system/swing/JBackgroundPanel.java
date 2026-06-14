/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system.swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.PopupMenu;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Kamil
 */
public class JBackgroundPanel extends JPanel {
    
    private static final ImageIcon BACKGROUND = new ImageIcon(ClassLoader.getSystemResource("system/resources/background.jpg"));
    
    public JBackgroundPanel(Icon i) {
        setLayout(null);
        JLabel image = new JLabel(i);
        image.setSize(i.getIconWidth(), i.getIconHeight());
        image.setLocation(0, 0);
        super.add(image);
        setLocation(0, 0);
        setSize(image.getSize());
    }

    public JBackgroundPanel() {
        this(BACKGROUND);
    }

    @Override
    public Component add(Component comp) {
        System.err.println("Komponent nie może być dodany do panelu JBackground! Metoda zwróci <nulltype>.");
        return null;
    }

    @Override
    public void add(PopupMenu popup) {
        System.err.println("Komponent nie może być dodany do panelu JBackground!");
    }

    @Override
    public void add(Component comp, Object constraints) {
        System.err.println("Komponent nie może być dodany do panelu JBackground!");
    }

    @Override
    public Component add(Component comp, int index) {
        System.err.println("Komponent nie może być dodany do panelu JBackground! Metoda zwróci <nulltype>.");
        return null;
    }

    @Override
    public Component add(String name, Component comp) {
        System.err.println("Komponent nie może być dodany do panelu JBackground! Metoda zwróci <nulltype>.");
        return null;
    }

    @Override
    public void add(Component comp, Object constraints, int index) {
        System.err.println("Komponent nie może być dodany do panelu JBackground!");
    }
}
