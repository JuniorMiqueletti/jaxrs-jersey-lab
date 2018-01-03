package com.juniormiqueletti.store.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.juniormiqueletti.store.dao.ShoppingCartDAO;
import com.juniormiqueletti.store.domain.Product;
import com.juniormiqueletti.store.domain.ShoppingCart;

@Path("shoppingcart")
public class ShoppingCartRS {

	ShoppingCartDAO dao = new ShoppingCartDAO();

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response findAll() {
		Map<Long, ShoppingCart> carts = dao.findAll();

		List<ShoppingCart> cartsList = new ArrayList<ShoppingCart>(); 
		for (Entry<Long, ShoppingCart> cart : carts.entrySet()) {
			cartsList.add(cart.getValue());
		}
		GenericEntity<List<ShoppingCart>> entity = new GenericEntity<List<ShoppingCart>>(cartsList) {};

		return Response.ok(entity).build();
	}

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public ShoppingCart find(@PathParam("id") Long id) {
		ShoppingCart shoppingCart = dao.find(id);
		return shoppingCart;
	}

	@POST
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public Response add(ShoppingCart cart) {
		dao.add(cart);

		URI uri = URI.create("/shoppingcart/" + cart.getId());
		return Response.created(uri).build();
	}

	@Path("{id}/products/{productId}")
	@DELETE
	public Response deleteProduct(@PathParam("id") long id, @PathParam("productId") long productId) {
		ShoppingCart cart = dao.find(id);
		cart.remove(productId);
		return Response.ok().build();
	}

	@Path("{id}/products/{productId}/quantity")
	@PUT
	public Response changeProduct(@PathParam("id") long id, @PathParam("productId") long productId, Product product) {
		ShoppingCart cart = dao.find(id);

		cart.changeQuantity(product);

		return Response.ok().build();
	}
}
