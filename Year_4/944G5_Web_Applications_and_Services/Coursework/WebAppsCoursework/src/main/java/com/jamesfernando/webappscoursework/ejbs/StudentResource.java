/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.webappscoursework.ejbs;

import com.jamesfernando.webappscoursework.entities.Project;
import com.jamesfernando.webappscoursework.entities.Supervisor;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author James
 */
@Path("/student/{supervisorid}")
public class StudentResource {

    @Context
    private UriInfo context;
    @EJB
    SupervisorStorageService supervisorStorageService;
    @EJB
    StudentStorageService studentStorageService;
    /**
     * Creates a new instance of StudentResource
     */
    public StudentResource() {
    }

    /**
     * Retrieves representation of an instance of com.jamesfernando.webappscoursework.ejbs.StudentResource
     * @param supervisorid resource URI parameter
     * @return an instance of java.util.List
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List getJson(@PathParam("supervisorid") String supervisorid) {
        Supervisor supervisor = supervisorStorageService.getSupervisor(supervisorid);
        Collection<Project> projects = supervisor.getProjects();
        return studentStorageService.getStudentList().stream().filter(student -> projects.contains(student.getProject())).collect(Collectors.toList());

    }


}
