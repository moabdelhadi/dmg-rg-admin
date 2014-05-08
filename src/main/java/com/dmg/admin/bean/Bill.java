package com.dmg.admin.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.dmg.core.bean.AbstractPojo;

@Entity
@Table(name = "BILLS")
public class Bill extends AbstractPojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 711201638919389292L;

	@Column(name = "ACCOUNT_NUMBER")
	private String accountNumber;

	@Column(name = "PAYED")
	private boolean payed;

	@Column(name = "FROM_DATE")
	private Date fromDate;

	@Column(name = "TO_DATE")
	private Date toDate;

	@Column(name = "AMOUNT")
	private int amount;

	@Column(name = "BILL_NUMBER")
	private int billNumber;

	public Bill() {

	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public boolean isPayed() {
		return payed;
	}

	public void setPayed(boolean payed) {
		this.payed = payed;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(int billNumber) {
		this.billNumber = billNumber;
	}

}