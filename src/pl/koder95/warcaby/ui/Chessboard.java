/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.koder95.warcaby.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import pl.koder95.warcaby.system.ChessboardPoint;

/**
 *
 * @author Kamil Mularski
 */
public class Chessboard extends JLayeredPane {
    
    public static final class Fields extends JPanel {
        
        private final Color color1, color2;
        private final int fieldSize;
        private final ChessField[][] fields;

        public Fields(Color color1, Color color2, int fieldSize) {
            this.color1 = color1;
            this.color2 = color2;
            this.fieldSize = fieldSize;
            this.fields = new ChessField[8][8];

            for (char column = 'A'; column <= 'H'; column++) {
                for (int row = 8; row >= 1; row--) {
                    ChessboardPoint point = ChessboardPoint.get(column, row);
                    this.fields[column - 'A'][8-row] = new ChessField(this, point);
                    this.fields[column - 'A'][8-row].setText(column + ":" + row);
                }
            }
            this.addComponentListener(new ComponentAdapter() {

                @Override
                public void componentResized(ComponentEvent ce) {
                    super.componentResized(ce);
                    Component comp = ce.getComponent();
                    comp.setSize(comp.getHeight(), comp.getHeight());
                    comp.validate();
                    comp.setLocation((comp.getParent().getWidth() - comp.getWidth())/2, (comp.getParent().getHeight() - comp.getHeight())/2);
                }

            });
            this.setBackground(Color.LIGHT_GRAY);

            GroupLayout l = new GroupLayout(this);
            l.setHorizontalGroup(createHorizontalLayout(l));
            l.setVerticalGroup(createVerticalLayout(l));
            this.setLayout(l);
        }

        public Fields() {
            this(Color.BLACK, Color.WHITE, 100);
        }

        private GroupLayout.SequentialGroup createHorizontalLayout(GroupLayout l) {
            GroupLayout.SequentialGroup s = l.createSequentialGroup().addGap(10);
            for (ChessField[] field : fields) {
                GroupLayout.ParallelGroup p = l.createParallelGroup(GroupLayout.Alignment.CENTER);
                for (ChessField field1 : field) {
                    p = p.addComponent(field1, GroupLayout.Alignment.CENTER, fieldSize, fieldSize, Short.MAX_VALUE);
                }
                s = s.addGroup(p);
            }
            s = s.addGap(10);
            return s;
        }

        private GroupLayout.SequentialGroup createVerticalLayout(GroupLayout l) {
            GroupLayout.SequentialGroup s = l.createSequentialGroup().addGap(10);
            for (int x = 0; x < fields.length; x++) {
                GroupLayout.ParallelGroup p = l.createParallelGroup();
                for (int y = 0; y < fields[x].length; y++) {
                    p = p.addComponent(fields[y][x], GroupLayout.Alignment.CENTER, fieldSize, fieldSize, Short.MAX_VALUE);
                }
                s = s.addGroup(p);
            }
            s = s.addGap(10);
            return s;
        }

        public Color getFirstFieldColor() {
            return color1;
        }

        public Color getSecondFieldColor() {
            return color2;
        }

        public int getFieldSize() {
            return fieldSize;
        }

        public ChessField getChessField(ChessboardPoint location) {
            return fields[location.getColumn() - 'A'][8 - location.getRow()];
        }
    }
    
    public static final class Pawns extends JPanel {

        private final int fieldSize;

        public Pawns(int fieldSize) {
            this.setBorder(null);
            this.fieldSize = fieldSize;
            this.setLayout(new FlowLayout());
            ImageComponent com = new ImageComponent("pl/koder95/warcaby/assets/pawnWhite.png");
            com.setSize(this.fieldSize, this.fieldSize);
            com.setLocation(10, 10);
            MouseAdapter mouse = new MouseAdapter() {
                
                private Point begin;

                @Override
                public void mouseDragged(MouseEvent me) {
                    super.mouseDragged(me);
                    Component comp = me.getComponent();
                    Point loc = comp.getLocation();
                    Point p = me.getPoint();
                    if (begin == null) begin = p;
                    me.getComponent().setLocation(loc.x + p.x - comp.getWidth()/2, loc.y + p.y - comp.getHeight()/2);
                }
                
            };
            com.addMouseMotionListener(mouse);
            this.add(com);
        }
        
        public Pawns() {
            this(100);
        }
        
    }

    public Chessboard() {
        Fields f = new Fields();
        f.setBounds(0, 0, 600, 500);
        Pawns p = new Pawns();
        p.setOpaque(false);
        this.add(f, 0, 0);
        this.add(p, 1, 0);
    }
}
