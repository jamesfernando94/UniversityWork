package com.jamesfernando.transactions.ejb;

import com.jamesfernando.transactions.entity.ExerciseEntity;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@TransactionAttribute(NOT_SUPPORTED)
public class TransactionEJB {

    @PersistenceContext(unitName = "TransactionExercisePU")
    EntityManager em;

    public int read(long id) {
        ExerciseEntity e = em.find(ExerciseEntity.class, id);
        return e.getRecordValue();
    }

    public void write(long id, int value) {
        ExerciseEntity e = em.find(ExerciseEntity.class, id);
        e.setRecordValue(value);
        em.persist(e);
        em.flush();
    }

}
