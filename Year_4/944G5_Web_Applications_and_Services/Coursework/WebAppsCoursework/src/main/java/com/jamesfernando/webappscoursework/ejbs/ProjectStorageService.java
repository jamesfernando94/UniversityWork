/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.webappscoursework.ejbs;

import com.jamesfernando.webappscoursework.entities.Project;
import javax.ejb.Local;
import com.jamesfernando.webappscoursework.entities.ProjectStatus;
import com.jamesfernando.webappscoursework.entities.ProjectTopic;
import com.jamesfernando.webappscoursework.entities.Supervisor;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author James
 */
@Local
public interface ProjectStorageService {

    public List<Project> getProjectList();

    public void insertProject(String title, String description, String requiredSkills, ProjectStatus status, Collection<ProjectTopic> projectTopics, Supervisor supervisor);
    public void insertProject(Project project);
    public void updateProject(Project project);
    
}
