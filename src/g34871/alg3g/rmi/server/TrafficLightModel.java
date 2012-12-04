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
public class TrafficLightModel extends AbstractLight {

    public TrafficLightModel(Axe axe) {
        super(axe);
    }

    @Override
    public void goIntermediate() {
        this.state = LightState.ORANGE;
    }

    @Override
    public void goOffService() {
        this.state = LightState.BLINKING_ORANGE;
    }
    
}
