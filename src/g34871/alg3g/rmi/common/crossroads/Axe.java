/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package g34871.alg3g.rmi.common.crossroads;

/**
 *
 * @author kevin
 */
public enum Axe {
    NS_SN("Nord-Sud - Sud-Nord"),
    EW_WE("Est-Ouest - Ouest-Est");
    
    private String name;

    private Axe(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
