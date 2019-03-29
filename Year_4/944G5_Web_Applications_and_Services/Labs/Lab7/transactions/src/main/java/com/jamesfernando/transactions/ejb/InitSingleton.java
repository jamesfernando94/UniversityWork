package com.jamesfernando.transactions.ejb;

import com.jamesfernando.transactions.entity.ExerciseEntity;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/*This EJB will be instantiated only once when the applciation is deployed - @Startup and @Singleton respectively*/
@Startup
@Singleton
public class InitSingleton {

    @PersistenceContext(unitName = "TransactionExercisePU")
    EntityManager em;

    @PostConstruct
    public void dbInit() {
        System.out.println("At startup: Initialising Datbase with rows (x,y) with initial values 0");
        // DB Primary Keys start at 1!
        for (long i = 1; i <= 2; i++) {
            ExerciseEntity e = new ExerciseEntity();
            e.setRecordValue(0);
            em.persist(e);
            em.flush();
        }
    }
}
