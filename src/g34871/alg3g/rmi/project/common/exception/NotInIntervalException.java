/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package g34871.alg3g.rmi.project.common.exception;

/**
 *
 * @author kevin
 */
public class NotInIntervalException extends Exception {

    /**
     * Creates a new instance of
     * <code>NotInIntervalException</code> without detail message.
     */
    public NotInIntervalException() {
    }

    /**
     * Constructs an instance of
     * <code>NotInIntervalException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public NotInIntervalException(String msg) {
        super(msg);
    }
}
