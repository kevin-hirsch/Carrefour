/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package g34871.alg3g.rmi.server;

import g34871.alg3g.rmi.common.light.AbstractLight;
import g34871.alg3g.rmi.common.crossroads.Axe;
import g34871.alg3g.rmi.common.light.LightState;

/**
 *
 * @author kevin
 */
public class PedestrianLightModel extends AbstractLight {

    public PedestrianLightModel(Axe axe) {
        super(axe);
    }

    @Override
    public void goIntermediate() {
        this.state = LightState.BLINKING_GREEN;
    }

    @Override
    public void goOffService() {
        this.state = LightState.OFF;
    }
    
}
