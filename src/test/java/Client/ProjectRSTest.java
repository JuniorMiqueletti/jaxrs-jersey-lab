package Client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.juniormiqueletti.store.app.Server;
import com.juniormiqueletti.store.domain.Project;

public class ProjectRSTest {

	private static final String HTTP_LOCALHOST_8080 = "http://localhost:8080";
	private static Server server;
	private static Client client;

	@BeforeClass
	public static void setUp() {
		server = new Server();
		server.start();
	}
	
	@Before
	public void setUpMethod() {
		client = ClientBuilder.newClient();
	}

	@AfterClass
	public static void flush() {
		server.shutdown();
	}
	
	@After
	public void tearDown() {
		client.close();
	}

	@Test
	public void findAllProjectsTest() {
		WebTarget target = client.target(HTTP_LOCALHOST_8080);

		List<Project> projects = target.path("project").request().get(new GenericType<List<Project>>() {});
		
		assertTrue(projects.size() > 0);
		assertTrue("My Store".equals(projects.get(0).getName()));
	}
	
	@Test
	public void findOneProjectTest() {
		WebTarget target = client.target(HTTP_LOCALHOST_8080);
		Project project = target.path("project/1").request().get(Project.class);
		
		assertTrue("My Store".equals(project.getName()));
	}
	
	@Test
	public void addProjectTest() {
		WebTarget target = client.target(HTTP_LOCALHOST_8080);
		
		Project project = new Project(1l, "Old Software", 2017);
		
		Entity<Project> entity = Entity.entity(project, MediaType.APPLICATION_XML);
		
		Response response = target.path("project").request().post(entity);
		assertEquals(201, response.getStatus());
	}
}
