/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.webappscoursework.entitys;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@NamedQuery(name = "findAllStudents", query = "SELECT s FROM Student s")

/**
 *
 * @author James
 */
@Entity
public class Student extends SystemUser implements Serializable {

    @NotNull
    private String course;
    
    @OneToOne
    private Project project;
    
    public Student(){
        super();
    }
    
    public Student(String sussexId, String password, String name, String surname, String emailAddress, String course, Project project){
        super(sussexId, password, name, surname, emailAddress);
        this.course = course;
        this.project = project;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.course);
        hash = 97 * hash + Objects.hashCode(this.project);
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
        final Student other = (Student) obj;
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        if (!Objects.equals(this.project, other.project)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Student{" + "SystemUser=" + super.toString() + "course=" + course + ", project=" + project + '}';
    }

    
    
    

}
