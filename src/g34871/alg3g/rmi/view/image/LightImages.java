/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package g34871.alg3g.rmi.view.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author kevin
 */
public class LightImages {

    public static BufferedImage trafficLightOffImage = readImage("images/traffic_light_off.png");
    public static BufferedImage trafficLightGreenImage = readImage("images/traffic_light_green.png");
    public static BufferedImage trafficLightOrangeImage = readImage("images/traffic_light_orange.png");
    public static BufferedImage trafficLightRedImage = readImage("images/traffic_light_red.png");
    public static BufferedImage pedestrianLightOffImage  = readImage("images/pedestrian_light_off.png");
    public static BufferedImage pedestrianLightGreenImage  = readImage("images/pedestrian_light_green.png");
    public static BufferedImage pedestrianLightRedImage  = readImage("images/pedestrian_light_red.png");

    public static BufferedImage readImage(String fileName) {
        try {
            return ImageIO.read(new File(fileName));
        } catch (IOException ex) {
            Logger.getLogger(LightImages.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
