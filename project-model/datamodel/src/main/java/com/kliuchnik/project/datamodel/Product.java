package com.kliuchnik.project.datamodel;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Product extends AbstractModel  implements Serializable{
	private static final long serialVersionUID = 1L;

	@Column (name= "product_name")
	private String productName;
	
	@Column
	@Enumerated(value = EnumType.ORDINAL)
	private Unit unit;
	
	@Column (name= "current_quantity")
	private Long currentQuantity;
	
	@Column
	private BigDecimal price;
	
	@ManyToOne(targetEntity = Sklad.class, fetch = FetchType.LAZY)
	private Sklad sklad;	

	public Sklad getSklad() {
		return sklad;
	}

	public void setSklad(Sklad sklad) {
		this.sklad = sklad;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Long getCurrentQuantity() {
		return currentQuantity;
	}

	public void setCurrentQuantity(Long currentQuantity) {
		this.currentQuantity = currentQuantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [productName=" + productName + ", unit=" + unit + ", currentQuantity=" + currentQuantity
				+ ", price=" + price + ", sklad=" + sklad + "]";
	}
	
}
