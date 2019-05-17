/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.webappscoursework.jsfs;

import com.jamesfernando.webappscoursework.ejbs.ProjectStorageService;
import com.jamesfernando.webappscoursework.ejbs.StudentStorageService;
import com.jamesfernando.webappscoursework.ejbs.SupervisorStorageService;
import com.jamesfernando.webappscoursework.entities.Project;
import com.jamesfernando.webappscoursework.entities.ProjectStatus;
import com.jamesfernando.webappscoursework.entities.ProjectTopic;
import com.jamesfernando.webappscoursework.entities.Student;
import com.jamesfernando.webappscoursework.entities.Supervisor;
import java.util.Collection;
import java.util.Optional;
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
public class StudentProjectRegistrationBean {

    @EJB
    ProjectStorageService projectStorageService;

    @EJB
    SupervisorStorageService supervisorStorageService;

    @EJB
    StudentStorageService studentStorageService;

    String title;
    String description;
    String requiredSkills;
    Collection<ProjectTopic> projectTopics;
    String supervisorSussexId;

    public String register() {
        String studentSussexId = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        Optional<Student> student = studentStorageService.getStudentList().stream().filter(stu -> stu.getSussexId().equals(studentSussexId)).findFirst();
        Optional<Supervisor> supervisor = supervisorStorageService.getSupervisorList().stream().filter(superv -> superv.getSussexId().equalsIgnoreCase(supervisorSussexId)).findFirst();
        if (student.isPresent() && supervisor.isPresent() && student.get().getProject() == null) {
            Project project = new Project(title, description, requiredSkills, ProjectStatus.Proposed, projectTopics, supervisor.get());
            student.get().setProject(project);
            projectStorageService.insertProject(project);
            studentStorageService.updateStudent(student.get());
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
    

}
