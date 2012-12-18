/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package g34871.alg3g.rmi.project.common.light;

import g34871.alg3g.rmi.project.common.exception.NotInIntervalException;
import g34871.alg3g.rmi.project.common.utils.Interval;

/**
 *
 * @author kevin
 */
public class LightParameters {

    //Traffic Light Timers
    private Interval trRedTime;
    private Interval trOrangeTime;
    private Interval trGreenTime;
    private Interval trBlinkingTime;
    //Pedestrian Light Timers
    private Interval pdRedTime;
    private Interval pdOrangeTime;
    private Interval pdGreenTime;
    private Interval pdBlinkingTime;
    //General Timers
    private Interval pauseTime;

    public LightParameters(Interval trRedTime, Interval trOrangeTime, 
            Interval trGreenTime, Interval trBlinkingTime, Interval pauseTime, 
            Interval pdRedTime, Interval pdOrangeTime, 
            Interval pdGreenTime, Interval pdBlinkingTime) {
        //Traffic timers
        this.trRedTime = trRedTime;
        this.trOrangeTime = trOrangeTime;
        this.trGreenTime = trGreenTime;
        this.trBlinkingTime = trBlinkingTime;
        //Pedestrian timers
        this.pdRedTime = pdRedTime;
        this.pdOrangeTime = pdOrangeTime;
        this.pdGreenTime = pdGreenTime;
        this.pdBlinkingTime = pdBlinkingTime;
        //General timers
        this.pauseTime = pauseTime;
    }

    //Setters
    
    public void setTrRedTime(int redLightTime) throws NotInIntervalException {
        this.trRedTime.setCurrentValue(redLightTime);
    }

    public void setTrOrangeTime(int orangeLightTime) throws NotInIntervalException {
        this.trOrangeTime.setCurrentValue(orangeLightTime);
    }

    public void setTrGreenTime(int greenLightTime) throws NotInIntervalException {
        this.trGreenTime.setCurrentValue(greenLightTime);
    }

    public void setPauseTime(int pauseTime) throws NotInIntervalException {
        this.pauseTime.setCurrentValue(pauseTime);
    }

    public void setTrBlinkingTime(int blinkingTimer) throws NotInIntervalException {
        this.trBlinkingTime.setCurrentValue(blinkingTimer);
    }

    public void setPdBlinkingTime(int greenBlinkingTimer) throws NotInIntervalException {
        this.pdBlinkingTime.setCurrentValue(greenBlinkingTimer);
    }

    public void setPdRedTime(int pdRedTime) throws NotInIntervalException {
        this.pdRedTime.setCurrentValue(pdRedTime);
    }

    public void setPdOrangeTime(int pdOrangeTime) throws NotInIntervalException {
        this.pdOrangeTime.setCurrentValue(pdOrangeTime);
    }

    public void setPdGreenTime(int pdGreenTime) throws NotInIntervalException {
        this.pdGreenTime.setCurrentValue(pdGreenTime);
    }
    
    //Getters

    public Interval getTrRedTime() {
        return trRedTime;
    }

    public Interval getTrOrangeTime() {
        return trOrangeTime;
    }

    public Interval getTrGreenTime() {
        return trGreenTime;
    }

    public Interval getTrBlinkingTime() {
        return trBlinkingTime;
    }

    public Interval getPdRedTime() {
        return pdRedTime;
    }

    public Interval getPdOrangeTime() {
        return pdOrangeTime;
    }

    public Interval getPdGreenTime() {
        return pdGreenTime;
    }

    public Interval getPdBlinkingTime() {
        return pdBlinkingTime;
    }

    public Interval getPauseTime() {
        return pauseTime;
    }
    
}
