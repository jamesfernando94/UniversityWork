/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.webappscoursework.jsfs;

import com.jamesfernando.webappscoursework.ejbs.ProjectStorageService;
import com.jamesfernando.webappscoursework.ejbs.SupervisorStorageService;
import com.jamesfernando.webappscoursework.entities.Project;
import com.jamesfernando.webappscoursework.entities.ProjectStatus;
import com.jamesfernando.webappscoursework.entities.Supervisor;
import java.util.ArrayList;
import java.util.List;
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
public class SupervisorProjectsBean {

    @EJB
    SupervisorStorageService supervisorStorageService;

    @EJB
    ProjectStorageService projectStorageService;

    String supervisorSussexId;
    List<Project> supervisorProjects;

    public SupervisorProjectsBean() {
        supervisorSussexId = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    }

    public void acceptProject(Project project) {
        project.setStatus(ProjectStatus.Accepted);
        projectStorageService.updateProject(project);
        updateProjectList();

    }

    public void rejectProject(Project project) {
        project.setStatus(ProjectStatus.Rejected);
        projectStorageService.updateProject(project);
        updateProjectList();
    }

    public void updateProjectList() {
        if (supervisorStorageService.doesSupervisorExist(supervisorSussexId)) {
            Supervisor supervisor = supervisorStorageService.getSupervisor(supervisorSussexId);
            supervisorProjects = new ArrayList<>(supervisor.getProjects());
        }
    }

    public List<Project> getSupervisorProjects() {
        if (supervisorProjects == null) {
            updateProjectList();
        }
        return supervisorProjects;
    }

    public void setSupervisorProjects(List<Project> supervisorProjects) {
        this.supervisorProjects = supervisorProjects;
    }

}
