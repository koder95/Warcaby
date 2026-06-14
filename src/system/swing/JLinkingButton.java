/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Kamil
 */
public class JLinkingButton extends JButton {

    public JLinkingButton(String text, JMainPanel main) {
        super(text);
        final JMainPanel mp = main;
        super.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MainGameFrame.setMainPanel(mp);
            }
        });
    }

    @Override
    public void addActionListener(ActionListener l) {
        System.err.println("Ten komponent nie może przyjąć innej akcji niż jest to ustalone.");
    }
    
    public static final JLinkingButton createBackButton(JMainPanel lastMainPanel){
        return new JLinkingButton("Wstecz", lastMainPanel);
    }
}
