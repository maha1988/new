package com.kliuchnik.project.datamodel;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ extends com.kliuchnik.project.datamodel.AbstractModel_ {

	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, Role> role;
	public static volatile SingularAttribute<User, String> name;

}

