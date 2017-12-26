package com.juniormiqueletti.store.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

public class ShoppingCart {

	private Long id;
	private String street;
	private String city;
	private List<Product> products = new ArrayList<Product>();

	public Long getId() {
		return id;
	}

	public ShoppingCart setId(Long id) {
		this.id = id;
		return this;
	}

	public String getStreet() {
		return street;
	}

	public ShoppingCart setStreet(String street) {
		this.street = street;
		return this;
	}

	public String getCity() {
		return city;
	}

	public ShoppingCart setCity(String city) {
		this.city = city;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingCart other = (ShoppingCart) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}

	public ShoppingCart add(Product product) {
		this.products.add(product);
		return this;
	}

	public ShoppingCart to(String string, String string2) {
		this.street = string;
		this.city = string2;
		return this;
	}

	public void remove(long productId) {
		for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
			Product product = iterator.next();
			if (product.getId() == productId) {
				iterator.remove();
			}
		}
	}

	public String toXML() {
		return new XStream().toXML(this);
	}

	public String toJson() {
		return new Gson().toJson(this);
	}

	public void change(Product product) {
		remove(product.getId());
		add(product);
	}

	public void changeQuantity(Product product) {
		for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
			Product p = iterator.next();
			if (p.getId() == product.getId()) {
				p.setQuantity(product.getQuantity());
				return;
			}
		}
	}
}
