package com.kliuchnik.project.datamodel;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Customer.class)
public abstract class Customer_ extends com.kliuchnik.project.datamodel.AbstractModel_ {

	public static volatile SingularAttribute<Customer, String> address;
	public static volatile SingularAttribute<Customer, String> bankR;
	public static volatile ListAttribute<Customer, Order> orders;
	public static volatile SingularAttribute<Customer, User> user;

}

