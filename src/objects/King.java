/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;

/**
 *
 * @author Kamil
 */
public class King extends Pawn {

    public King(Pawn p, String path) {
        super(p.getIndex(), new ImageIcon(ClassLoader.getSystemResource(path)).getImage(), p.getLocation());
        this.setPlayer(p.getPlayer());
    }

    @Override
    public King toKing() { return this; }

    @Override
    public void transformToKing() {}
}
