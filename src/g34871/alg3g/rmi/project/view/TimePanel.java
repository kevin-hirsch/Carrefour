/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package g34871.alg3g.rmi.project.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author kevin
 */
public class TimePanel extends JPanel {

    private JLabel image;
    private JSlider slider;
    private JLabel label;
    public static final String CURRENT_VALUE_CHANGED = "current value changed";

    public TimePanel(String name, String imagePath, int min, int max,
            int currentValue, int majorTickSpacing, int minorTickSpacing) {
        label = new JLabel(name + " :");

        image = new JLabel(new ImageIcon(imagePath));

        slider = new JSlider();
        slider.setMajorTickSpacing(majorTickSpacing);
        slider.setMinorTickSpacing(minorTickSpacing);
        slider.setMaximum(max);
        slider.setMinimum(min);
        slider.setValue(currentValue);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                fireEvent(ce);
            }
        });
        init();
    }

    private void init() {
        this.setLayout(new java.awt.GridBagLayout());

        GridBagConstraints gridBagConstraints;
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 10, 0, 0);
        this.add(image, gridBagConstraints);

        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);
        slider.setPreferredSize(new Dimension(350, 50));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        this.add(slider, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 10, 0, 0);
        this.add(label, gridBagConstraints);
    }

    private void fireEvent(ChangeEvent pce) {
        firePropertyChange(CURRENT_VALUE_CHANGED, null, slider.getValue());
    }

    public void setIconImage(String imagePath) {
        image.setIcon(new ImageIcon(imagePath));
    }
}
