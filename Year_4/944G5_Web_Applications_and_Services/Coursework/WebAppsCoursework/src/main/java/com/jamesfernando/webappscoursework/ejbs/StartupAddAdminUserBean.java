/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.webappscoursework.ejbs;

import com.jamesfernando.webappscoursework.entities.SystemUser;
import com.jamesfernando.webappscoursework.entities.SystemUserGroup;
import java.io.UnsupportedEncodingException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author James
 */
@Startup
@Singleton
public class StartupAddAdminUserBean implements StartupAddAdminUser {

    @PersistenceContext
    EntityManager em;

    @Override
    @PostConstruct
    public void Initialise() {
        if (em.createNamedQuery("findAllSystemUsers").getResultList().stream().anyMatch(user -> "admin1".equals(((SystemUser)user).getSussexId()))) {
            return;
        }
        try {
            SystemUser sys_user;
            SystemUserGroup sys_user_group;

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String passwd = "admin1";
            md.update(passwd.getBytes("UTF-8"));
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
                sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }
            String paswdToStoreInDB = sb.toString();

            // apart from the default constructor which is required by JPA
            // you need to also implement a constructor that will make the following code succeed
            sys_user = new SystemUser("admin1", paswdToStoreInDB, "admin", "admin", "admin@admin.com");
            sys_user_group = new SystemUserGroup("admin1", "Administrator");

            em.persist(sys_user);
            em.persist(sys_user_group);

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(StartupAddAdminUserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
