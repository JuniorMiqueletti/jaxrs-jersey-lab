package Client;

import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.juniormiqueletti.store.app.Server;
import com.juniormiqueletti.store.domain.Project;
import com.thoughtworks.xstream.XStream;

public class ProjectRSTest {

	private static final String HTTP_LOCALHOST_8080 = "http://localhost:8080";
	private static Server server;

	@BeforeClass
	public static void setUp() {
		server = new Server();
		server.start();
	}

	@AfterClass
	public static void flush() {
		server.shutdown();
	}

	@Test
	public void getNameTest() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HTTP_LOCALHOST_8080);
		String content = target.path("project").request().get(String.class);

		Project project = (Project) new XStream().fromXML(content);

		assertTrue("My Store".equals(project.getName()));
	}
	
	@Test
	public void getProject1Test() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HTTP_LOCALHOST_8080);
		String content = target.path("project/1").request().get(String.class);
		
		Project project = (Project) new XStream().fromXML(content);
		
		assertTrue("My Store".equals(project.getName()));
	}
	
	@Test
	public void postProjectTest() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HTTP_LOCALHOST_8080);
		
		Project project = new Project(1l, "Old Software", 2017);
		
		String xml = project.toXML();
		
		Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);
		
		Response response = target.path("project").request().post(entity);
		
		assertTrue("<status>sucess</status>".equals(response.readEntity(String.class)));
	}

}
