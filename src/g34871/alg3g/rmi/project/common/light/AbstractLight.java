/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package g34871.alg3g.rmi.project.common.light;

import g34871.alg3g.rmi.project.common.crossroads.Axe;

/**
 *
 * @author kevin
 */
public abstract class AbstractLight implements LightInterface {
    
    protected LightState state;
    private Axe axe;

    public AbstractLight(Axe axe) {
        this.state = LightState.OFF;
        this.axe = axe;
    }

    @Override
    public void goGreen() {
        this.state = LightState.GREEN;
    }

    @Override
    public void goRed() {
        this.state = LightState.RED;
    }
    
    @Override
    public void goPause() {
        this.state = LightState.PAUSE;
    }

    @Override
    public Axe getAxe() {
        return axe;
    }

    @Override
    public LightState getState() {
        return state;
    }
    
}
