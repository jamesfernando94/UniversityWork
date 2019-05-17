/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.webappscoursework.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@NamedQuery(name = "findAllSupervisors", query = "SELECT s FROM Supervisor s")
/**
 *
 * @author James
 */
@Entity
public class Supervisor extends SystemUser implements Serializable {

    @NotNull
    private String department;

    @NotNull
    private String telephoneNumber;
    
    @OneToMany(mappedBy = "supervisor")
    private Collection<Project> projects;

    public Supervisor() {
        super();
    }

    public Supervisor(String sussexId, String password, String name, String surname, String emailAddress, String department, String telephoneNumber, Collection<Project> projects) {
        super(sussexId, password, name, surname, emailAddress);
        this.department = department;
        this.telephoneNumber = telephoneNumber;
        this.projects = projects;
    }
    public Supervisor(String sussexId, String password, String name, String surname, String emailAddress, String department, String telephoneNumber) {
        super(sussexId, password, name, surname, emailAddress);
        this.department = department;
        this.telephoneNumber = telephoneNumber;
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

    public Collection<Project> getProjects() {
        return projects;
    }

    public void setProjects(Collection<Project> projects) {
        this.projects = projects;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.department);
        hash = 13 * hash + Objects.hashCode(this.telephoneNumber);
        hash = 13 * hash + Objects.hashCode(this.projects);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Supervisor other = (Supervisor) obj;
        if (!Objects.equals(this.department, other.department)) {
            return false;
        }
        if (!Objects.equals(this.telephoneNumber, other.telephoneNumber)) {
            return false;
        }
        if (!Objects.equals(this.projects, other.projects)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Supervisor{"+ "SystemUser=" + super.toString() + "department=" + department + ", telephoneNumber=" + telephoneNumber + ", projects=" + projects + '}';
    }



}
