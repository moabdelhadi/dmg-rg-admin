package com.dmg.admin.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.dmg.core.bean.AbstractPojo;

@Entity
@Table(name = "BILLS2")
public class Bill extends AbstractPojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 711201638919389292L;

	@Column(name = "COMPANY")
	private String company;

	@Column(name = "DOCNO")
	private String docNo;

	@Column(name = "DOCTYPE")
	private String docType;

	@Column(name = "YEARCODE")
	private String yearCode;

	@Column(name = "SRNO")
	private int srNo;

	@Column(name = "BUILDING_CODE")
	private String buildingCode;

	@Column(name = "BUILDING_NAME")
	private String buildingName;

	@Column(name = "APARTMENT_CODE")
	private String apartmentCode;

	@Column(name = "LAST_READING")
	private BigDecimal lastReading;

	@Column(name = "LAST_READING_DATE")
	private Date lastReadingDate;

	@Column(name = "CURR_READING")
	private BigDecimal currReading;

	@Column(name = "CURR_READING_DATE")
	private Date currReadingDate;

	@Column(name = "TOTAL_UNIT")
	private BigDecimal totalUnit;

	@Column(name = "UNIT_PRICE")
	private BigDecimal unitPrice;

	@Column(name = "AMT")
	private BigDecimal amt;

	@Column(name = "TOTALAMT")
	private BigDecimal totalAmt;

	@Column(name = "RECEIVED_AMT")
	private BigDecimal receivedAmt;

	@Column(name = "MONTHLY_FEE")
	private BigDecimal monthlyFee;

	@Column(name = "OTHER_AMT")
	private BigDecimal otherAmt;

	@Column(name = "READING_DOCNO")
	private String readingDocno;

	@Column(name = "READING_DOCTYPE")
	private String readingDoctype;

	@Column(name = "CONTRACT_NO")
	private String contractNo;

	@Column(name = "LAST_INVDOCNO")
	private String lastInvdocno;

	@Column(name = "LAST_INVDOCTYPE")
	private String lastInvdocType;

	@Column(name = "LAST_YEARCODE")
	private String lastYearCode;

	@Column(name = "LAST_INVDATE")
	private Date lastInvDate;

	@Column(name = "PARTYCODE")
	private String partyCode;

	@Column(name = "PARTYNAME")
	private String partyName;

	@Column(name = "PREV_BALANCE")
	private BigDecimal prevBalance;

	@Column(name = "USED_FOR_ANOTHER_INVOICE_YN")
	private String usedForAnotherInvoiceYn;

	@Column(name = "DBREADINGAMT_ACCODE")
	private String dbReadingAmtAcCode;

	@Column(name = "DBMAINTENANCE_ACCODE")
	private String dbMaintenanceAcCode;

	@Column(name = "DBMONTHLY_ACCODE")
	private String dbMonthlyAcCode;

	@Column(name = "DBCONNECTION_ACCODE")
	private String dbConnectionAcCode;

	@Column(name = "DBOTHERAMT_ACCODE")
	private String dbOtherAmtAcCode;

	@Column(name = "DBINSURANCEAMT_ACCODE")
	private String dbInsuranceAmtAcCode;

	@Column(name = "DBDEPOSITAMT_ACCODE")
	private String dbDepositAmtAcCode;

	@Column(name = "READING_SRNO")
	private int readingSrNo;

	@Column(name = "FINAL_INVOICE_YN")
	private String finalInvoiceYn;

	@Column(name = "LAST_REC_DOCNO")
	private String lastRecDocNo;

	@Column(name = "LAST_REC_DOCTYPE")
	private String lastRecDocType;

	@Column(name = "LAST_REC_DOCDATE")
	private Date lastRecDocDate;

	@Column(name = "LAST_REC_YEARCODE")
	private String lastRecYearCode;

	@Column(name = "LAST_REC_AMT")
	private BigDecimal lastRecAmt;

	@Column(name = "GAS_METER_NO")
	private String gasMeterNo;

	@Column(name = "DISC_AMT")
	private BigDecimal discAmt;

	@Column(name = "MAINTENANCE_AMT")
	private BigDecimal maintenanceAmt;

	public Bill() {

	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getYearCode() {
		return yearCode;
	}

	public void setYearCode(String yearCode) {
		this.yearCode = yearCode;
	}

	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}

	public String getBuildingCode() {
		return buildingCode;
	}

	public void setBuildingCode(String buildingCode) {
		this.buildingCode = buildingCode;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getApartmentCode() {
		return apartmentCode;
	}

	public void setApartmentCode(String apartmentCode) {
		this.apartmentCode = apartmentCode;
	}

	public BigDecimal getLastReading() {
		return lastReading;
	}

	public void setLastReading(BigDecimal lastReading) {
		this.lastReading = lastReading;
	}

	public Date getLastReadingDate() {
		return lastReadingDate;
	}

	public void setLastReadingDate(Date lastReadingDate) {
		this.lastReadingDate = lastReadingDate;
	}

	public BigDecimal getCurrReading() {
		return currReading;
	}

	public void setCurrReading(BigDecimal currReading) {
		this.currReading = currReading;
	}

	public Date getCurrReadingDate() {
		return currReadingDate;
	}

	public void setCurrReadingDate(Date currReadingDate) {
		this.currReadingDate = currReadingDate;
	}

	public BigDecimal getTotalUnit() {
		return totalUnit;
	}

	public void setTotalUnit(BigDecimal totalUnit) {
		this.totalUnit = totalUnit;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public BigDecimal getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	public BigDecimal getReceivedAmt() {
		return receivedAmt;
	}

	public void setReceivedAmt(BigDecimal receivedAmt) {
		this.receivedAmt = receivedAmt;
	}

	public BigDecimal getMonthlyFee() {
		return monthlyFee;
	}

	public void setMonthlyFee(BigDecimal monthlyFee) {
		this.monthlyFee = monthlyFee;
	}

	public BigDecimal getOtherAmt() {
		return otherAmt;
	}

	public void setOtherAmt(BigDecimal otherAmt) {
		this.otherAmt = otherAmt;
	}

	public String getReadingDocno() {
		return readingDocno;
	}

	public void setReadingDocno(String readingDocno) {
		this.readingDocno = readingDocno;
	}

	public String getReadingDoctype() {
		return readingDoctype;
	}

	public void setReadingDoctype(String readingDoctype) {
		this.readingDoctype = readingDoctype;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getLastInvdocno() {
		return lastInvdocno;
	}

	public void setLastInvdocno(String lastInvdocno) {
		this.lastInvdocno = lastInvdocno;
	}

	public String getLastInvdocType() {
		return lastInvdocType;
	}

	public void setLastInvdocType(String lastInvdocType) {
		this.lastInvdocType = lastInvdocType;
	}

	public String getLastYearCode() {
		return lastYearCode;
	}

	public void setLastYearCode(String lastYearCode) {
		this.lastYearCode = lastYearCode;
	}

	public Date getLastInvDate() {
		return lastInvDate;
	}

	public void setLastInvDate(Date lastInvDate) {
		this.lastInvDate = lastInvDate;
	}

	public String getPartyCode() {
		return partyCode;
	}

	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public BigDecimal getPrevBalance() {
		return prevBalance;
	}

	public void setPrevBalance(BigDecimal prevBalance) {
		this.prevBalance = prevBalance;
	}

	public String getUsedForAnotherInvoiceYn() {
		return usedForAnotherInvoiceYn;
	}

	public void setUsedForAnotherInvoiceYn(String usedForAnotherInvoiceYn) {
		this.usedForAnotherInvoiceYn = usedForAnotherInvoiceYn;
	}

	public String getDbReadingAmtAcCode() {
		return dbReadingAmtAcCode;
	}

	public void setDbReadingAmtAcCode(String dbReadingAmtAcCode) {
		this.dbReadingAmtAcCode = dbReadingAmtAcCode;
	}

	public String getDbMaintenanceAcCode() {
		return dbMaintenanceAcCode;
	}

	public void setDbMaintenanceAcCode(String dbMaintenanceAcCode) {
		this.dbMaintenanceAcCode = dbMaintenanceAcCode;
	}

	public String getDbMonthlyAcCode() {
		return dbMonthlyAcCode;
	}

	public void setDbMonthlyAcCode(String dbMonthlyAcCode) {
		this.dbMonthlyAcCode = dbMonthlyAcCode;
	}

	public String getDbConnectionAcCode() {
		return dbConnectionAcCode;
	}

	public void setDbConnectionAcCode(String dbConnectionAcCode) {
		this.dbConnectionAcCode = dbConnectionAcCode;
	}

	public String getDbOtherAmtAcCode() {
		return dbOtherAmtAcCode;
	}

	public void setDbOtherAmtAcCode(String dbOtherAmtAcCode) {
		this.dbOtherAmtAcCode = dbOtherAmtAcCode;
	}

	public String getDbInsuranceAmtAcCode() {
		return dbInsuranceAmtAcCode;
	}

	public void setDbInsuranceAmtAcCode(String dbInsuranceAmtAcCode) {
		this.dbInsuranceAmtAcCode = dbInsuranceAmtAcCode;
	}

	public String getDbDepositAmtAcCode() {
		return dbDepositAmtAcCode;
	}

	public void setDbDepositAmtAcCode(String dbDepositAmtAcCode) {
		this.dbDepositAmtAcCode = dbDepositAmtAcCode;
	}

	public int getReadingSrNo() {
		return readingSrNo;
	}

	public void setReadingSrNo(int readingSrNo) {
		this.readingSrNo = readingSrNo;
	}

	public String getFinalInvoiceYn() {
		return finalInvoiceYn;
	}

	public void setFinalInvoiceYn(String finalInvoiceYn) {
		this.finalInvoiceYn = finalInvoiceYn;
	}

	public String getLastRecDocNo() {
		return lastRecDocNo;
	}

	public void setLastRecDocNo(String lastRecDocNo) {
		this.lastRecDocNo = lastRecDocNo;
	}

	public String getLastRecDocType() {
		return lastRecDocType;
	}

	public void setLastRecDocType(String lastRecDocType) {
		this.lastRecDocType = lastRecDocType;
	}

	public Date getLastRecDocDate() {
		return lastRecDocDate;
	}

	public void setLastRecDocDate(Date lastRecDocDate) {
		this.lastRecDocDate = lastRecDocDate;
	}

	public String getLastRecYearCode() {
		return lastRecYearCode;
	}

	public void setLastRecYearCode(String lastRecYearCode) {
		this.lastRecYearCode = lastRecYearCode;
	}

	public BigDecimal getLastRecAmt() {
		return lastRecAmt;
	}

	public void setLastRecAmt(BigDecimal lastRecAmt) {
		this.lastRecAmt = lastRecAmt;
	}

	public String getGasMeterNo() {
		return gasMeterNo;
	}

	public void setGasMeterNo(String gasMeterNo) {
		this.gasMeterNo = gasMeterNo;
	}

	public BigDecimal getDiscAmt() {
		return discAmt;
	}

	public void setDiscAmt(BigDecimal discAmt) {
		this.discAmt = discAmt;
	}

	public BigDecimal getMaintenanceAmt() {
		return maintenanceAmt;
	}

	public void setMaintenanceAmt(BigDecimal maintenanceAmt) {
		this.maintenanceAmt = maintenanceAmt;
	}

}
