package com.kliuchnik.project.datamodel;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ extends com.kliuchnik.project.datamodel.AbstractModel_ {

	public static volatile SingularAttribute<Product, Sklad> sklad;
	public static volatile SingularAttribute<Product, Unit> unit;
	public static volatile SingularAttribute<Product, BigDecimal> price;
	public static volatile SingularAttribute<Product, Long> currentQuantity;
	public static volatile SingularAttribute<Product, String> productName;

}

