/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package g34871.alg3g.rmi.project;

import g34871.alg3g.rmi.project.common.crossroads.Axe;
import g34871.alg3g.rmi.project.common.crossroads.CrossroadsParameters;
import g34871.alg3g.rmi.project.common.exception.NotInIntervalException;
import g34871.alg3g.rmi.project.common.light.LightParameters;
import g34871.alg3g.rmi.project.common.utils.Interval;
import g34871.alg3g.rmi.project.server.Crossroads;
import g34871.alg3g.rmi.project.view.AdministratorGUI;
import g34871.alg3g.rmi.project.view.CrossroadsGUI;
import g34871.alg3g.rmi.project.view.PedestrianLightGUI;
import g34871.alg3g.rmi.project.view.TrafficLightGUI;
import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ArrayList<Interval> ints = loadIntervalsFromProperties();
            LightParameters lp = new LightParameters(
                    ints.get(0), ints.get(1), ints.get(2), ints.get(3), ints.get(4), 
                    ints.get(5), ints.get(6), ints.get(7), ints.get(8)
                    );
            CrossroadsParameters cp = new CrossroadsParameters(lp);
            Crossroads crossr = new Crossroads(cp);

            TrafficLightGUI tr_lgt_ns = new TrafficLightGUI("Feu de signalisation", crossr, Axe.NS_SN);
            TrafficLightGUI tr_lgt_we = new TrafficLightGUI("Feu de signalisation", crossr, Axe.EW_WE);
            PedestrianLightGUI pd_lgt_ns = new PedestrianLightGUI("Feu de signalisation", crossr, Axe.NS_SN);
            PedestrianLightGUI pd_lgt_we = new PedestrianLightGUI("Feu de signalisation", crossr, Axe.EW_WE);

            tr_lgt_ns.setVisible(true);
            tr_lgt_we.setVisible(true);
            pd_lgt_ns.setVisible(true);
            pd_lgt_we.setVisible(true);

            tr_lgt_ns.setLocation(150, 150);
            tr_lgt_we.setLocation(270, 150);
            pd_lgt_ns.setLocation(150, 440);
            pd_lgt_we.setLocation(270, 440);

            new CrossroadsGUI("Carrefour", crossr).setVisible(true);
            new AdministratorGUI(crossr).setVisible(true);
        } catch (HeadlessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotInIntervalException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<Interval> loadIntervalsFromProperties() throws IOException, NotInIntervalException {
        ArrayList<Interval> intervals = new ArrayList<Interval>();

        Properties crossroadsProp = new Properties();
        InputStream in = new FileInputStream("src/g34871/alg3g/rmi/project/properties/crossroads.properties");
        crossroadsProp.load(in);
        Properties currentTimesProp = new Properties();
        currentTimesProp.load(new FileInputStream("src/g34871/alg3g/rmi/project/properties/currentLightTimes.properties"));
        
        Interval trRedTime = createInterval(
                Integer.parseInt(crossroadsProp.getProperty("trafficRedMinTime")),
                Integer.parseInt(crossroadsProp.getProperty("trafficRedMaxTime")),
                Integer.parseInt(currentTimesProp.getProperty("trafficRedCurrentTime"))
                );
        
        Interval trOrangeTime = createInterval(
                Integer.parseInt(crossroadsProp.getProperty("trafficOrangeMinTime")),
                Integer.parseInt(crossroadsProp.getProperty("trafficOrangeMaxTime")),
                Integer.parseInt(currentTimesProp.getProperty("trafficOrangeCurrentTime"))
                );
        
        Interval trGreenTime = createInterval(
                Integer.parseInt(crossroadsProp.getProperty("trafficGreenMinTime")),
                Integer.parseInt(crossroadsProp.getProperty("trafficGreenMaxTime")),
                Integer.parseInt(currentTimesProp.getProperty("trafficGreenCurrentTime"))
                );
        
        Interval trBlinkingTime = createInterval(
                Integer.parseInt(crossroadsProp.getProperty("trafficBlinkingMinTime")),
                Integer.parseInt(crossroadsProp.getProperty("trafficBlinkingMaxTime")),
                Integer.parseInt(currentTimesProp.getProperty("trafficBlinkingCurrentTime"))
                );
        
        Interval pdRedTime = createInterval(
                Integer.parseInt(crossroadsProp.getProperty("pedestrianRedMinTime")),
                Integer.parseInt(crossroadsProp.getProperty("pedestrianRedMaxTime")),
                Integer.parseInt(currentTimesProp.getProperty("pedestrianRedCurrentTime"))
                );
        
        Interval pdOrangeTime = createInterval(
                Integer.parseInt(crossroadsProp.getProperty("pedestrianOrangeMinTime")),
                Integer.parseInt(crossroadsProp.getProperty("pedestrianOrangeMaxTime")),
                Integer.parseInt(currentTimesProp.getProperty("pedestrianOrangeCurrentTime"))
                );
        
        Interval pdGreenTime = createInterval(
                Integer.parseInt(crossroadsProp.getProperty("pedestrianGreenMinTime")),
                Integer.parseInt(crossroadsProp.getProperty("pedestrianGreenMaxTime")),
                Integer.parseInt(currentTimesProp.getProperty("pedestrianGreenCurrentTime"))
                );
        
        Interval pdBlinkingTime = createInterval(
                Integer.parseInt(crossroadsProp.getProperty("pedestrianBlinkingMinTime")),
                Integer.parseInt(crossroadsProp.getProperty("pedestrianBlinkingMaxTime")),
                Integer.parseInt(currentTimesProp.getProperty("pedestrianBlinkingCurrentTime"))
                );
        
        Interval pauseTime = createInterval(
                Integer.parseInt(crossroadsProp.getProperty("pauseMinTime")),
                Integer.parseInt(crossroadsProp.getProperty("pauseMaxTime")),
                Integer.parseInt(currentTimesProp.getProperty("pauseCurrentTime"))
                );
        
        intervals.add(trRedTime);
        intervals.add(trOrangeTime);
        intervals.add(trGreenTime);
        intervals.add(trBlinkingTime);
        intervals.add(pdRedTime);
        intervals.add(pdOrangeTime);
        intervals.add(pdGreenTime);
        intervals.add(pdBlinkingTime);
        intervals.add(pauseTime);
        
        return intervals;
    }

    private static Interval createInterval(int min, int max, int current) throws NotInIntervalException {
        return new Interval(min, max, current);
    }
}
