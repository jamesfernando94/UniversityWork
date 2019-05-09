package com.jamesfernando.transactions.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.Version;

/**
 * @author ianw
 * This is a very simple Java class representing a Persistence entity.
 * Only 2 such entities will be inserted in the Database when the application is deployed - see InitSingleton EJB whose PostConstruct method is called on @Startup.
 * Both Entity Instances (representing x and y respectively) will be initialized to 0.
 */
@Entity
public class ExerciseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int recordValue;
//    @Version
//    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRecordValue() {
        return recordValue;
    }

    public void setRecordValue(int recordValue) {
        this.recordValue = recordValue;
    }
//
//    public Long getVersion() {
//        return version;
//    }
//
//    public void setVersion(Long version) {
//        this.version = version;
//    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (this.id != null ? this.id.hashCode() : 0);
        //hash = 41 * hash + (this.version != null ? this.version.hashCode() : 0);
        hash = 41 * hash + this.recordValue;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ExerciseEntity other = (ExerciseEntity) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
//        if (this.version != other.version && (this.version == null || !this.version.equals(other.version))) {
//            return false;
//        }
        return this.recordValue == other.recordValue;
    }

    @Override
    public String toString() {
        return "TransactionExerciseEntity[ id=" + id + " ]";
    }

}
