/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.webappscoursework.ejbs;

import com.jamesfernando.webappscoursework.entities.SystemUserGroup;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author James
 */
@Stateless
public class SystemUserGroupStorageServiceBean implements SystemUserGroupStorageService {

    @PersistenceContext
    EntityManager em;
    
    @Override
    public synchronized List<SystemUserGroup> getSystemUserGroupList() {
        return em.createNamedQuery("findAllSystemUserGroups").getResultList();
    }

    @Override
    public synchronized void insertSystemUserGroup(String sussexId, String groupName) {
        SystemUserGroup systemUserGroup = new SystemUserGroup(sussexId, groupName);
        em.persist(systemUserGroup);
    }

}
