/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package g34871.alg3g.rmi.view.image;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author kevin
 */
public class TrafficLightImage extends LightImage {

    public TrafficLightImage(ScaleType scale, Rotate rotation) {
        super(scale, rotation);
    }

    public static void main(String[] args) {
        JFrame t = new JFrame();
        t.setLayout(new BorderLayout());
        t.getContentPane().add(
                new TrafficLightImage(
                ScaleType.SMALL_SIZE, Rotate.UPSIDE_DOWN),
                BorderLayout.CENTER);
        t.setVisible(true);
        t.pack();
    }
    
    @Override
    public void toGreen() {
        this.setImage(LightImages.trafficLightGreenImage);
    }

    @Override
    public void toRed() {
        this.setImage(LightImages.trafficLightRedImage);
    }

    @Override
    public void toIntermediate() {
        this.setImage(LightImages.trafficLightOrangeImage);
    }

    @Override
    public void toOff() {
        this.setImage(LightImages.trafficLightOffImage);
    }
}
