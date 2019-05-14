/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.webappscoursework.ejbs;

import com.jamesfernando.webappscoursework.entitys.Project;
import com.jamesfernando.webappscoursework.entitys.Student;
import com.jamesfernando.webappscoursework.entitys.Supervisor;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author James
 */
@Stateless
public class SupervisorStorageServiceBean implements SupervisorStorageService {

    @PersistenceContext
    EntityManager em;
    
    @Override
    public synchronized List<Supervisor> getSupervisorList() {
        return em.createNamedQuery("findAllSupervisors").getResultList();
    }

    @Override
    public synchronized void insertSupervisor(String sussexId, String password, String name, String surname, String emailAddress, String department, String telephoneNumber, Collection<Project> projects) {
        Supervisor supervisor = new Supervisor(sussexId, password, name, surname, emailAddress, department, telephoneNumber, projects);
        em.persist(supervisor);
    }


}
