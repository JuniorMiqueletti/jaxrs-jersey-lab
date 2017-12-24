package Client;

import static org.junit.Assert.assertEquals;
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
import com.juniormiqueletti.store.domain.Product;
import com.juniormiqueletti.store.domain.ShoppingCart;
import com.thoughtworks.xstream.XStream;

public class ShoppingCartRSTest {

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
	public void getStreetTest() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HTTP_LOCALHOST_8080);
		String content = target.path("shoppingcart").request().get(String.class);

		ShoppingCart shoppingCart = (ShoppingCart) new XStream().fromXML(content);

		assertTrue("Homby St".equals(shoppingCart.getStreet()));

	}
	
	@Test
	public void getShoppingCart1Test() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HTTP_LOCALHOST_8080);
		String content = target.path("shoppingcart/1").request().get(String.class);
		
		ShoppingCart shoppingCart = (ShoppingCart) new XStream().fromXML(content);
		
		assertTrue("Homby St".equals(shoppingCart.getStreet()));
	}

	@Test
	public void postShoppingCart2Test() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HTTP_LOCALHOST_8080);
		
		ShoppingCart cart = new ShoppingCart();
		cart.add(new Product(314, "Laptop", 2000, 1));
		cart.setStreet("St Queen")
			.setCity("Springfield");
		
		String xml = cart.toXML();
		
		Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);
		
		Response response = target.path("shoppingcart").request().post(entity);
		assertEquals(201, response.getStatus());
	}
}
