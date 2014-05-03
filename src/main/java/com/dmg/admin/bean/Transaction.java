package com.dmg.admin.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.dmg.core.bean.AbstractPojo;

@Entity
@Table(name = "TRANSACTIONS")
public class Transaction extends AbstractPojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 711201638919389292L;

	@Column(name = "ACCOUNT_NUMBER")
	private String accountNumber;

	@Column(name = "PAYMENT_DATE")
	private Date paymentDate;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "BILL_NUMBER")
	private int billNumber;

	public Transaction() {

	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(int billNumber) {
		this.billNumber = billNumber;
	}

}
