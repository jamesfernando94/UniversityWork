/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.webappscoursework.jsfs;

import com.jamesfernando.webappscoursework.ejbs.SystemUserStorageService;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author James
 */
@Named
@RequestScoped
public class AdminRegistrationBean {

    @EJB
    SystemUserStorageService systemUserStorageService;
    
    String sussexId;
    String password;
    String name;
    String surname;
    String emailAddress;

    public AdminRegistrationBean() {

    }

    //call the injected EJB
    public String register() {
        if (systemUserStorageService.userWithSussexIdExists(sussexId)) {
            return "error";
        }
        systemUserStorageService.insertAdministrator(sussexId, password, name, surname, emailAddress);
        return "index";
    }

    public String getSussexId() {
        return sussexId;
    }

    public void setSussexId(String sussexId) {
        this.sussexId = sussexId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public SystemUserStorageService getSystemUserStorageService() {
        return systemUserStorageService;
    }

    public void setSystemUserStorageService(SystemUserStorageService systemUserStorageService) {
        this.systemUserStorageService = systemUserStorageService;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
}
