/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package g34871.alg3g.rmi.project.view;

import g34871.alg3g.rmi.project.common.crossroads.CrossroadsParameters;
import g34871.alg3g.rmi.project.common.light.LightParameters;
import g34871.alg3g.rmi.project.common.utils.Interval;
import g34871.alg3g.rmi.project.server.Crossroads;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

/**
 *
 * @author kevin
 */
public class AdministratorGUI extends JFrame {

    private Crossroads crossroads;
    private CrossroadsParameters params;
    private JPanel container;
    private JPanel timeParamsPanel;
    private TimePanel greenTimePanel;
    private TimePanel orangeTimePanel;
    private TimePanel redTimePanel;
    private TimePanel pauseTimePanel;
    private TimePanel blinkingTimePanel;
    private JToggleButton btOffService;
    private Timer blinkingTrOrangeTimer;
    private boolean isBlinkinAndOrange;
    private static final int TIME_RATE = 1000;

    public AdministratorGUI(Crossroads crossroads) {
        super("Administration");
        this.crossroads = crossroads;
        this.params = crossroads.getParameters();

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
        initTimer();

        btOffService = new JToggleButton("Mettre hors service");
        btOffService.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btOffServiceClicked();
            }
        });
        container.add(btOffService, BorderLayout.NORTH);

        container.add(timeParamsPanel);
        this.pack();
    }

    private void initTimer() {
        isBlinkinAndOrange = false;
        blinkingTrOrangeTimer = new Timer(crossroads.getParameters().
                getLightParams().getTrBlinkingTime().getCurrentValue(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                blinkOrange();
            }
        });

        blinkingTrOrangeTimer.start();
    }

    private void initTimePanels() {
        LightParameters lp = params.getLightParams();
        Interval intervalTime = lp.getTrGreenTime();
        greenTimePanel = new TimePanel("Feu Vert (s)", "images/green_light.png",
                intervalTime.getMin() / TIME_RATE, intervalTime.getMax() / TIME_RATE,
                intervalTime.getCurrentValue() / TIME_RATE, 1, 1);

        timeParamsPanel.add(greenTimePanel);

        intervalTime = lp.getTrOrangeTime();
        orangeTimePanel = new TimePanel("Feu Orange (ms)", "images/orange_light.png",
                intervalTime.getMin(), intervalTime.getMax(), intervalTime.getCurrentValue(), 1000, 500);

        timeParamsPanel.add(orangeTimePanel);

        intervalTime = lp.getTrRedTime();
        redTimePanel = new TimePanel("Feu Rouge (s)", "images/red_light.png",
                intervalTime.getMin() / TIME_RATE, intervalTime.getMax() / TIME_RATE,
                intervalTime.getCurrentValue() / TIME_RATE, 1, 1);

        timeParamsPanel.add(redTimePanel);

        intervalTime = lp.getPauseTime();
        pauseTimePanel = new TimePanel("Pause (ms)", "images/pause_light.png",
                intervalTime.getMin(), intervalTime.getMax(),
                intervalTime.getCurrentValue(), 1000, 500);

        timeParamsPanel.add(pauseTimePanel);

        intervalTime = lp.getTrBlinkingTime();
        blinkingTimePanel = new TimePanel("Feu Orange Clignotant (ms)", "images/orange_light.png",
                intervalTime.getMin(), intervalTime.getMax(),
                intervalTime.getCurrentValue(), 500, 100);
        
        blinkingTimePanel.addPropertyChangeListener(TimePanel.CURRENT_VALUE_CHANGED,
                new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent pce) {
                        setBlinkingTime(pce);
                    }
                });

        timeParamsPanel.add(blinkingTimePanel);
    }

    private void btOffServiceClicked() {
        if (btOffService.isSelected()) {
            crossroads.putOffService();
        } else {
            crossroads.restoreService();
        }
    }

    private void blinkOrange() {
        if (isBlinkinAndOrange) {
            blinkingTimePanel.setIconImage("images/orange_light.png");
        } else {
            blinkingTimePanel.setIconImage("images/orange_off_light.png");
        }

        isBlinkinAndOrange = !isBlinkinAndOrange;
    }

    private void setBlinkingTime(PropertyChangeEvent pce) {
        blinkingTrOrangeTimer.setDelay((Integer) pce.getNewValue());
        blinkingTrOrangeTimer.restart();
    }
}
