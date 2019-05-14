/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.webappscoursework.ejbs;

import com.jamesfernando.webappscoursework.entitys.SystemUser;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author James
 */
@Stateless
public class SystemUserStorageServiceBean implements SystemUserStorageService {

    @PersistenceContext
    EntityManager em;

    @Override
    public synchronized List<SystemUser> getSystemUserList() {
        return em.createNamedQuery("findAllSystemUsers").getResultList();
    }

    @Override
    public void insertSystemUser(String sussexId, String password, String name, String surname, String emailAddress) {
        SystemUser systemUser = new SystemUser(sussexId, password, name, surname, emailAddress);
        em.persist(systemUser);
    }

}
