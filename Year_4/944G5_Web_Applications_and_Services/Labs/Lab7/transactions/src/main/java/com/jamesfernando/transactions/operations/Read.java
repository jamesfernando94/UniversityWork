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
public class Read extends Operation {

    private final String id;
    private final HashMap<String, Integer> recordConversion = new HashMap<String, Integer>();

    public Read(String id) {
        super();
        recordConversion.put("x", 1);
        recordConversion.put("y", 2);
        this.id = id;
    }

    @Override
    public String execute(TransactionEJB bean) {
        return "Read " + bean.read(recordConversion.get(id)) + " from " + id + ";";
    }

    @Override
    public String toString() {
        return "READ(" + id + ")";
    }
}
