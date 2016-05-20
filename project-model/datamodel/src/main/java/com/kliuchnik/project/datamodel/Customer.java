package com.kliuchnik.project.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Customer extends AbstractModel implements Serializable {

	
	@MapsId
	@OneToOne(fetch = FetchType.LAZY, optional = false, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(nullable = false, updatable = false, name = "id")
	private User user;

	
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	private List<Order> orders= new ArrayList<Order>();

	@Column
	private String address;
	@Column
	private String bankR;

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(Order order) {
		this.orders.add(order);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBankR() {
		return bankR;
	}

	public void setBankR(String bankR) {
		this.bankR = bankR;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Customer [user=" + user + ", orders=" + orders + ", address=" + address + ", bankR=" + bankR + "]";
	}

}
