package Client;

import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.juniormiqueletti.store.app.Server;
import com.juniormiqueletti.store.domain.ShoppingCart;
import com.thoughtworks.xstream.XStream;

public class ShoppingCartRSTest {

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
	public void getStreetTest() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		String content = target.path("shoppingcart").request().get(String.class);

		ShoppingCart shoppingCart = (ShoppingCart) new XStream().fromXML(content);

		assertTrue("Homby St".equals(shoppingCart.getStreet()));

	}

}
