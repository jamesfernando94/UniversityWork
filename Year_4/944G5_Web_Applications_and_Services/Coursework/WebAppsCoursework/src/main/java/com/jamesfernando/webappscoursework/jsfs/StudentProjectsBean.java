/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.webappscoursework.jsfs;

import com.jamesfernando.webappscoursework.ejbs.ProjectStorageService;
import com.jamesfernando.webappscoursework.ejbs.StudentStorageService;
import com.jamesfernando.webappscoursework.entities.Project;
import com.jamesfernando.webappscoursework.entities.ProjectStatus;
import com.jamesfernando.webappscoursework.entities.Student;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author James
 */
@Named
@RequestScoped
public class StudentProjectsBean {

    @EJB
    ProjectStorageService projectStorageService;
    @EJB
    StudentStorageService studentStorageService;

    List<Project> availableProjects;
    Student student;
    String studentSussexId;

    public StudentProjectsBean() {
        studentSussexId = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    }

    public void updateProjectList() {
        availableProjects = projectStorageService
                .getProjectList()
                .stream()
                .filter(project -> project.getStatus() == ProjectStatus.Available)
                .collect(Collectors.toList());
    }

    public void apply(Project project) {
        if (getStudent().getProject() != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("You may not apply for multiple projects"));
            return;
        }
        project.setStatus(ProjectStatus.Proposed);
        getStudent().setProject(project);
        projectStorageService.updateProject(project);
        studentStorageService.updateStudent(student);
    }

    public ProjectStorageService getProjectStorageService() {
        return projectStorageService;
    }

    public void setProjectStorageService(ProjectStorageService projectStorageService) {
        this.projectStorageService = projectStorageService;
    }

    public StudentStorageService getStudentStorageService() {
        return studentStorageService;
    }

    public void setStudentStorageService(StudentStorageService studentStorageService) {
        this.studentStorageService = studentStorageService;
    }

    public List<Project> getAvailableProjects() {
        if (availableProjects == null) {
            updateProjectList();
        }
        return availableProjects;
    }

    public void setAvailableProjects(List<Project> availableProjects) {
        this.availableProjects = availableProjects;
    }

    public Student getStudent() {
        if (student == null) {
            student = studentStorageService
                    .getStudentList()
                    .stream()
                    .filter(stu -> studentSussexId.equals(stu.getSussexId()))
                    .findFirst()
                    .get();
        }
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getStudentSussexId() {
        return studentSussexId;
    }

    public void setStudentSussexId(String studentSussexId) {
        this.studentSussexId = studentSussexId;
    }

}
