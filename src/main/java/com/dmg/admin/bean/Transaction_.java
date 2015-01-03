package com.dmg.admin.bean;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-01-03T17:31:42.744+0400")
@StaticMetamodel(Transaction.class)
public class Transaction_ {
	public static volatile SingularAttribute<Transaction, String> accountNumber;
	public static volatile SingularAttribute<Transaction, Date> paymentDate;
	public static volatile SingularAttribute<Transaction, String> status;
	public static volatile SingularAttribute<Transaction, String> billNumber;
	public static volatile SingularAttribute<Transaction, BigDecimal> amount;
}
