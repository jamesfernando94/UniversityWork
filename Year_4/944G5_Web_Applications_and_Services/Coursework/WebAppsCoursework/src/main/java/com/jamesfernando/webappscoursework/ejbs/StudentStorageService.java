/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.webappscoursework.ejbs;

import com.jamesfernando.webappscoursework.entities.Project;
import com.jamesfernando.webappscoursework.entities.Student;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author James
 */
@Local
public interface StudentStorageService {

    public List<Student> getStudentList();

    public void insertStudent( String sussexId, String password, String name, String surname, String emailAddress, String course);

    public void updateStudent(Student student);
}
