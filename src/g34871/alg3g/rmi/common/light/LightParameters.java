/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package g34871.alg3g.rmi.common.light;

import g34871.alg3g.rmi.common.exception.NotInIntervalException;
import g34871.alg3g.rmi.common.utils.Interval;

/**
 *
 * @author kevin
 */
public class LightParameters {

    private Interval redLightTime;
    private Interval orangeLightTime;
    private Interval greenLightTime;
    private Interval pauseTime;
    private Interval blinkingTime;
    private Interval greenBlinkingTime;

    public LightParameters(Interval redLightTime, Interval orangeLightTime, 
            Interval greenLightTime, Interval pauseTime, Interval blinkingTimer, 
            Interval greenBlinkingTimer) {
        this.redLightTime = redLightTime;
        this.orangeLightTime = orangeLightTime;
        this.greenLightTime = greenLightTime;
        this.pauseTime = pauseTime;
        this.blinkingTime = blinkingTimer;
        this.greenBlinkingTime = greenBlinkingTimer;
    }
    
    public LightParameters(int redLightTime, int orangeLightTime, 
            int greenLightTime, int pauseTime, int blinkingTimer, 
            int greenBlinkingTimer) throws NotInIntervalException {
        this.redLightTime = new Interval(1000, 100000);
        this.orangeLightTime = new Interval(1000, 100000);
        this.greenLightTime = new Interval(1000, 100000);
        this.pauseTime = new Interval(500, 100000);
        this.blinkingTime = new Interval(200, 100000);
        this.greenBlinkingTime = new Interval(200, 100000);
        
        this.redLightTime.setCurrentValue(redLightTime);
        this.orangeLightTime.setCurrentValue(orangeLightTime);
        this.greenLightTime.setCurrentValue(greenLightTime);
        this.pauseTime.setCurrentValue(pauseTime);
        this.blinkingTime.setCurrentValue(blinkingTimer);
        this.greenBlinkingTime.setCurrentValue(greenBlinkingTimer);
    }

    public final Interval getRedLightTime() {
        return redLightTime;
    }

    public void setRedLightTime(int redLightTime) throws NotInIntervalException {
        this.redLightTime.setCurrentValue(redLightTime);
    }

    public final Interval getOrangeLightTime() {
        return orangeLightTime;
    }

    public void setOrangeLightTime(int orangeLightTime) throws NotInIntervalException {
        this.orangeLightTime.setCurrentValue(orangeLightTime);
    }

    public final Interval getGreenLightTime() {
        return greenLightTime;
    }

    public void setGreenLightTime(int greenLightTime) throws NotInIntervalException {
        this.greenLightTime.setCurrentValue(greenLightTime);
    }

    public final Interval getPauseTime() {
        return pauseTime;
    }

    public void setPauseTime(int pauseTime) throws NotInIntervalException {
        this.pauseTime.setCurrentValue(pauseTime);
    }

    public final Interval getBlinkingTime() {
        return blinkingTime;
    }

    public void setBlinkingTime(int blinkingTimer) throws NotInIntervalException {
        this.blinkingTime.setCurrentValue(blinkingTimer);
    }

    public final Interval getGreenBlinkingTime() {
        return greenBlinkingTime;
    }

    public void setGreenBlinkingTime(int greenBlinkingTimer) throws NotInIntervalException {
        this.greenBlinkingTime.setCurrentValue(greenBlinkingTimer);
    }
    
}
