/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.webappscoursework.jsfs;

import com.jamesfernando.webappscoursework.ejbs.ProjectStorageService;
import com.jamesfernando.webappscoursework.ejbs.SupervisorStorageService;
import com.jamesfernando.webappscoursework.entities.ProjectStatus;
import com.jamesfernando.webappscoursework.entities.ProjectTopic;
import com.jamesfernando.webappscoursework.entities.Supervisor;
import java.util.Collection;
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
public class ProjectRegistrationBean {

    @EJB
    ProjectStorageService projectStorageService;

    @EJB
    SupervisorStorageService supervisorStorageService;

    String title;
    String description;
    String requiredSkills;
    Collection<ProjectTopic> projectTopics;

    public String register() {
        String supervisorSussexId = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        if (supervisorStorageService.doesSupervisorExist(supervisorSussexId)) {
            Supervisor supervisor = supervisorStorageService.getSupervisor(supervisorSussexId);
            projectStorageService.insertProject(title, description, requiredSkills, ProjectStatus.Available, projectTopics, supervisor);
            return "index";
        } else {
            return "error";
        }
    }

    public ProjectStorageService getProjectStorageService() {
        return projectStorageService;
    }

    public void setProjectStorageService(ProjectStorageService projectStorageService) {
        this.projectStorageService = projectStorageService;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public SupervisorStorageService getSupervisorStorageService() {
        return supervisorStorageService;
    }

    public void setSupervisorStorageService(SupervisorStorageService supervisorStorageService) {
        this.supervisorStorageService = supervisorStorageService;
    }

    public Collection<ProjectTopic> getProjectTopics() {
        return projectTopics;
    }

    public void setProjectTopics(Collection<ProjectTopic> projectTopics) {
        this.projectTopics = projectTopics;
    }


}
