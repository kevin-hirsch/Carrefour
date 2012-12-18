/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package g34871.alg3g.rmi.project.view;

import g34871.alg3g.rmi.project.common.crossroads.Axe;
import g34871.alg3g.rmi.project.common.light.LightState;
import g34871.alg3g.rmi.project.common.utils.gui.SimpleMoveFrame;
import g34871.alg3g.rmi.project.server.Crossroads;
import g34871.alg3g.rmi.project.view.image.LightImage;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author kevin
 */
public abstract class LightGUI extends SimpleMoveFrame {

    private JPanel containerPanel;
    protected LightImage image;
    private JLabel lbAxe;
    protected Crossroads crossroads;

    public LightGUI(String string, Crossroads crossroads, Axe axe) throws HeadlessException, IOException {
        super(string);
        this.crossroads = crossroads;

        initGUI(axe);
    }

    private void initGUI(Axe axe) throws IOException {
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        containerPanel = new JPanel(new BorderLayout());

        if (axe == Axe.NS_SN) {
            lbAxe = new JLabel(new ImageIcon(ImageIO.read(new File("images/ns_axe.png"))));
        } else {
            lbAxe = new JLabel(new ImageIcon(ImageIO.read(new File("images/we_axe.png"))));
        }
        containerPanel.add(lbAxe, BorderLayout.NORTH);

        initImage();
        containerPanel.add(image, BorderLayout.CENTER);

        this.setContentPane(containerPanel);
        this.pack();
    }

    protected abstract void refresh(LightState state);

    protected abstract void initImage();
    
}
