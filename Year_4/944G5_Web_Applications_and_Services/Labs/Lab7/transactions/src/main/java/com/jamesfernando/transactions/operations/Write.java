/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.transactions.operations;

import com.jamesfernando.transactions.ejb.TransactionEJB;
import java.util.HashMap;

/**
 *
 * @author ianw
 */
public class Write extends Operation {

    private final String id;
    private final int value;
    private final HashMap<String, Integer> recordConversion = new HashMap<String, Integer>();

    public Write(String id, int value2) {
        super();
        recordConversion.put("x", 1);
        recordConversion.put("y", 2);
        this.id = id;
        this.value = value2;
    }

    @Override
    public String execute(TransactionEJB bean) {
        bean.write(recordConversion.get(id), value);
        return "Wrote " + value + " to " + id + ";";
    }

    @Override
    public String toString() {
        return "WRITE(" + id + "," + value + ")";
    }

}
