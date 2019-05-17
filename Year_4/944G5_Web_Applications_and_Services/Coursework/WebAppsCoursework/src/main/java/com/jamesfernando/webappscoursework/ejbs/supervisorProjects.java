/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.webappscoursework.ejbs;

import com.jamesfernando.webappscoursework.entities.Project;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * REST Web Service
 *
 * @author James
 */
@Path("/project/{supervisorid}")
public class supervisorProjects {

    @Context
    private UriInfo context;

    @EJB
    SupervisorStorageService supervisorStorageService;

    
    /**
     * Creates a new instance of supervisorProjects
     */
    public supervisorProjects() {
    }

    /**
     * Retrieves representation of an instance of
     * com.jamesfernando.webappscoursework.ejbs.supervisorProjects
     *
     * @param supervisorId
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getJson(@PathParam("supervisorid") String supervisorId) {
        ObjectMapper mapper = new ObjectMapper();
        if ("all".equalsIgnoreCase(supervisorId)) {
            return supervisorStorageService
                    .getSupervisorList()
                    .stream()
                    .flatMap(supervisor -> supervisor.getProjects().stream())
                    .collect(Collectors.toList());
        }
        return new ArrayList(supervisorStorageService.getSupervisor(supervisorId).getProjects());
    }
}
