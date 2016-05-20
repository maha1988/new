package com.kliuchnik.project.datamodel;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Sklad.class)
public abstract class Sklad_ extends com.kliuchnik.project.datamodel.AbstractModel_ {

	public static volatile SingularAttribute<Sklad, String> name;
	public static volatile ListAttribute<Sklad, Product> products;

}

