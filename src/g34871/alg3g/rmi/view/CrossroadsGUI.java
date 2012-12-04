/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package g34871.alg3g.rmi.view;

import g34871.alg3g.rmi.common.crossroads.Axe;
import g34871.alg3g.rmi.common.light.LightState;
import g34871.alg3g.rmi.common.utils.gui.SimpleMoveFrame;
import g34871.alg3g.rmi.server.Crossroads;
import g34871.alg3g.rmi.view.image.LightImage;
import g34871.alg3g.rmi.view.image.PedestrianLightImage;
import g34871.alg3g.rmi.view.image.TrafficLightImage;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author kevin
 */
public class CrossroadsGUI extends SimpleMoveFrame {

    private JLabel backgroundImage;
    private JPanel containerPanel;
    private TrafficLightImage trafficLightNS;
    private TrafficLightImage trafficLightSN;
    private TrafficLightImage trafficLightWE;
    private TrafficLightImage trafficLightEW;
    private PedestrianLightImage pedestrianLightNS;
    private PedestrianLightImage pedestrianLightSN;
    private PedestrianLightImage pedestrianLightWE;
    private PedestrianLightImage pedestrianLightEW;
    private Crossroads crossroads;
    private Timer blinkingGreenTimer;
    private boolean isBlinkingAndGreenNS;
    private boolean isBlinkingAndGreenWE;

    public CrossroadsGUI(String string, Crossroads crossroads) throws HeadlessException {
        super(string);
        this.crossroads = crossroads;
        isBlinkingAndGreenNS = false;
        isBlinkingAndGreenWE = false;

        init();
    }

    private void init() {
        this.setResizable(false);
        this.setSize(new Dimension(500, 500));
        this.setPreferredSize(this.getSize());
        this.setUndecorated(true);
        containerPanel = (JPanel) this.getContentPane();

        blinkingGreenTimer = new Timer(crossroads.getParameters().getLightParams().getGreenBlinkingTime().getCurrentValue(),
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        blinkingGreen();
                    }
                });

        initGUI();
    }

    private void initGUI() {
        containerPanel.setLayout(null);

        initListener();
        initLights();

        backgroundImage = new JLabel(new ImageIcon("/Users/kevin/NetBeansProjects/Carrefour/images/crossroads.png"));
        containerPanel.add(backgroundImage);
        backgroundImage.setBounds(0, 0, 500, 500);
    }

    private void initListener() {
        this.crossroads.addPropertyChangeListener(
                Crossroads.TRAFFIC_LIGHTS_CHANGED,
                new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent pce) {
                        refresh();
                    }
                });
    }

    private void initLights() {
        trafficLightNS = new TrafficLightImage(LightImage.ScaleType.SMALL_SIZE, LightImage.Rotate.UPSIDE_DOWN);
        containerPanel.add(trafficLightNS);
        trafficLightNS.setBounds(161, 67, trafficLightNS.getWidth(), trafficLightNS.getHeight());

        trafficLightSN = new TrafficLightImage(LightImage.ScaleType.SMALL_SIZE, LightImage.Rotate.NONE);
        containerPanel.add(trafficLightSN);
        trafficLightSN.setBounds(327, 401, trafficLightSN.getWidth(), trafficLightSN.getHeight());

        trafficLightWE = new TrafficLightImage(LightImage.ScaleType.SMALL_SIZE, LightImage.Rotate.A_QUARTER);
        containerPanel.add(trafficLightWE);
        trafficLightWE.setBounds(65, 324, trafficLightWE.getWidth(), trafficLightWE.getHeight());

        trafficLightEW = new TrafficLightImage(LightImage.ScaleType.SMALL_SIZE, LightImage.Rotate.THREE_QUARTER);
        containerPanel.add(trafficLightEW);
        trafficLightEW.setBounds(397, 158, trafficLightEW.getWidth(), trafficLightEW.getHeight());

        pedestrianLightNS = new PedestrianLightImage(LightImage.ScaleType.SMALL_SIZE, LightImage.Rotate.THREE_QUARTER);
        containerPanel.add(pedestrianLightNS);
        pedestrianLightNS.setBounds(145, 120, pedestrianLightNS.getWidth(), pedestrianLightNS.getHeight());

        pedestrianLightSN = new PedestrianLightImage(LightImage.ScaleType.SMALL_SIZE, LightImage.Rotate.A_QUARTER);
        containerPanel.add(pedestrianLightSN);
        pedestrianLightSN.setBounds(333, 366, pedestrianLightSN.getWidth(), pedestrianLightSN.getHeight());

        pedestrianLightWE = new PedestrianLightImage(LightImage.ScaleType.SMALL_SIZE, LightImage.Rotate.UPSIDE_DOWN);
        containerPanel.add(pedestrianLightWE);
        pedestrianLightWE.setBounds(117, 326, pedestrianLightWE.getWidth(), pedestrianLightWE.getHeight());

        pedestrianLightEW = new PedestrianLightImage(LightImage.ScaleType.SMALL_SIZE, LightImage.Rotate.NONE);
        containerPanel.add(pedestrianLightEW);
        pedestrianLightEW.setBounds(366, 144, pedestrianLightEW.getWidth(), pedestrianLightEW.getHeight());
    }

    private void refresh() {
        updateTrafficLights(Axe.NS_SN);
        updateTrafficLights(Axe.EW_WE);
        updatePedestrianLights(Axe.NS_SN);
        updatePedestrianLights(Axe.EW_WE);
    }

    private void updateTrafficLights(Axe axe) {
        TrafficLightImage light1 = null;
        TrafficLightImage light2 = null;

        switch (axe) {
            case NS_SN:
                light1 = trafficLightNS;
                light2 = trafficLightSN;
                break;
            case EW_WE:
                light1 = trafficLightWE;
                light2 = trafficLightEW;
                break;
        }

        switch (crossroads.getTrafficLight(axe).getState()) {
            case OFF:
                light1.toOff();
                light2.toOff();
                break;
            case GREEN:
                light1.toGreen();
                light2.toGreen();
                break;
            case PAUSE:
            case RED:
                light1.toRed();
                light2.toRed();
                break;
            case ORANGE:
                light1.toIntermediate();
                light2.toIntermediate();
                break;
        }
    }

    private void updatePedestrianLights(Axe axe) {
        PedestrianLightImage light1 = null;
        PedestrianLightImage light2 = null;
        LightState state = crossroads.getPedestrianLight(axe).getState();

        switch (axe) {
            case NS_SN:
                light1 = pedestrianLightNS;
                light2 = pedestrianLightSN;
                break;
            case EW_WE:
                light1 = pedestrianLightWE;
                light2 = pedestrianLightEW;
                break;
        }

        if (axe == Axe.NS_SN && state == LightState.BLINKING_GREEN) {
            isBlinkingAndGreenNS = true;
            blinkingGreenTimer.start();
        } else if (axe == Axe.EW_WE && state == LightState.BLINKING_GREEN) {
            isBlinkingAndGreenWE = true;
            blinkingGreenTimer.start();
        } else {
            if (!isBlinkingAndGreenNS && axe == Axe.NS_SN 
                    || !isBlinkingAndGreenWE && axe == Axe.EW_WE) {
                blinkingGreenTimer.stop();
            }

            switch (state) {
                case OFF:
                    light1.toOff();
                    light2.toOff();
                    break;
                case GREEN:
                    light1.toGreen();
                    light2.toGreen();
                    break;
                case PAUSE:
                case RED:
                    light1.toRed();
                    light2.toRed();
                    break;
            }
        }
    }

    private void blinkingGreen() {
        PedestrianLightImage light1 = null;
        PedestrianLightImage light2 = null;
        boolean isBlinkingAndGreen = false;

        switch (crossroads.getPedestrianLight(Axe.NS_SN).getState()) {
            case BLINKING_GREEN: //the NS_SN lights are in this state
                light1 = pedestrianLightNS;
                light2 = pedestrianLightSN;
                isBlinkingAndGreen = !isBlinkingAndGreen;
                break;
            default: //the WE_EW lights are in this state
                light1 = pedestrianLightWE;
                light2 = pedestrianLightEW;
        }

        if (isBlinkingAndGreen) {
            //go off
            light1.toOff();
            light2.toOff();
        } else {
            //go green
            light1.toGreen();
            light2.toGreen();
        }
    }
}
