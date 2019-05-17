/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.webappscoursework.ejbs;

import com.jamesfernando.webappscoursework.entities.Supervisor;
import java.util.ArrayList;
import java.util.List;
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
@Path("/supervisor/{studentid}")
public class SupervisorResource {

    @Context
    private UriInfo context;
    @EJB
    SupervisorStorageService supervisorStorageService;
    @EJB
    StudentStorageService studentStorageService;

    /**
     * Creates a new instance of SupervisorResource
     */
    public SupervisorResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.jamesfernando.webappscoursework.ejbs.SupervisorResource
     *
     * @return an instance of
     * com.jamesfernando.webappscoursework.entities.Supervisor
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Supervisor> getJson(@PathParam("studentid") String studentId) {
        ArrayList supervisors = new ArrayList();
        if ("all".equalsIgnoreCase(studentId)) {
            supervisors.addAll(supervisorStorageService.getSupervisorList());
        } else {
            supervisors.add(studentStorageService.getStudentList().stream().filter(student -> studentId.equalsIgnoreCase(student.getSussexId())).findFirst().get().getProject().getSupervisor());
        }

        return supervisors;
    }

}
