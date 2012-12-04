/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package g34871.alg3g.rmi.common.light;

import g34871.alg3g.rmi.common.crossroads.Axe;

/**
 *
 * @author kevin
 */
public interface LightInterface {
    public void goGreen();
    public void goRed();
    public void goPause();
    public void goIntermediate();
    public void goOffService();
    public Axe getAxe();
    public LightState getState();
}
