/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.transactions.operations;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.jamesfernando.transactions.ejb.TransactionEJB;

/**
 *
 * @author ianw
 */
public class Delay extends Operation {
    private final long sleepPeriod;
    
    public Delay(long sleepSeconds) {
        this.sleepPeriod = 1000 * sleepSeconds;
    }
    
    @Override
    public String execute(TransactionEJB bean) {
        try {
            Thread.sleep(this.sleepPeriod);
            return "Sleep " + sleepPeriod + "ms;";
        } catch (InterruptedException ex) {
            Logger.getLogger(Delay.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }

       
    }
    @Override
    public String toString() {
        return "DELAY(" + sleepPeriod + "ms);";
    }
    
}
