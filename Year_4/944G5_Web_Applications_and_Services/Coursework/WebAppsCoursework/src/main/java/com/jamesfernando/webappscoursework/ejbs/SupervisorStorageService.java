/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.webappscoursework.ejbs;

import com.jamesfernando.webappscoursework.entities.Project;
import com.jamesfernando.webappscoursework.entities.Supervisor;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author James
 */
@Local
public interface SupervisorStorageService {

    public List<Supervisor> getSupervisorList();

    public void insertSupervisor(String sussexId, String password, String name, String surname, String emailAddress, String department, String telephoneNumber);
    
    public Supervisor getSupervisor(String sussexId);
    
    public boolean doesSupervisorExist(String sussexId);
}
