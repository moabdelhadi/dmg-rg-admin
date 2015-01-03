package com.dmg.admin.bean;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-01-03T17:31:42.753+0400")
@StaticMetamodel(UserAccount.class)
public class UserAccount_ {
	public static volatile SingularAttribute<UserAccount, String> email;
	public static volatile SingularAttribute<UserAccount, String> password;
	public static volatile SingularAttribute<UserAccount, String> name;
	public static volatile SingularAttribute<UserAccount, String> city;
	public static volatile SingularAttribute<UserAccount, String> buildingNumber;
	public static volatile SingularAttribute<UserAccount, String> appartmentNumber;
	public static volatile SingularAttribute<UserAccount, String> contractNo;
	public static volatile SingularAttribute<UserAccount, String> phone;
	public static volatile SingularAttribute<UserAccount, String> mobile;
	public static volatile SingularAttribute<UserAccount, String> pobox;
	public static volatile SingularAttribute<UserAccount, String> poboxCity;
	public static volatile SingularAttribute<UserAccount, Integer> status;
	public static volatile SingularAttribute<UserAccount, String> activationString;
	public static volatile SingularAttribute<UserAccount, Date> lastUpdate;
	public static volatile SingularAttribute<UserAccount, Boolean> enable;
	public static volatile SingularAttribute<UserAccount, Integer> syncStatus;
}
