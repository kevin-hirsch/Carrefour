/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package g34871.alg3g.rmi;

import g34871.alg3g.rmi.common.crossroads.Axe;
import g34871.alg3g.rmi.common.crossroads.CrossroadsParameters;
import g34871.alg3g.rmi.common.exception.NotInIntervalException;
import g34871.alg3g.rmi.common.light.LightParameters;
import g34871.alg3g.rmi.server.Crossroads;
import g34871.alg3g.rmi.view.NewJFrame;
import g34871.alg3g.rmi.view.PedestrianLightGUI;
import g34871.alg3g.rmi.view.TrafficLightGUI;
import java.awt.HeadlessException;
import java.io.IOException;
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
            LightParameters lp = new LightParameters(5000, 2000, 5000, 1500, 500, 300);
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
            
            new NewJFrame(crossr).setVisible(true);
        } catch (HeadlessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotInIntervalException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
