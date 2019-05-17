/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.webappscoursework.jsfs;

import com.jamesfernando.webappscoursework.ejbs.ProjectTopicStorageService;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author James
 */
@Named
@RequestScoped
public class ProjectTopicRegistrationBean {

    @EJB
    ProjectTopicStorageService projectTopicStorageService;

    String topicTitle;
    String topicDescription;

    public String register() {
        projectTopicStorageService.insertProjectTopic(topicTitle, topicDescription);
        return "index";
    }

    public ProjectTopicStorageService getProjectTopicStorageService() {
        return projectTopicStorageService;
    }

    public void setProjectTopicStorageService(ProjectTopicStorageService projectTopicStorageService) {
        this.projectTopicStorageService = projectTopicStorageService;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getTopicDescription() {
        return topicDescription;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }

}
