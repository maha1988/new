package com.kliuchnik.project.datamodel;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Order.class)
public abstract class Order_ extends com.kliuchnik.project.datamodel.AbstractModel_ {

	public static volatile SingularAttribute<Order, Date> date;
	public static volatile SingularAttribute<Order, BigDecimal> sum;
	public static volatile ListAttribute<Order, Product> products;
	public static volatile SingularAttribute<Order, Customer> customer;

}

