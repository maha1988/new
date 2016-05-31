package com.kliuchnik.project.datamodel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order extends AbstractModel implements Serializable {

	@Column
	private Date date;

	@Column
	private BigDecimal sum;

	@JoinTable(name = "order_2_product", joinColumns = { @JoinColumn(name = "order_id") }, inverseJoinColumns = {
	@JoinColumn(name = "product_id") })
	@ManyToMany(targetEntity = Product.class, fetch = FetchType.LAZY)
	private List<Product> products = new ArrayList<>();

	@ManyToOne(targetEntity = Customer.class, fetch = FetchType.EAGER)
	private Customer customer;
	

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(Product products) {
		this.products.add(products);
		
	}


	

	@Override
	public String toString() {
		return "Order [ date=" + date + ", sum=" + sum + ", products=" + products + ", customer=" + customer + "]";
	}
}
