package com.dmg.admin.bean;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-01-03T17:31:42.749+0400")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, String> username;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> fullName;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> role;
	public static volatile SingularAttribute<User, Integer> failedLoginAttempts;
	public static volatile SingularAttribute<User, Boolean> accountLocked;
	public static volatile SingularAttribute<User, String> reasonForLockedAccount;
}
