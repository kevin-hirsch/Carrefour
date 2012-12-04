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
    private PedestrianLightImage pedestrianLightNS_1;
    private PedestrianLightImage pedestrianLightSN_1;
    private PedestrianLightImage pedestrianLightWE_1;
    private PedestrianLightImage pedestrianLightEW_1;
    private PedestrianLightImage pedestrianLightNS_2;
    private PedestrianLightImage pedestrianLightSN_2;
    private PedestrianLightImage pedestrianLightWE_2;
    private PedestrianLightImage pedestrianLightEW_2;
    private Crossroads crossroads;
    private Timer blinkingGreenTimer;
    private Timer blinkingTimer;
    private boolean isBlinkingAndOrange;
    private boolean isBlinkingAndGreen;
    private boolean isNSAxeInIntermediateState;

    public CrossroadsGUI(String string, Crossroads crossroads) throws HeadlessException {
        super(string);
        this.crossroads = crossroads;
        this.isBlinkingAndOrange = false;
        this.isBlinkingAndGreen = false;
        this.isNSAxeInIntermediateState = false;

        init();
    }

    private void init() {
        this.setResizable(false);
        this.setSize(new Dimension(500, 500));
        this.setPreferredSize(this.getSize());
        this.setUndecorated(true);
        containerPanel = (JPanel) this.getContentPane();

        blinkingTimer = new Timer(crossroads.getParameters().getLightParams().getBlinkingTime().getCurrentValue(),
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        blinkingOrange();
                    }
                });

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

        pedestrianLightNS_1 = new PedestrianLightImage(LightImage.ScaleType.SMALL_SIZE, LightImage.Rotate.THREE_QUARTER);
        containerPanel.add(pedestrianLightNS_1);
        pedestrianLightNS_1.setBounds(145, 120, pedestrianLightNS_1.getWidth(), pedestrianLightNS_1.getHeight());

        pedestrianLightSN_1 = new PedestrianLightImage(LightImage.ScaleType.SMALL_SIZE, LightImage.Rotate.A_QUARTER);
        containerPanel.add(pedestrianLightSN_1);
        pedestrianLightSN_1.setBounds(333, 366, pedestrianLightSN_1.getWidth(), pedestrianLightSN_1.getHeight());

        pedestrianLightWE_1 = new PedestrianLightImage(LightImage.ScaleType.SMALL_SIZE, LightImage.Rotate.UPSIDE_DOWN);
        containerPanel.add(pedestrianLightWE_1);
        pedestrianLightWE_1.setBounds(117, 326, pedestrianLightWE_1.getWidth(), pedestrianLightWE_1.getHeight());

        pedestrianLightEW_1 = new PedestrianLightImage(LightImage.ScaleType.SMALL_SIZE, LightImage.Rotate.NONE);
        containerPanel.add(pedestrianLightEW_1);
        pedestrianLightEW_1.setBounds(366, 144, pedestrianLightEW_1.getWidth(), pedestrianLightEW_1.getHeight());
        
        pedestrianLightNS_2 = new PedestrianLightImage(LightImage.ScaleType.SMALL_SIZE, LightImage.Rotate.A_QUARTER);
        containerPanel.add(pedestrianLightNS_2);
        pedestrianLightNS_2.setBounds(333, 120, pedestrianLightNS_2.getWidth(), pedestrianLightNS_2.getHeight());

        pedestrianLightSN_2 = new PedestrianLightImage(LightImage.ScaleType.SMALL_SIZE, LightImage.Rotate.THREE_QUARTER);
        containerPanel.add(pedestrianLightSN_2);
        pedestrianLightSN_2.setBounds(145, 366, pedestrianLightSN_2.getWidth(), pedestrianLightSN_2.getHeight());

        pedestrianLightWE_2 = new PedestrianLightImage(LightImage.ScaleType.SMALL_SIZE, LightImage.Rotate.NONE);
        containerPanel.add(pedestrianLightWE_2);
        pedestrianLightWE_2.setBounds(117, 144, pedestrianLightWE_2.getWidth(), pedestrianLightWE_2.getHeight());

        pedestrianLightEW_2 = new PedestrianLightImage(LightImage.ScaleType.SMALL_SIZE, LightImage.Rotate.UPSIDE_DOWN);
        containerPanel.add(pedestrianLightEW_2);
        pedestrianLightEW_2.setBounds(366, 326, pedestrianLightEW_2.getWidth(), pedestrianLightEW_2.getHeight());
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
        LightState state = crossroads.getTrafficLight(axe).getState();

        if (state == LightState.BLINKING_ORANGE) {
            if (!isBlinkingAndOrange) {
                isBlinkingAndOrange = true;
                blinkingTimer.start();
            }
            return;
        } else {
            isBlinkingAndOrange = false;
            blinkingTimer.stop();
        }

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
            case ORANGE:
                light1.toIntermediate();
                light2.toIntermediate();
                break;
        }
    }

    private void updatePedestrianLights(Axe axe) {
        PedestrianLightImage light1 = null;
        PedestrianLightImage light2 = null;
        PedestrianLightImage light3 = null;
        PedestrianLightImage light4 = null;
        LightState state = crossroads.getPedestrianLight(axe).getState();

        if (state == LightState.BLINKING_GREEN) {
            isBlinkingAndGreen = true;
            isNSAxeInIntermediateState = ((axe == Axe.NS_SN) ? true : false);
            blinkingGreenTimer.start();
            return;
        }
        
        
        if ((isNSAxeInIntermediateState && axe == Axe.NS_SN) || (!isNSAxeInIntermediateState && axe == Axe.EW_WE)
                && blinkingGreenTimer.isRunning()) {
            isNSAxeInIntermediateState = !isNSAxeInIntermediateState;
            blinkingGreenTimer.stop();
        }
        
        
        switch (axe) {
            case NS_SN:
                light1 = pedestrianLightNS_1;
                light2 = pedestrianLightSN_1;
                light3 = pedestrianLightNS_2;
                light4 = pedestrianLightSN_2;
                break;
            case EW_WE:
                light1 = pedestrianLightWE_1;
                light2 = pedestrianLightEW_1;
                light3 = pedestrianLightWE_2;
                light4 = pedestrianLightEW_2;
                break;
        }

        switch (state) {
            case OFF:
                light1.toOff();
                light2.toOff();
                light3.toOff();
                light4.toOff();
                break;
            case GREEN:
                light1.toGreen();
                light2.toGreen();
                light3.toGreen();
                light4.toGreen();
                break;
            case PAUSE:
            case RED:
                light1.toRed();
                light2.toRed();
                light3.toRed();
                light4.toRed();
                break;
        }
    }

    private void blinkingOrange() {
        if (isBlinkingAndOrange) {
            //go off
            trafficLightNS.toOff();
            trafficLightSN.toOff();
            trafficLightWE.toOff();
            trafficLightEW.toOff();
        } else {
            //go orange
            trafficLightNS.toIntermediate();
            trafficLightSN.toIntermediate();
            trafficLightWE.toIntermediate();
            trafficLightEW.toIntermediate();
        }

        isBlinkingAndOrange = !isBlinkingAndOrange;
    }

    private void blinkingGreen() {
        PedestrianLightImage light1;
        PedestrianLightImage light2;
        PedestrianLightImage light3;
        PedestrianLightImage light4;

        if (isNSAxeInIntermediateState) {
            //the NS_SN lights are in this state
            light1 = pedestrianLightNS_1;
            light2 = pedestrianLightSN_1;
            light3 = pedestrianLightNS_2;
            light4 = pedestrianLightSN_2;
        } else {
            //the WE_EW lights are in this state
            light1 = pedestrianLightWE_1;
            light2 = pedestrianLightEW_1;
            light3 = pedestrianLightWE_2;
            light4 = pedestrianLightEW_2;
        }

        if (isBlinkingAndGreen) {
            //go off
            light1.toOff();
            light2.toOff();
            light3.toOff();
            light4.toOff();
        } else {
            //go green
            light1.toGreen();
            light2.toGreen();
            light3.toGreen();
            light4.toGreen();
        }

        isBlinkingAndGreen = !isBlinkingAndGreen;
    }
}
