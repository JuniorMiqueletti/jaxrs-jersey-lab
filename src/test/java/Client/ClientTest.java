package Client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class ClientTest {
	
	@Test
	public void basicConectionGetTest() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://www.mocky.io");
		String content = target.path("v2/5a3e52c92f0000281017137c")
							.request()
							.get(String.class);
		
		assertTrue(content.contains("<street>Homby St"));
	}

}
