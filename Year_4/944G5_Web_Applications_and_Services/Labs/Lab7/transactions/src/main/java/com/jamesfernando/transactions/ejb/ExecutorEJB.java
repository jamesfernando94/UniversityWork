package com.jamesfernando.transactions.ejb;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.jamesfernando.transactions.operations.Operation;

@Stateless
@TransactionAttribute(NOT_SUPPORTED)
public class ExecutorEJB {

    @PersistenceContext(unitName = "TransactionExercisePU")
    EntityManager em;
    @EJB
    TransactionEJB tb;

    public String execute(List<Operation> operations) {
        try{
        String s = "";
        for (Operation o : operations) {
            s += o.execute(tb) + "\n";
        }
        return s;
        } catch (EJBTransactionRolledbackException trb) {
            return "EJBTransactionRolledbackException Was Caught and Transaction was Rolled Back..In Real life I should have done something here for the user (e.g. cancel a payment or reset and tell user to try again).!!!!";
        }
    }
}
