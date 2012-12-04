/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package g34871.alg3g.rmi.view.image;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author kevin
 */
public class PedestrianLightImage extends LightImage {
    
    public PedestrianLightImage(ScaleType scale, Rotate rotation) {
        super(scale, rotation);
    }
    
    @Override
    public void toGreen() {
        this.setImage(LightImages.pedestrianLightGreenImage);
    }

    @Override
    public void toRed() {
        this.setImage(LightImages.pedestrianLightRedImage);
    }

    @Override
    public void toIntermediate() {
        this.setImage(LightImages.pedestrianLightGreenImage);
    }

    @Override
    public void toOff() {
        this.setImage(LightImages.pedestrianLightOffImage);
    }
}
