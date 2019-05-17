/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.webappscoursework.jsfs;

import com.jamesfernando.webappscoursework.ejbs.SupervisorStorageService;
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
public class SupervisorRegistrationBean {

    @EJB
    SupervisorStorageService supervisorStorageService;
    @EJB
    SystemUserStorageService systemUserStorageService;
    String sussexId;
    String password;
    String name;
    String surname;
    String emailAddress;
    String department;
    String telephoneNumber;

    public SupervisorRegistrationBean() {

    }

    //call the injected EJB
    public String register() {
        if (systemUserStorageService.userWithSussexIdExists(sussexId)) {
            return "error";
        }
        supervisorStorageService.insertSupervisor(sussexId, password, name, surname, emailAddress, department, telephoneNumber);
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

    public SupervisorStorageService getSupervisorStorageService() {
        return supervisorStorageService;
    }

    public void setSupervisorStorageService(SupervisorStorageService supervisorStorageService) {
        this.supervisorStorageService = supervisorStorageService;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

}
