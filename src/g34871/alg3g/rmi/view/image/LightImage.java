/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package g34871.alg3g.rmi.view.image;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author kevin
 */
public abstract class LightImage extends JPanel {

    private BufferedImage image;
    private AffineTransform at;

    public LightImage(ScaleType scale, Rotate rotation) {
        toOff();
        at = new AffineTransform();
        at.scale(scale.getScale(), scale.getScale());
        at.rotate(Math.toRadians(rotation.getRotationDegress()));

        int imageWidth, imageHeight;

        switch (rotation) {
            case A_QUARTER:
                at.translate(0, -image.getHeight());
                break;
            case THREE_QUARTER:
                at.translate(-image.getWidth(), 0);
                break;
            case UPSIDE_DOWN:
                at.translate(-image.getWidth(), -image.getHeight());
                break;
        }

        if (rotation == Rotate.A_QUARTER || rotation == Rotate.THREE_QUARTER) {
            imageWidth = image.getHeight();
            imageHeight = image.getWidth();
        } else {
            imageWidth = image.getWidth();
            imageHeight = image.getHeight();
        }

        this.setSize(new Dimension((int) (imageWidth * scale.getScale()), (int) (imageHeight * scale.getScale())));
        this.setPreferredSize(this.getSize());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, at, null);
    }

    protected void setImage(BufferedImage image) {
        this.image = image;
        this.repaint();
    }

    protected BufferedImage getImage() {
        return image;
    }

    public abstract void toGreen();

    public abstract void toRed();

    public abstract void toIntermediate();

    public abstract void toOff();

    public enum ScaleType {

        REAL_SIZE(1),
        SMALL_SIZE(0.16);
        private double scale;

        private ScaleType(double scale) {
            this.scale = scale;
        }

        public double getScale() {
            return scale;
        }
    }

    public enum Rotate {

        NONE(0),
        A_QUARTER(90),
        UPSIDE_DOWN(180),
        THREE_QUARTER(270);
        private int rotation;

        private Rotate(int rotation) {
            this.rotation = rotation;
        }

        public int getRotationDegress() {
            return rotation;
        }
    }
    
}
