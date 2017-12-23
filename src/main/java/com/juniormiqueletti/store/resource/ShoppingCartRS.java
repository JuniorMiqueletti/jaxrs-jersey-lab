package com.juniormiqueletti.store.resource;

import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.juniormiqueletti.store.dao.ShoppingCartDAO;
import com.juniormiqueletti.store.domain.ShoppingCart;

@Path("shoppingcart")
public class ShoppingCartRS {

	ShoppingCartDAO dao = new ShoppingCartDAO();

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String findAll() {
		Map<Long, ShoppingCart> shoppingCart = dao.findAll();

		String allCarts = "";
		for (Entry<Long, ShoppingCart> cart : shoppingCart.entrySet()) {
			allCarts += cart.getValue().toXML();
		}
		return allCarts;
	}

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String find(@PathParam("id") Long id) {
		ShoppingCart shoppingCart = dao.find(id);
		return shoppingCart.toXML();
	}
}
