/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system.swing;

import javax.swing.*;

/**
 *
 * @author Kamil
 */
public class MainGameFrame extends JFrame {
    
    private MainGameFrame(){
        super("Warcaby");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setSize(806, 826);
        setLocationRelativeTo(null);
        setContentPane(new JContentPane(this));
    }
    
    private static final MainGameFrame DEFAULT = new MainGameFrame();
    
    public static void setMainPanel(JMainPanel mainPanel){
        JContentPane newCP = new JContentPane(DEFAULT);
        newCP.setBackgroundPanel(getJContentPane().getBackgroundPanel());
        newCP.setMainPanel(mainPanel);
        DEFAULT.setContentPane(newCP);
    }
    
    public static JMainPanel getMainPanel(){
        return getJContentPane().getMainPanel();
    }
    
    public static void setVisibleFrame(boolean b){
        DEFAULT.setVisible(b);
    }
    
    public static boolean isVisibleFrame(){
        if(DEFAULT == null) return false;
        return DEFAULT.isVisible();
    }
    
    public static void repaintContent(){
        if(isVisibleFrame()) 
            DEFAULT.repaint();
    }
    
    private static JContentPane getJContentPane(){
        if(DEFAULT.getContentPane() instanceof JContentPane)
            return (JContentPane) DEFAULT.getContentPane();
        return null;
    }
}
