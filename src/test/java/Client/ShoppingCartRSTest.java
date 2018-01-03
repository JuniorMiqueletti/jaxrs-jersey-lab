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

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.juniormiqueletti.store.app.Server;
import com.juniormiqueletti.store.domain.Product;
import com.juniormiqueletti.store.domain.ShoppingCart;

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
	public void findAllTest() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HTTP_LOCALHOST_8080);

		List<ShoppingCart> carts = target.path("shoppingcart").request().get(new GenericType<List<ShoppingCart>>() {});
		
		assertTrue(carts.size() > 0);
		assertTrue("Homby St".equals(carts.get(0).getStreet()));
	}

	@Test
	public void findOneTest() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HTTP_LOCALHOST_8080);

		ShoppingCart shoppingCart = target.path("shoppingcart/1").request().get(ShoppingCart.class);

		assertTrue("Homby St".equals(shoppingCart.getStreet()));
	}

	@Test
	public void addCartTest() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HTTP_LOCALHOST_8080);

		ShoppingCart cart = new ShoppingCart();
		cart.add(new Product(314, "Laptop", 2000, 1));
		cart.setStreet("St Queen").setCity("Springfield");

		Entity<ShoppingCart> entity = Entity.entity(cart, MediaType.APPLICATION_XML);

		Response response = target.path("shoppingcart").request().post(entity);
		assertEquals(201, response.getStatus());
	}

	@Test
	public void deleteAProduct1FromShoppingCart1Test() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HTTP_LOCALHOST_8080);
		
		Response response = target.path("shoppingcart/1/products/1").request().delete();
		assertEquals(200, response.getStatus());
	}

	@Test
	public void changeQuantityFromProdutoctFromShoppingCart1Test() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(HTTP_LOCALHOST_8080);
		
		Product product = new Product(3467, "Sport Game", 22, 2333);
		
		Entity<Product> entity = Entity.entity(product, MediaType.APPLICATION_XML);
		
		Response response = target.path("shoppingcart/1/products/1/quantity").request().put(entity);
		assertEquals(200, response.getStatus());
	}
}
