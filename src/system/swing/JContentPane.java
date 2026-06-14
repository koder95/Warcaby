/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system.swing;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Kamil
 */
public class JContentPane extends JPanel {
    
    private JBackgroundPanel backgroundPanel = new JBackgroundPanel();
    private JMainPanel mainPanel = new JMainPanel();
    
    public JContentPane(int width, int height){
        setLayout(null);
        setSize(width, height);
        this.backgroundPanel = new JBackgroundPanel();
        add(this.backgroundPanel, 0);
        if(MainGameFrame.isVisibleFrame()){
            setComponentZOrder(this.mainPanel, 0);
            setComponentZOrder(this.backgroundPanel, 1);
        }
        MainGameFrame.repaintContent();
        this.backgroundPanel.repaint();
        this.mainPanel = new MainMenuPanel();
        add(this.mainPanel, 1);
        if(MainGameFrame.isVisibleFrame()){
            setComponentZOrder(this.mainPanel, 0);
            setComponentZOrder(this.backgroundPanel, 1);
        }
        MainGameFrame.repaintContent();
        this.mainPanel.repaint();
        setComponentZOrder(this.mainPanel, 0);
        setComponentZOrder(this.backgroundPanel, 1);
    }
    
    public JContentPane(Dimension size){
        this(size.width, size.height);
    }
    
    JContentPane(JFrame frame){
        this(frame.getSize());
    }

    public JBackgroundPanel getBackgroundPanel() {
        return backgroundPanel;
    }

    public JMainPanel getMainPanel() {
        return mainPanel;
    }

    public synchronized void setBackgroundPanel(JBackgroundPanel backgroundPanel) {
        if(this.backgroundPanel != null) remove(getBackgroundPanel());
        this.backgroundPanel = backgroundPanel;
        add(this.backgroundPanel, 0);
        if(MainGameFrame.isVisibleFrame()){
            setComponentZOrder(this.mainPanel, 0);
            setComponentZOrder(this.backgroundPanel, 1);
        }
        MainGameFrame.repaintContent();
        this.backgroundPanel.repaint();
    }

    public synchronized void setMainPanel(JMainPanel mainPanel) {
        if(this.mainPanel != null) remove(getMainPanel());
        this.mainPanel = mainPanel;
        add(this.mainPanel, 1);
        if(MainGameFrame.isVisibleFrame()){
            setComponentZOrder(this.mainPanel, 0);
            setComponentZOrder(this.backgroundPanel, 1);
        }
        MainGameFrame.repaintContent();
        this.mainPanel.repaint();
    }
}
