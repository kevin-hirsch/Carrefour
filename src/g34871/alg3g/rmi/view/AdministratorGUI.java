/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package g34871.alg3g.rmi.view;

import g34871.alg3g.rmi.common.crossroads.CrossroadsParameters;
import g34871.alg3g.rmi.common.exception.NotInIntervalException;
import g34871.alg3g.rmi.common.light.LightParameters;
import g34871.alg3g.rmi.common.utils.Interval;
import g34871.alg3g.rmi.server.Crossroads;
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author kevin
 */
public class AdministratorGUI extends JFrame {

    private Crossroads crossroads;
    private JPanel container;
    private JPanel timeParamsPanel;
    private TimePanel greenTimePanel;
    private TimePanel orangeTimePanel;
    private TimePanel redTimePanel;
    private TimePanel pauseTimePanel;
    private TimePanel blinkingTimePanel;

    public AdministratorGUI(Crossroads crossroads) {
        super("Administration");
        this.crossroads = crossroads;

        init();
    }

    private void init() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        container = (JPanel) this.getContentPane();

        timeParamsPanel = new JPanel();
        timeParamsPanel.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(new java.awt.Color(102, 102, 102), 1, true), "Paramètres de temps"));
        timeParamsPanel.setLayout(new BoxLayout(timeParamsPanel, BoxLayout.PAGE_AXIS));

        initTimePanels();

        container.add(timeParamsPanel);
        this.pack();
    }

    private void initTimePanels() {
        LightParameters lp = crossroads.getParameters().getLightParams();
        Interval intervalTime = lp.getGreenLightTime();
        greenTimePanel = new TimePanel("Feu Vert (s)", "images/green_light.png",
                intervalTime.getMin() / 1000, intervalTime.getMax() / 1000, intervalTime.getCurrentValue() / 1000, 1);

        timeParamsPanel.add(greenTimePanel);
        
        intervalTime = lp.getOrangeLightTime();
        orangeTimePanel = new TimePanel("Feu Orange (ms)", "images/orange_light.png",
                intervalTime.getMin(), intervalTime.getMax(), intervalTime.getCurrentValue(), 1000);

        timeParamsPanel.add(orangeTimePanel);
        
        intervalTime = lp.getRedLightTime();
        redTimePanel = new TimePanel("Feu Rouge (s)", "images/red_light.png",
                intervalTime.getMin() / 1000, intervalTime.getMax() / 1000, intervalTime.getCurrentValue() / 1000, 1);

        timeParamsPanel.add(redTimePanel);
    }

    public static void main(String[] args) {
        try {
            LightParameters lp = new LightParameters(4000, 3000, 5000, 1000, 500, 300);
            CrossroadsParameters cp = new CrossroadsParameters(lp);
            Crossroads crossr = new Crossroads(cp);

            new AdministratorGUI(crossr).setVisible(true);
        } catch (NotInIntervalException ex) {
            Logger.getLogger(AdministratorGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
