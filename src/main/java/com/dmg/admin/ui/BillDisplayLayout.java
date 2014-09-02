package com.dmg.admin.ui;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.dmg.admin.bean.Bill;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class BillDisplayLayout extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 701948131350786451L;
	private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public BillDisplayLayout(Bill item) {
		setSpacing(true);
		setMargin(true);
		setWidth("100%");

		// setting default values for null
		VerticalLayout verticalLayout1 = new VerticalLayout();
		VerticalLayout verticalLayout2 = new VerticalLayout();
		VerticalLayout verticalLayout3 = new VerticalLayout();

		verticalLayout1.addComponent(new DataRowLayout("Doc No.:", StringUtils.trimToEmpty(item.getDocNo())));
		verticalLayout1.addComponent(new DataRowLayout("Doc Type:", StringUtils.trimToEmpty(item.getDocType())));
		verticalLayout1.addComponent(new DataRowLayout("Year Code:", StringUtils.trimToEmpty(item.getYearCode())));
		verticalLayout1.addComponent(new DataRowLayout("Serial No.:", trimString(String.valueOf(item.getSerialNo()))));
		verticalLayout1.addComponent(new DataRowLayout("Party Name:", StringUtils.trimToEmpty(item.getPartyName())));
		verticalLayout1.addComponent(new DataRowLayout("Previous Balance:", trimString(String.valueOf(item.getPrevBalance()))));
		verticalLayout1.addComponent(new DataRowLayout("Last Received Date:", getFormattedDate(item.getLastReceivingDate())));
		verticalLayout1.addComponent(new DataRowLayout("Last Received Amount:", trimString(String.valueOf(item.getLastReceivingAmount()))));
		verticalLayout1.addComponent(new DataRowLayout("City:", StringUtils.trimToEmpty(item.getCity())));

		verticalLayout2.addComponent(new DataRowLayout("Last Reading Date:", getFormattedDate(item.getBillDate())));
		verticalLayout2.addComponent(new DataRowLayout("Service:", trimString(String.valueOf(item.getService()))));
		verticalLayout2.addComponent(new DataRowLayout("Gas Difference:", trimString(String.valueOf(item.getGasDifference()))));
		verticalLayout2.addComponent(new DataRowLayout("Last Received Pay Reference:", StringUtils.trimToEmpty(item.getLastReceivedPayReference())));
		verticalLayout2.addComponent(new DataRowLayout("Collector Name:", StringUtils.trimToEmpty(item.getCollectorName())));
		verticalLayout2.addComponent(new DataRowLayout("Last Reading:", StringUtils.trimToEmpty(item.getLastReading())));
		verticalLayout2.addComponent(new DataRowLayout("Last Reading Date:", getFormattedDate(item.getLastReadingDate())));
		verticalLayout2.addComponent(new DataRowLayout("Current Reading:", StringUtils.trimToEmpty(item.getCurrentReading())));
		verticalLayout2.addComponent(new DataRowLayout("Current Reading Date:", getFormattedDate(item.getCurrentReadingDate())));

		verticalLayout3.addComponent(new DataRowLayout("Building Code:", StringUtils.trimToEmpty(item.getBuildingCode())));
		verticalLayout3.addComponent(new DataRowLayout("Building Name:", StringUtils.trimToEmpty(item.getBuildingName())));
		verticalLayout3.addComponent(new DataRowLayout("Apartment Code:", StringUtils.trimToEmpty(item.getApartmentCode())));
		verticalLayout3.addComponent(new DataRowLayout("Total Unit:", StringUtils.trimToEmpty(item.getTotalUnit())));
		verticalLayout3.addComponent(new DataRowLayout("Unit Price:", StringUtils.trimToEmpty(item.getUnitPrice())));
		verticalLayout3.addComponent(new DataRowLayout("Amount:", trimString(String.valueOf(item.getAmount()))));
		verticalLayout3.addComponent(new DataRowLayout("Total Amount:", trimString(String.valueOf(item.getTotalAmount()))));
		verticalLayout3.addComponent(new DataRowLayout("Contract No.:", StringUtils.trimToEmpty(item.getContractNo())));
		verticalLayout3.addComponent(new DataRowLayout("Received Amount:", trimString(String.valueOf(item.getReceivedAmmount()))));

		addComponent(verticalLayout1);
		addComponent(verticalLayout2);
		addComponent(verticalLayout3);

		/*
		 * horizontalLayout.setExpandRatio(verticalLayout1, 0.45F);
		 * horizontalLayout.setExpandRatio(verticalLayout2, 0.45F);
		 * horizontalLayout.setExpandRatio(verticalLayout3, 0.45F);
		 */

		// TODO Auto-generated constructor stub

	}

	private String getFormattedDate(Date date) {
		if (date != null) {
			return sdf.format(date);
		}
		return "";

	}

	private String trimString(String str) {
		String trimmed = StringUtils.trimToEmpty(str);
		if (trimmed.equals("null")) {
			return "";
		}
		return trimmed;

	}
}
