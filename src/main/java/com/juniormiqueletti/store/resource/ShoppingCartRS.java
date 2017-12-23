package com.juniormiqueletti.store.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.juniormiqueletti.store.dao.ShoppingCartDAO;
import com.juniormiqueletti.store.domain.ShoppingCart;

@Path("shoppingcart")
public class ShoppingCartRS {

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String find() {
		ShoppingCart shoppingCart = new ShoppingCartDAO().find(1L);
		return shoppingCart.toXML();
	}
}
