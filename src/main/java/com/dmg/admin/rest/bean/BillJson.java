package com.dmg.admin.rest.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BillJson implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9156146763289036470L;

	private String errorMsg;

	private String company;

	private String docNo;

	private String docType;

	private String yearCode;

	private String srNo;

	private String buildingCode;

	private String buildingName;

	private String apartmentCode;

	private String lastReading;

	private String lastReadingDate;

	private String currReading;

	private String currReadingDate;

	private String totalUnit;

	private String unitPrice;

	private String amt;

	private String totalAmt;

	private String receivedAmt;

	private String monthlyFee;

	private String otherAmt;

	private String readingDocno;

	private String readingDoctype;

	private String contractNo;

	private String lastInvdocno;

	private String lastInvdocType;

	private String lastYearCode;

	private String lastInvDate;

	private String partyCode;

	private String partyName;

	private String prevBalance;

	private String usedForAnotherInvoiceYn;

	private String dbReadingAmtAcCode;

	private String dbMaintenanceAcCode;

	private String dbMonthlyAcCode;

	private String dbConnectionAcCode;

	private String dbOtherAmtAcCode;

	private String dbInsuranceAmtAcCode;

	private String dbDepositAmtAcCode;

	private String readingSrNo;

	private String finalInvoiceYn;

	private String lastRecDocNo;

	private String lastRecDocType;

	private String lastRecDocDate;

	private String lastRecYearCode;

	private String lastRecAmt;

	private String gasMeterNo;

	private String discAmt;

	private String maintenanceAmt;

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

	public String getSrNo() {
		return srNo;
	}

	public void setSrNo(String srNo) {
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

	public String getLastReading() {
		return lastReading;
	}

	public void setLastReading(String lastReading) {
		this.lastReading = lastReading;
	}

	public String getLastReadingDate() {
		return lastReadingDate;
	}

	public void setLastReadingDate(String lastReadingDate) {
		this.lastReadingDate = lastReadingDate;
	}

	public String getCurrReading() {
		return currReading;
	}

	public void setCurrReading(String currReading) {
		this.currReading = currReading;
	}

	public String getCurrReadingDate() {
		return currReadingDate;
	}

	public void setCurrReadingDate(String currReadingDate) {
		this.currReadingDate = currReadingDate;
	}

	public String getTotalUnit() {
		return totalUnit;
	}

	public void setTotalUnit(String totalUnit) {
		this.totalUnit = totalUnit;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getReceivedAmt() {
		return receivedAmt;
	}

	public void setReceivedAmt(String receivedAmt) {
		this.receivedAmt = receivedAmt;
	}

	public String getMonthlyFee() {
		return monthlyFee;
	}

	public void setMonthlyFee(String monthlyFee) {
		this.monthlyFee = monthlyFee;
	}

	public String getOtherAmt() {
		return otherAmt;
	}

	public void setOtherAmt(String otherAmt) {
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

	public String getLastInvDate() {
		return lastInvDate;
	}

	public void setLastInvDate(String lastInvDate) {
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

	public String getPrevBalance() {
		return prevBalance;
	}

	public void setPrevBalance(String prevBalance) {
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

	public String getReadingSrNo() {
		return readingSrNo;
	}

	public void setReadingSrNo(String readingSrNo) {
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

	public String getLastRecDocDate() {
		return lastRecDocDate;
	}

	public void setLastRecDocDate(String lastRecDocDate) {
		this.lastRecDocDate = lastRecDocDate;
	}

	public String getLastRecYearCode() {
		return lastRecYearCode;
	}

	public void setLastRecYearCode(String lastRecYearCode) {
		this.lastRecYearCode = lastRecYearCode;
	}

	public String getLastRecAmt() {
		return lastRecAmt;
	}

	public void setLastRecAmt(String lastRecAmt) {
		this.lastRecAmt = lastRecAmt;
	}

	public String getGasMeterNo() {
		return gasMeterNo;
	}

	public void setGasMeterNo(String gasMeterNo) {
		this.gasMeterNo = gasMeterNo;
	}

	public String getDiscAmt() {
		return discAmt;
	}

	public void setDiscAmt(String discAmt) {
		this.discAmt = discAmt;
	}

	public String getMaintenanceAmt() {
		return maintenanceAmt;
	}

	public void setMaintenanceAmt(String maintenanceAmt) {
		this.maintenanceAmt = maintenanceAmt;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
