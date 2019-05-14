/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.webappscoursework.ejbs;

import com.jamesfernando.webappscoursework.entitys.Project;
import com.jamesfernando.webappscoursework.entitys.ProjectStatus;
import com.jamesfernando.webappscoursework.entitys.ProjectTopic;
import com.jamesfernando.webappscoursework.entitys.Supervisor;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author James
 */
@Stateless
public class ProjectStorageServiceBean implements ProjectStorageService {

    @PersistenceContext
    EntityManager em;

    @Override
    public synchronized List<Project> getProjectList() {
        return em.createNamedQuery("findAllProjects").getResultList();
    }

    @Override
    public synchronized void insertProject(String title, String description, String requiredSkills, ProjectStatus status, Collection<ProjectTopic> projectTopics, Supervisor supervisor) {
        Project project = new Project(title, description, requiredSkills, status, projectTopics, supervisor);
        em.persist(project);
    }


}
