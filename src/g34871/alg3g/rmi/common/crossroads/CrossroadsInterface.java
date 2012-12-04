/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package g34871.alg3g.rmi.common.crossroads;

import g34871.alg3g.rmi.common.light.LightInterface;

/**
 *
 * @author kevin
 */
public interface CrossroadsInterface {
    public void setSpeedRate(double rate);
    public double getSpeedRate();
    public void putOffService();
    public void rebootService();
    public boolean pedestrianButtonPushed(Axe axe);
    public LightInterface getTrafficLight(Axe axe);
    public LightInterface getPedestrianLight(Axe axe);
    public CrossroadsParameters getParameters();
    public void setParameters(CrossroadsParameters params); 
}
