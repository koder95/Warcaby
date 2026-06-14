
import system.swing.MainMenuPanel;
import system.swing.MainGameFrame;
import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.koder95.warcaby.ui.MainFrame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kamil
 */
public class Test {

    public static void main(String[] args) {
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        try {
//            EventQueue.invokeAndWait(new Runnable() {
//
//                @Override
//                public void run() {
//                    MainGameFrame.setVisibleFrame(true);
//                }
//            });
//        } catch (InterruptedException | InvocationTargetException ex) {
//            Logger.getGlobal().log(Level.SEVERE, ex.getLocalizedMessage());
//        }
        MainFrame.main(args);
    }
    
}
