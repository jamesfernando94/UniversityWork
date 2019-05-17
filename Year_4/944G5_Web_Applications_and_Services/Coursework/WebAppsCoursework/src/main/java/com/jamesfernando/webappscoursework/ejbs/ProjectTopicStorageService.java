/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.webappscoursework.ejbs;

import com.jamesfernando.webappscoursework.entities.Project;
import com.jamesfernando.webappscoursework.entities.ProjectTopic;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author James
 */
@Local
public interface ProjectTopicStorageService {
    public List<ProjectTopic> getProjectTopicsList();
    
    public void insertProjectTopic(String title, String description);
}
