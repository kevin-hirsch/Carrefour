/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package g34871.alg3g.rmi.server;

import g34871.alg3g.rmi.common.crossroads.Axe;
import g34871.alg3g.rmi.common.crossroads.CrossroadsInterface;
import g34871.alg3g.rmi.common.crossroads.CrossroadsParameters;
import g34871.alg3g.rmi.common.light.LightInterface;
import g34871.alg3g.rmi.common.light.LightState;
import g34871.alg3g.rmi.common.utils.Interval;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.Timer;

/**
 *
 * @author kevin
 */
public class Crossroads implements CrossroadsInterface {

    private CrossroadsParameters params;
    private double rate;
    private TrafficLightModel trafficLightNSAxe;
    private TrafficLightModel trafficLightWEAxe;
    private PedestrianLightModel pedestrianLightNSAxe;
    private PedestrianLightModel pedestrianLightWEAxe;
    private boolean hasHandNSAxe;
    private Timer trafficTimer;
    private PropertyChangeSupport propertyChange;
    public static final String TRAFFIC_LIGHTS_CHANGED = "traffic lights changed";
    public static final String TRAFFIC_LIGHTS_NS_CHANGED = "traffic lights north-south changed";
    public static final String TRAFFIC_LIGHTS_WE_CHANGED = "traffic lights west-est changed";
    public static final String PEDESTRIAN_LIGHTS_NS_CHANGED = "pedestrian lights north-south changed";
    public static final String PEDESTRIAN_LIGHTS_WE_CHANGED = "pedestrian lights west-est changed";
    private boolean hasNSChanged;
    private boolean hasWEChanged;

    public Crossroads(CrossroadsParameters params) {
        this.params = params;
        this.rate = 1;
        this.propertyChange = new PropertyChangeSupport(this);
        this.hasHandNSAxe = true;
        this.hasNSChanged = false;
        this.hasWEChanged = false;

        initLights();
        initTimers();
    }

    private void initLights() {
        //North-South Axe
        this.trafficLightNSAxe = new TrafficLightModel(Axe.NS_SN);
        this.pedestrianLightNSAxe = new PedestrianLightModel(Axe.NS_SN);

        //Est-West Axe
        this.trafficLightWEAxe = new TrafficLightModel(Axe.EW_WE);
        this.pedestrianLightWEAxe = new PedestrianLightModel(Axe.EW_WE);
    }

    private void initTimers() {
        this.trafficTimer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                updateTrafficLights();
            }
        });

        this.trafficTimer.start();
    }

    private void updateTrafficLights() {
        Interval timeInterval = null;
        LightState previousState = ((hasHandNSAxe) ? trafficLightNSAxe.getState() : trafficLightWEAxe.getState());

        if (trafficTimer.getInitialDelay() != 0) {
            //on a changé le délai, on le reset
            trafficTimer.setInitialDelay(0);
        }

        switch (previousState) {
            case GREEN: //end of green --> to orange
                if (hasHandNSAxe) {
                    trafficLightNSAxe.goIntermediate();
                    pedestrianLightWEAxe.goIntermediate();
                    hasNSChanged = true;
                    hasWEChanged = false;
                } else {
                    trafficLightWEAxe.goIntermediate();
                    pedestrianLightNSAxe.goIntermediate();
                    hasNSChanged = false;
                    hasWEChanged = true;
                }
                timeInterval = params.getLightParams().getOrangeLightTime();
                break;
            case OFF:
            case ORANGE: //end of orange --> to pause
                trafficLightNSAxe.goPause();
                trafficLightWEAxe.goPause();
                pedestrianLightNSAxe.goPause();
                pedestrianLightWEAxe.goPause();
                hasNSChanged = true;
                hasWEChanged = true;

                hasHandNSAxe = !hasHandNSAxe;
                timeInterval = params.getLightParams().getPauseTime();
                break;
            case PAUSE: //end of pause --> to green/red
                if (hasHandNSAxe) {
                    trafficLightNSAxe.goGreen();
                    trafficLightWEAxe.goRed();
                    pedestrianLightWEAxe.goGreen();
                    pedestrianLightNSAxe.goRed();
                } else {
                    trafficLightWEAxe.goGreen();
                    trafficLightNSAxe.goRed();
                    pedestrianLightWEAxe.goRed();
                    pedestrianLightNSAxe.goGreen();
                }
                hasNSChanged = true;
                hasWEChanged = true;
                timeInterval = params.getLightParams().getGreenLightTime();
                break;
            case BLINKING_ORANGE: //blinking orange --> continue to blink
                if (!hasNSChanged) {
                    hasNSChanged = true;
                    hasWEChanged = true;
                }
                timeInterval = params.getLightParams().getBlinkingTime();
                break;
        }

        restartWithInterval(trafficTimer, timeInterval);
        propertyChange.firePropertyChange(TRAFFIC_LIGHTS_CHANGED, previousState,
                ((hasHandNSAxe) ? trafficLightNSAxe.getState() : trafficLightWEAxe.getState()));
        if (hasNSChanged) {
            propertyChange.firePropertyChange(TRAFFIC_LIGHTS_NS_CHANGED, previousState,
                    trafficLightNSAxe.getState());
            propertyChange.firePropertyChange(PEDESTRIAN_LIGHTS_WE_CHANGED, null,
                    pedestrianLightWEAxe.getState());
        }
        if (hasWEChanged) {
            propertyChange.firePropertyChange(TRAFFIC_LIGHTS_WE_CHANGED, previousState,
                    trafficLightWEAxe.getState());
            propertyChange.firePropertyChange(PEDESTRIAN_LIGHTS_NS_CHANGED, null,
                    pedestrianLightNSAxe.getState());
        }
    }

    private void restartWithInterval(Timer lightTimer, Interval currentValue) {
        restartWithDelay(lightTimer, currentValue.getCurrentValue());
    }

    private void restartWithDelay(Timer lightTimer, int currentValue) {
        lightTimer.setDelay(currentValue);
        lightTimer.restart();
    }

    @Override
    public void putOffService() {
        trafficLightNSAxe.goOffService();
        trafficLightWEAxe.goOffService();
        pedestrianLightNSAxe.goOffService();
        pedestrianLightWEAxe.goOffService();
        updateTrafficLights();
    }

    @Override
    public void rebootService() {
        trafficLightNSAxe.goIntermediate();
        trafficLightWEAxe.goIntermediate();
        updateTrafficLights();
    }

    @Override
    public void setSpeedRate(double rate) {
        this.rate = rate;
    }

    @Override
    public double getSpeedRate() {
        return rate;
    }

    @Override
    public boolean pedestrianButtonPushed(Axe axe) {
        if (getTrafficLight(axe).getState() == LightState.GREEN) {
            trafficTimer.setInitialDelay(params.getLightParams().getGreenLightTime().getMin());
            trafficTimer.restart();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public LightInterface getTrafficLight(Axe axe) {
        if (axe == Axe.EW_WE) {
            return trafficLightWEAxe;
        } else {
            return trafficLightNSAxe;
        }
    }

    @Override
    public LightInterface getPedestrianLight(Axe axe) {
        if (axe == Axe.EW_WE) {
            return pedestrianLightWEAxe;
        } else {
            return pedestrianLightNSAxe;
        }
    }

    @Override
    public CrossroadsParameters getParameters() {
        return params;
    }

    @Override
    public void setParameters(CrossroadsParameters params) {
        this.params = params;
    }

    public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
        propertyChange.addPropertyChangeListener(property, listener);
    }

    public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
        propertyChange.removePropertyChangeListener(property, listener);
    }
}
