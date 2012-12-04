/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package g34871.alg3g.rmi.common.utils;

import g34871.alg3g.rmi.common.exception.NotInIntervalException;

/**
 *
 * @author kevin
 */
public class Interval {
    private int min;
    private int max;
    private int currentValue;

    public Interval(int min, int max, int currentValue) throws NotInIntervalException {
        this.min = min;
        this.max = max;
        setCurrentValue(currentValue);
    }

    public Interval(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) throws NotInIntervalException {
        if (currentValue < min || currentValue > max)
            throw new NotInIntervalException("la valeur n'est pas dans l'intervalle !");
        
        this.currentValue = currentValue;
    }
    
}
