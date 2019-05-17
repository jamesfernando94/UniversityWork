/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.webappscoursework.jsfs;

import com.jamesfernando.webappscoursework.ejbs.StudentStorageService;
import com.jamesfernando.webappscoursework.ejbs.SupervisorStorageService;
import com.jamesfernando.webappscoursework.entities.Student;
import com.jamesfernando.webappscoursework.entities.Supervisor;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author James
 */
@Named
@RequestScoped
public class SupervisorStudentsBean {

    @EJB
    SupervisorStorageService supervisorStorageService;

    @EJB
    StudentStorageService studentStorageService;

    String supervisorSussexId;
    List<Student> supervisorStudentList;

    public SupervisorStudentsBean() {
        supervisorSussexId = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

    }

    private void updateStudentList() {
        if (supervisorStorageService.doesSupervisorExist(supervisorSussexId)) {
            Supervisor supervisor = supervisorStorageService.getSupervisor(supervisorSussexId);
            supervisorStudentList = studentStorageService
                    .getStudentList()
                    .stream()
                    .filter(student -> student.getProject() != null)
                    .filter(student -> Objects.equals(student.getProject().getSupervisor().getId(), supervisor.getId()))
                    .collect(Collectors.toList());
        }
    }

    public SupervisorStorageService getSupervisorStorageService() {
        return supervisorStorageService;
    }

    public void setSupervisorStorageService(SupervisorStorageService supervisorStorageService) {
        this.supervisorStorageService = supervisorStorageService;
    }

    public StudentStorageService getStudentStorageService() {
        return studentStorageService;
    }

    public void setStudentStorageService(StudentStorageService studentStorageService) {
        this.studentStorageService = studentStorageService;
    }

    public String getSupervisorSussexId() {
        return supervisorSussexId;
    }

    public void setSupervisorSussexId(String supervisorSussexId) {
        this.supervisorSussexId = supervisorSussexId;
    }

    public List<Student> getSupervisorStudentList() {
        updateStudentList();
        return supervisorStudentList;
    }

    public void setSupervisorStudentList(List<Student> supervisorStudentList) {
        this.supervisorStudentList = supervisorStudentList;
    }

}
