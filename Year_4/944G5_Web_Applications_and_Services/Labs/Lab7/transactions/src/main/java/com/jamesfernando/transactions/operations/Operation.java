package com.jamesfernando.transactions.operations;

import com.jamesfernando.transactions.ejb.TransactionEJB;

/**
 *
 * @author ianw
 */
public abstract class Operation {

    public abstract String execute(TransactionEJB bean);
}
