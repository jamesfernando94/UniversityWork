/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.webappscoursework.ejbs;

import com.jamesfernando.webappscoursework.entities.SystemUser;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author James
 */
@Local
public interface SystemUserStorageService {

    public List<SystemUser> getSystemUserList();

    public void insertAdministrator(String sussexId, String password, String name, String surname, String emailAddress);
    
    public boolean userWithSussexIdExists(String sussexId);
}
