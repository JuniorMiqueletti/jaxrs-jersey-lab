package Client;

import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.juniormiqueletti.store.app.Server;
import com.juniormiqueletti.store.domain.Project;
import com.thoughtworks.xstream.XStream;

public class ProjectRSTest {

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
		WebTarget target = client.target("http://localhost:8080");
		String content = target.path("project").request().get(String.class);

		Project project = (Project) new XStream().fromXML(content);

		assertTrue("My Store".equals(project.getName()));
	}

}
