package com.dmg.admin.ui;

import java.text.SimpleDateFormat;

import com.dmg.admin.bean.Bill;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class BillDisplayLayout extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 701948131350786451L;

	public BillDisplayLayout(Bill item) {

		setMargin(true);
		setWidth("100%");

		// setting default values for null
		VerticalLayout verticalLayout1 = new VerticalLayout();
		VerticalLayout verticalLayout2 = new VerticalLayout();
		VerticalLayout verticalLayout3 = new VerticalLayout();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		verticalLayout1.addComponent(new DataRowLayout("Contract #:", item.getContractNo()));
		verticalLayout1.addComponent(new DataRowLayout("Company:", item.getCompany()));
		verticalLayout1.addComponent(new DataRowLayout("Doc #:", item.getDocNo()));
		verticalLayout1.addComponent(new DataRowLayout("Doc Type:", item.getDocType()));
		verticalLayout1.addComponent(new DataRowLayout("Year Code:", item.getYearCode()));
		verticalLayout1.addComponent(new DataRowLayout("Sr. #:", String.valueOf(item.getSrNo())));
		verticalLayout1.addComponent(new DataRowLayout("Building Code:", item.getBuildingCode()));
		verticalLayout1.addComponent(new DataRowLayout("Building Name:", item.getBuildingName()));

		verticalLayout2.addComponent(new DataRowLayout("Apartment Code:", item.getApartmentCode()));
		verticalLayout2.addComponent(new DataRowLayout("Current Reading:", String.valueOf(item.getCurrReading())));
		verticalLayout2.addComponent(new DataRowLayout("Current Reading Date:", sdf.format(item.getCurrReadingDate())));
		verticalLayout2.addComponent(new DataRowLayout("Last Reading:", String.valueOf(item.getLastReading())));
		verticalLayout2.addComponent(new DataRowLayout("Last Reading Date:", sdf.format(item.getLastReadingDate())));
		verticalLayout2.addComponent(new DataRowLayout("Total Unit:", String.valueOf(item.getTotalUnit())));
		verticalLayout2.addComponent(new DataRowLayout("Unit Price:", String.valueOf(item.getUnitPrice())));

		verticalLayout3.addComponent(new DataRowLayout("Amount:", String.valueOf(item.getAmt())));
		verticalLayout3.addComponent(new DataRowLayout("Total Amount:", String.valueOf(item.getTotalAmt())));
		verticalLayout3.addComponent(new DataRowLayout("Received Amount:", String.valueOf(item.getReceivedAmt())));
		verticalLayout3.addComponent(new DataRowLayout("Monthly Fee:", String.valueOf(item.getMonthlyFee())));
		verticalLayout3.addComponent(new DataRowLayout("Other Amount:", String.valueOf(item.getOtherAmt())));
		verticalLayout3.addComponent(new DataRowLayout("Reading Doc #:", item.getReadingDocno()));
		verticalLayout3.addComponent(new DataRowLayout("Reading Doc Type:", item.getReadingDoctype()));
		verticalLayout3.addComponent(new DataRowLayout("Last Invoice Doc #:", item.getLastInvdocno()));

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

}
