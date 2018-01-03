package com.juniormiqueletti.store.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.juniormiqueletti.store.domain.Product;
import com.juniormiqueletti.store.domain.ShoppingCart;

public class ShoppingCartDAO {
	
	private static Map<Long, ShoppingCart> db = new HashMap<Long, ShoppingCart>();
	private static AtomicLong count = new AtomicLong(1);
	
	static {
		Product game = new Product(6237, "Game 4", 4000, 1);
		Product sportGame = new Product(3467, "Sport Game", 60, 2);
		ShoppingCart ShoppingCart = new ShoppingCart()
								.add(game)
								.add(sportGame)
								.to("Homby St", "Vancouver")
								.setId(1l);
		
		db.put(1l, ShoppingCart);

		Product game2 = new Product(6237, "Game 7", 7777, 2);
		Product sportGame2 = new Product(3467, "Sport Game Two", 160, 2);
		ShoppingCart ShoppingCart2 = new ShoppingCart()
				.add(game2)
				.add(sportGame2)
				.to("Homby St", "Vancouver")
				.setId(2l);
		
		db.put(2l, ShoppingCart2);
	}
	
	public void add(ShoppingCart ShoppingCart) {
		long id = count.incrementAndGet();
		ShoppingCart.setId(id);
		db.put(id, ShoppingCart);
	}
	
	public ShoppingCart find(Long id) {
		return db.get(id);
	}
	public Map<Long, ShoppingCart> findAll() {
		return db;
	}
	
	public ShoppingCart remove(long id) {
		return db.remove(id);
	}

}
