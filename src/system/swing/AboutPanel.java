/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system.swing;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Kamil
 */
public class AboutPanel extends JMainPanel {

    public AboutPanel() {
        setOpaque(true);
        setBorder(createTitledBorder("Informacje o grze", Color.orange));
        setLayout(new BorderLayout());
        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(getBackground());
        main.setBorder(createTitledBorder("Informacje o autorze", Color.yellow));
        JPanel empty = new JPanel();
        empty.setOpaque(false);
        JPanel authorInfo = new JPanel(true);
        authorInfo.setLayout(new BoxLayout(authorInfo, BoxLayout.PAGE_AXIS));
        authorInfo.add(Box.createVerticalGlue());
        Box b = Box.createVerticalBox();
        Box c = Box.createHorizontalBox();
        c.add(createJLabel("Autor:"));
        c.add(Box.createHorizontalGlue());
        c.add(createJLabel("Kamil Mularski", Color.white));
        b.add(c);
        c = Box.createHorizontalBox();
        c.add(createJLabel("Kontakt (e-mail):"));
        c.add(Box.createHorizontalGlue());
        c.add(createJLabel("kamil.mularski.polska@wp.pl", Color.white));
        b.add(c);
        authorInfo.add(b);
        authorInfo.add(Box.createHorizontalGlue());
        b = Box.createVerticalBox();
        c = Box.createHorizontalBox();
        c.add(createJLabel("Projekt graficzny:"));
        c.add(Box.createHorizontalGlue());
        c.add(createJLabel("Kamil Mularski", Color.white));
        b.add(c);
        c = Box.createHorizontalBox();
        c.add(createJLabel("Kontakt (nr GG):"));
        c.add(Box.createHorizontalGlue());
        c.add(createJLabel("10621578", Color.white));
        b.add(c);
        authorInfo.add(b);
        authorInfo.setOpaque(false);
        main.add(authorInfo, BorderLayout.CENTER);
        add(main, BorderLayout.NORTH);
        JPanel applicationInfo = new JPanel(true);
        applicationInfo.setLayout(new BoxLayout(applicationInfo, BoxLayout.PAGE_AXIS));
        applicationInfo.add(createJLabel("Nazwa projektu:"));
        applicationInfo.add(createJLabel("Warcaby"));
        applicationInfo.add(createJLabel("Nazwa programu:"));
        applicationInfo.add(createJLabel("Warcaby"));
        applicationInfo.add(createJLabel("Wersja projektu:"));
        applicationInfo.add(createJLabel("3.0"));
        applicationInfo.add(createJLabel("Wersja programu:"));
        applicationInfo.add(createJLabel("1.0"));
        applicationInfo.add(createJLabel("Wersja grafiki programu:"));
        applicationInfo.add(createJLabel("1.0"));
        applicationInfo.setBackground(getBackground());
        applicationInfo.setBorder(createTitledBorder("Informacje o programie", Color.yellow));
        add(applicationInfo, BorderLayout.CENTER);
        JLinkingButton back = JLinkingButton.createBackButton(new MainMenuPanel());
        back.setPreferredSize(new Dimension(500, 50));
        add(back, BorderLayout.SOUTH);
    }

    private static TitledBorder createTitledBorder(String title, Color titleColor) {
        TitledBorder tb = BorderFactory.createTitledBorder(title);
        tb.setTitleColor(titleColor);
        return tb;
    }

    private static JLabel createJLabel(String text, Color textColor) {
        JLabel b = new JLabel(text);
        b.setForeground(textColor);
        return b;
    }

    private static JLabel createJLabel(String text) {
        return createJLabel(text, Color.green);
    }
}
