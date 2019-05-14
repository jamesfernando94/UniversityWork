/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.webappscoursework.ejbs;

import com.jamesfernando.webappscoursework.entitys.Project;
import com.jamesfernando.webappscoursework.entitys.Student;
import java.util.List;
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
    public synchronized void insertStudent( String sussexId, String password, String name, String surname, String emailAddress, String course, Project project) {
        Student student = new Student(sussexId, password, name, surname, emailAddress, course, project);
        em.persist(student);
    }
}
