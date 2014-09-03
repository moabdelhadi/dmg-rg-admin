package com.dmg.admin.rest.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BillJson implements Serializable {

	/**
	 * @author Kareem Jabr
	 */
	private static final long serialVersionUID = 9156146763289036470L;

	private String errorMsg;

	private String docNo;

	private String docType;

	private String yearCode;

	private String serialNo;

	private String partyName;

	private String prevBalance;

	private String lastReceivingDate;

	private String LastReceivingAmount;

	private String city;

	private String billDate;

	private String service;

	private String gasDifference;

	private String lastReceivedPayReference;

	private String collectorName;

	private String lastReading;

	private String lastReadingDate;

	private String currentReading;

	private String currentReadingDate;

	private String buildingCode;

	private String buildingName;

	private String apartmentCode;

	private String totalUnit;

	private String unitPrice;

	private String amount;

	private String totalAmount;

	private String contractNo;

	private String receivedAmmount;

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
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

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
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

	public String getLastReceivingDate() {
		return lastReceivingDate;
	}

	public void setLastReceivingDate(String lastReceivingDate) {
		this.lastReceivingDate = lastReceivingDate;
	}

	public String getLastReceivingAmount() {
		return LastReceivingAmount;
	}

	public void setLastReceivingAmount(String lastReceivingAmount) {
		LastReceivingAmount = lastReceivingAmount;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getGasDifference() {
		return gasDifference;
	}

	public void setGasDifference(String gasDifference) {
		this.gasDifference = gasDifference;
	}

	public String getLastReceivedPayReference() {
		return lastReceivedPayReference;
	}

	public void setLastReceivedPayReference(String lastReceivedPayReference) {
		this.lastReceivedPayReference = lastReceivedPayReference;
	}

	public String getCollectorName() {
		return collectorName;
	}

	public void setCollectorName(String collectorName) {
		this.collectorName = collectorName;
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

	public String getCurrentReading() {
		return currentReading;
	}

	public void setCurrentReading(String currentReading) {
		this.currentReading = currentReading;
	}

	public String getCurrentReadingDate() {
		return currentReadingDate;
	}

	public void setCurrentReadingDate(String currentReadingDate) {
		this.currentReadingDate = currentReadingDate;
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getReceivedAmmount() {
		return receivedAmmount;
	}

	public void setReceivedAmmount(String receivedAmmount) {
		this.receivedAmmount = receivedAmmount;
	}

}
