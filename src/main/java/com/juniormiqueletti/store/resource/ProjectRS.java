package com.juniormiqueletti.store.resource;

import java.net.URI;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.juniormiqueletti.store.dao.ProjectDAO;
import com.juniormiqueletti.store.domain.Project;
import com.thoughtworks.xstream.XStream;

@Path("project")
public class ProjectRS {
	
	private ProjectDAO dao = new ProjectDAO();

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String findAll() {
		Map<Long, Project> projects = dao.findAll();
		String allCarts = "";
		for (Entry<Long, Project> project : projects.entrySet()) {
			allCarts += project.getValue().toXML();
		}
		return allCarts;
	}

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String find(@PathParam("id") Long id) {
		Project project = dao.find(id);
		return project.toXML();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
    public Response add(String content) {
		Project project = (Project) new XStream().fromXML(content);
		dao.add(project);
		
		URI uri = URI.create("/project/" + project.getId());
	    return Response.created(uri).build();
    }
	
	@Path("{id}")
	@DELETE
	public Response deleteProduct(@PathParam("id") long id) {
		dao.delete(id);
		return Response.ok().build();
	}
}
