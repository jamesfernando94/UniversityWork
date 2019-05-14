/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.webappscoursework.ejbs;

import com.jamesfernando.webappscoursework.entitys.Project;
import javax.ejb.Local;
import com.jamesfernando.webappscoursework.entitys.ProjectStatus;
import com.jamesfernando.webappscoursework.entitys.ProjectTopic;
import com.jamesfernando.webappscoursework.entitys.Supervisor;
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
}
