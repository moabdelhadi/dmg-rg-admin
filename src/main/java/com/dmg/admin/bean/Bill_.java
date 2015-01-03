package com.dmg.admin.bean;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-01-03T17:31:42.295+0400")
@StaticMetamodel(Bill.class)
public class Bill_ {
	public static volatile SingularAttribute<Bill, String> docNo;
	public static volatile SingularAttribute<Bill, String> docType;
	public static volatile SingularAttribute<Bill, String> yearCode;
	public static volatile SingularAttribute<Bill, String> serialNo;
	public static volatile SingularAttribute<Bill, String> partyName;
	public static volatile SingularAttribute<Bill, BigDecimal> prevBalance;
	public static volatile SingularAttribute<Bill, Date> lastReceivingDate;
	public static volatile SingularAttribute<Bill, BigDecimal> lastReceivingAmount;
	public static volatile SingularAttribute<Bill, String> city;
	public static volatile SingularAttribute<Bill, Date> billDate;
	public static volatile SingularAttribute<Bill, BigDecimal> service;
	public static volatile SingularAttribute<Bill, BigDecimal> gasDifference;
	public static volatile SingularAttribute<Bill, String> lastReceivedPayReference;
	public static volatile SingularAttribute<Bill, String> collectorName;
	public static volatile SingularAttribute<Bill, String> lastReading;
	public static volatile SingularAttribute<Bill, Date> lastReadingDate;
	public static volatile SingularAttribute<Bill, String> currentReading;
	public static volatile SingularAttribute<Bill, Date> currentReadingDate;
	public static volatile SingularAttribute<Bill, String> buildingCode;
	public static volatile SingularAttribute<Bill, String> buildingName;
	public static volatile SingularAttribute<Bill, String> apartmentCode;
	public static volatile SingularAttribute<Bill, String> totalUnit;
	public static volatile SingularAttribute<Bill, String> unitPrice;
	public static volatile SingularAttribute<Bill, BigDecimal> amount;
	public static volatile SingularAttribute<Bill, BigDecimal> totalAmount;
	public static volatile SingularAttribute<Bill, String> contractNo;
	public static volatile SingularAttribute<Bill, BigDecimal> receivedAmmount;
}
