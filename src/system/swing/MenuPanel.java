/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system.swing;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

/**
 *
 * @author Kamil
 */
public abstract class MenuPanel extends JMainPanel {

    JLabel title;
    JButton[] menu;
    private static final Font FONT_TO_TITLE = new Font("Calibria", 0, 40);
    
    public MenuPanel(JLabel title, JButton[] menu) {
        this.setLayout(new FlowLayout());
        this.menu = menu;
        this.title = title;
        this.title.setHorizontalAlignment(JLabel.CENTER);
        this.title.setVerticalAlignment(JLabel.BOTTOM);
        this.title.setPreferredSize(new Dimension(500, 150));
        this.title.setFont(FONT_TO_TITLE);
        addActionListeners();
        add(this.title);
        for(JButton button : this.menu){
            button.setPreferredSize(new Dimension(500, 50));
            add(button);
        }
    }
    
    public MenuPanel(String title, String[] menuLabels) {
        this.setLayout(new FlowLayout());
        this.menu = insertLabels(menu, menuLabels);
        for(JButton b : menu)
            System.out.println(b.getText());
        this.title = new JLabel(title);
        this.title.setHorizontalAlignment(JLabel.CENTER);
        this.title.setVerticalAlignment(JLabel.BOTTOM);
        this.title.setPreferredSize(new Dimension(500, 150));
        this.title.setFont(FONT_TO_TITLE);
        addActionListeners();
        add(this.title);
        for(JButton button : this.menu){
            System.out.println("ADD " + button.getText());
            button.setPreferredSize(new Dimension(500, 50));
            add(button);
        }
    }
    
    private JButton createJButton(String s){
        return new JButton(s);
    }
    
    private JButton[] insertLabels(JButton[] buttons, String[] labelTitles){
        ArrayList<JButton> created;
        if(buttons != null)
            created = new ArrayList<>(Arrays.asList(buttons));
        else
            created = new ArrayList<>();
        for(int i = 0; i < labelTitles.length; i++)
            created.add(createJButton(labelTitles[i]));
        buttons = created.toArray(new JButton[created.size()]);
        return buttons;
    }

    public JButton[] getMenuButtons() {
        return menu;
    }

    public JButton getMenuButton(int index) {
        return menu[index];
    }

    public void setMenuButtons(JButton[] buttons) {
        this.menu = buttons;
    }

    public void setMenuButton(int index, JButton button) {
        this.menu[index] = button;
    }
    
    public abstract void addActionListeners();
}
