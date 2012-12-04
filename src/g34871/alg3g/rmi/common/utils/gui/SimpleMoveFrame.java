/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package g34871.alg3g.rmi.common.utils.gui;

import java.awt.Cursor;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;

/**
 *
 * @author kevin
 */
public class SimpleMoveFrame extends JFrame {

    private Point cursorLocation;

    public SimpleMoveFrame(String string) throws HeadlessException {
        super(string);

        init();
    }

    private void init() {
        this.setUndecorated(true);
        this.setResizable(false);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        
        this.cursorLocation = new Point();

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                cursorLocation.x = e.getX();
                cursorLocation.y = e.getY();
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point p = getLocation();
                setLocation(p.x + e.getX() - cursorLocation.x, p.y + e.getY() - cursorLocation.y);
            }
        });
    }
}
