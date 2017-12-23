package com.juniormiqueletti.store.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.juniormiqueletti.store.dao.ProjectDAO;
import com.juniormiqueletti.store.domain.Project;

@Path("project")
public class ProjectRS {

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String find() {
		Project project = new ProjectDAO().find(1L);
		return project.toXML();
	}
}
