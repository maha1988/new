package com.kliuchnik.project.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Sklad extends AbstractModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	private String name;
	
	@OneToMany(mappedBy = "sklad", fetch = FetchType.EAGER)
	private List<Product> products= new ArrayList<>();
	


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(Product products) {
		this.products.add(products);
	}


	@Override
	public String toString() {
		return "Sklad [name=" + name + ", products=" + products + "]";
	}
	

}
