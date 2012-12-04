/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package g34871.alg3g.rmi.view;

import g34871.alg3g.rmi.common.crossroads.Axe;
import g34871.alg3g.rmi.common.light.LightState;
import g34871.alg3g.rmi.server.Crossroads;
import g34871.alg3g.rmi.view.image.LightImage;
import g34871.alg3g.rmi.view.image.PedestrianLightImage;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author kevin
 */
public class PedestrianLightGUI extends LightGUI {

    private Timer blinkingGreenTimer;
    private boolean isBlinkingAndGreen;
    private BufferedImage noMessageOnButton;
    private BufferedImage waitMessageOnButton;
    private BufferedImage goMessageOnButton;
    private JLabel bottomBt;
    private Axe axe;
    private boolean isButtonPushed;
    private boolean buttonHasBeenPushed;

    public PedestrianLightGUI(String string, Crossroads crossroads, Axe axe) throws HeadlessException, IOException {
        super(string, crossroads, axe);
        this.axe = axe;
        this.isButtonPushed = false;
        this.buttonHasBeenPushed = false;
        initImages();
        init(axe, crossroads);
        initGUI();
    }

    private void init(Axe axe, Crossroads crossroads) {
        this.crossroads.addPropertyChangeListener(
                ((axe == Axe.NS_SN) ? Crossroads.PEDESTRIAN_LIGHTS_NS_CHANGED : Crossroads.PEDESTRIAN_LIGHTS_WE_CHANGED),
                new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent pce) {
                        refresh((LightState) pce.getNewValue());
                    }
                });

        isBlinkingAndGreen = false;
        blinkingGreenTimer = new Timer(crossroads.getParameters().getLightParams().getGreenBlinkingTime().getCurrentValue(),
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        blinkingGreen();
                    }
                });
        
        refresh(crossroads.getPedestrianLight(axe).getState());
    }

    private void initGUI() throws IOException {
        JLabel topBt = new JLabel(new ImageIcon(ImageIO.read(new File("images/button_top.png"))));
        JLabel leftBt = new JLabel(new ImageIcon(ImageIO.read(new File("images/button_left.png"))));
        JLabel rightBt = new JLabel(new ImageIcon(ImageIO.read(new File("images/button_right.png"))));
        bottomBt = new JLabel(new ImageIcon(noMessageOnButton));

        JButton pedestrianBt = new JButton(new ImageIcon(ImageIO.read(new File("images/button_center.png"))));
        pedestrianBt.setPressedIcon(new ImageIcon(ImageIO.read(new File("images/button_center_active.png"))));
        pedestrianBt.setBorder(null);
        pedestrianBt.setBorderPainted(false);
        pedestrianBt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pedestrianBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                buttonPushed();
            }
        });

        JPanel panelButton = new JPanel(new BorderLayout());
        panelButton.add(topBt, BorderLayout.NORTH);
        panelButton.add(leftBt, BorderLayout.WEST);
        panelButton.add(rightBt, BorderLayout.EAST);
        panelButton.add(bottomBt, BorderLayout.SOUTH);
        panelButton.add(pedestrianBt, BorderLayout.CENTER);

        this.getContentPane().add(panelButton, BorderLayout.SOUTH);
        this.pack();
    }

    private void buttonPushed() {
        if (!isButtonPushed) {
            isButtonPushed = this.crossroads.pedestrianButtonPushed(axe);
            if (isButtonPushed) {
                bottomBt.setIcon(new ImageIcon(waitMessageOnButton));
            }
        }
    }

    protected void initImages() throws IOException {
        noMessageOnButton = ImageIO.read(new File("images/button_bottom.png"));
        waitMessageOnButton = ImageIO.read(new File("images/button_bottom_wait.png"));
        goMessageOnButton = ImageIO.read(new File("images/button_bottom_go.png"));
    }

    @Override
    protected void refresh(LightState state) {
        if (state == LightState.BLINKING_GREEN) {
            isBlinkingAndGreen = true;
            blinkingGreenTimer.start();
        } else {
            blinkingGreenTimer.stop();

            if (state != LightState.GREEN && buttonHasBeenPushed) {
                isButtonPushed = false;
                buttonHasBeenPushed = false;
                bottomBt.setIcon(new ImageIcon(noMessageOnButton));
            } else if (state == LightState.GREEN && isButtonPushed) {
                buttonHasBeenPushed = true;
                bottomBt.setIcon(new ImageIcon(goMessageOnButton));
            }

            switch (state) {
                case OFF:
                    image.toOff();
                    break;
                case GREEN:
                    image.toGreen();
                    break;
                case PAUSE:
                case RED:
                    image.toRed();
                    break;
            }
        }
    }

    private void blinkingGreen() {
        if (isBlinkingAndGreen) {
            //go off
            image.toOff();
        } else {
            //go green
            image.toGreen();
        }
        
        isBlinkingAndGreen = !isBlinkingAndGreen;
    }

    @Override
    protected void initImage() {
        this.image = new PedestrianLightImage(LightImage.ScaleType.REAL_SIZE, LightImage.Rotate.NONE);
    }
}
