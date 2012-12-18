/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package g34871.alg3g.rmi.project.common.crossroads;

import g34871.alg3g.rmi.project.common.light.LightParameters;

/**
 *
 * @author kevin
 */
public class CrossroadsParameters {
    private LightParameters lightParams;

    public CrossroadsParameters(LightParameters lightParams) {
        this.lightParams = lightParams;
    }

    public LightParameters getLightParams() {
        return lightParams;
    }
}
