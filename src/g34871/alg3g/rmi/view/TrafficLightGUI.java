/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package g34871.alg3g.rmi.view;

import g34871.alg3g.rmi.common.crossroads.Axe;
import g34871.alg3g.rmi.common.light.LightState;
import g34871.alg3g.rmi.server.Crossroads;
import g34871.alg3g.rmi.view.image.LightImage;
import g34871.alg3g.rmi.view.image.TrafficLightImage;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import javax.swing.Timer;

/**
 *
 * @author kevin
 */
public class TrafficLightGUI extends LightGUI {

    private Timer blinkingTimer;
    private boolean isBlinkingAndOrange;

    public TrafficLightGUI(String string, Crossroads crossroads, Axe axe) throws HeadlessException, IOException {
        super(string, crossroads, axe);
        init(axe, crossroads);
    }

    private void init(Axe axe, Crossroads crossroads) {
        this.crossroads.addPropertyChangeListener(
                ((axe == Axe.NS_SN) ? Crossroads.TRAFFIC_LIGHTS_NS_CHANGED : Crossroads.TRAFFIC_LIGHTS_WE_CHANGED),
                new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent pce) {
                        refresh((LightState) pce.getNewValue());
                    }
                });

        isBlinkingAndOrange = false;
        blinkingTimer = new Timer(crossroads.getParameters().getLightParams().getBlinkingTime().getCurrentValue(),
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        blinkingOrange();
                    }
                });

        refresh(crossroads.getPedestrianLight(axe).getState());
    }

    @Override
    protected void refresh(LightState state) {
        if (state == LightState.BLINKING_ORANGE) {
            if (!isBlinkingAndOrange) {
                isBlinkingAndOrange = true;
                blinkingTimer.start();
            }
        } else {
            isBlinkingAndOrange = false;
            blinkingTimer.stop();

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
                case ORANGE:
                    image.toIntermediate();
                    break;
            }
        }
    }

    private void blinkingOrange() {
        if (isBlinkingAndOrange) {
            //go off
            image.toOff();
        } else {
            //go orange
            image.toIntermediate();
        }

        isBlinkingAndOrange = !isBlinkingAndOrange;
    }

    @Override
    protected void initImage() {
        this.image = new TrafficLightImage(LightImage.ScaleType.REAL_SIZE, LightImage.Rotate.NONE);
    }
}
