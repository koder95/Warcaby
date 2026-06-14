/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.koder95.warcaby.ui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Kamil Mularski
 */
public class ImageComponent extends JLabel {

    public ImageComponent(String res) {
        super(new ImageIcon(ClassLoader.getSystemResource(res)));
    }
    
}
