/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.webappscoursework.ejbs;

import com.jamesfernando.webappscoursework.entities.Project;
import com.jamesfernando.webappscoursework.entities.Student;
import com.jamesfernando.webappscoursework.entities.SystemUser;
import com.jamesfernando.webappscoursework.entities.SystemUserGroup;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author James
 */
@Stateless
public class StudentStorageServiceBean implements StudentStorageService {

  @PersistenceContext
    EntityManager em;
    
    @Override
    public synchronized List<Student> getStudentList() {
        return em.createNamedQuery("findAllStudents").getResultList();
    }

    @Override
    public synchronized void insertStudent( String sussexId, String password, String name, String surname, String emailAddress, String course) {
         try {
            Student student;
            SystemUserGroup sys_user_group;

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes("UTF-8"));
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
                sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }
            String paswdToStoreInDB = sb.toString();

            // apart from the default constructor which is required by JPA
            // you need to also implement a constructor that will make the following code succeed
            student = new Student(sussexId, paswdToStoreInDB, name, surname, emailAddress, course);
            sys_user_group = new SystemUserGroup(sussexId, "Student");

            em.persist(student);
            em.persist(sys_user_group);

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(StartupAddAdminUserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateStudent(Student student) {
        em.merge(student);
    }
    
    
}
