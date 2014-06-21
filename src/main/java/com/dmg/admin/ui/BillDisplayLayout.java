package com.dmg.admin.ui;

import com.dmg.admin.bean.Bill;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class BillDisplayLayout extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 701948131350786451L;

	@PropertyId("contractNo")
	TextField contractNoLabel = new TextField("Contract #");

	@PropertyId("company")
	TextField companyLabel = new TextField("Company");

	@PropertyId("docNo")
	TextField docNoLabel = new TextField("Doc #");

	@PropertyId("docType")
	TextField docTypeLabel = new TextField("Doc Type");

	@PropertyId("yearCode")
	TextField yearCodeLabel = new TextField("Year Code");

	@PropertyId("srNo")
	TextField srNoLabel = new TextField("Sr. No.");

	@PropertyId("buildingCode")
	TextField buildingCodeLabel = new TextField("Building Code");

	@PropertyId("buildingName")
	TextField buildingNameLabel = new TextField("Building Name");

	@PropertyId("apartmentCode")
	TextField apartmentCodeLabel = new TextField("Apartment Code");

	@PropertyId("lastReading")
	TextField lastReadingLabel = new TextField("Last Reading");

	@PropertyId("lastReadingDate")
	TextField lastReadingDateLabel = new TextField("Last Reading Date");

	@PropertyId("currReading")
	TextField currReadingLabel = new TextField("Current Reading");

	@PropertyId("currReadingDate")
	TextField currReadingDateLabel = new TextField("Current Reading Date");

	@PropertyId("totalUnit")
	TextField totalUnitLabel = new TextField("Total Unit");

	@PropertyId("unitPrice")
	TextField unitPriceLabel = new TextField("Unit Price");

	@PropertyId("amt")
	TextField amtLabel = new TextField("Amount");

	@PropertyId("totalAmt")
	TextField totalAmtLabel = new TextField("Total Amount");

	@PropertyId("receivedAmt")
	TextField receivedAmtLabel = new TextField("Received Amount");

	@PropertyId("monthlyFee")
	TextField monthlyFeeLabel = new TextField("Monthly Fee");

	@PropertyId("otherAmt")
	TextField otherAmtLabel = new TextField("Other Amount");

	@PropertyId("readingDocno")
	TextField readingDocnoLabel = new TextField("Reading Doc #");

	@PropertyId("readingDoctype")
	TextField readingDoctypeLabel = new TextField("Reading Doc Type");

	@PropertyId("lastInvdocno")
	TextField lastInvdocnoLabel = new TextField("Last Invoice Doc #");

	FormLayout layout = new FormLayout();

	FieldGroup binder;

	public BillDisplayLayout(Bill item) {

		layout.setMargin(true);
		layout.setWidth("100%");

		// setting default values for null
		VerticalLayout verticalLayout1 = new VerticalLayout();
		VerticalLayout verticalLayout2 = new VerticalLayout();
		VerticalLayout verticalLayout3 = new VerticalLayout();

		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setMargin(true);
		horizontalLayout.setSpacing(true);

		verticalLayout1.addComponent(contractNoLabel);
		verticalLayout1.addComponent(companyLabel);
		verticalLayout1.addComponent(docNoLabel);
		verticalLayout1.addComponent(docTypeLabel);
		verticalLayout1.addComponent(yearCodeLabel);
		verticalLayout1.addComponent(srNoLabel);
		verticalLayout1.addComponent(buildingCodeLabel);
		verticalLayout1.addComponent(buildingNameLabel);

		verticalLayout2.addComponent(apartmentCodeLabel);
		verticalLayout2.addComponent(lastReadingLabel);
		verticalLayout2.addComponent(lastReadingDateLabel);
		verticalLayout2.addComponent(currReadingLabel);
		verticalLayout2.addComponent(currReadingDateLabel);
		verticalLayout2.addComponent(totalUnitLabel);
		verticalLayout2.addComponent(unitPriceLabel);
		verticalLayout2.addComponent(amtLabel);

		verticalLayout3.addComponent(totalAmtLabel);
		verticalLayout3.addComponent(receivedAmtLabel);
		verticalLayout3.addComponent(monthlyFeeLabel);
		verticalLayout3.addComponent(otherAmtLabel);
		verticalLayout3.addComponent(readingDocnoLabel);
		verticalLayout3.addComponent(readingDoctypeLabel);
		verticalLayout3.addComponent(lastInvdocnoLabel);

		Label label = new Label();
		horizontalLayout.addComponent(verticalLayout1);
		horizontalLayout.addComponent(verticalLayout2);
		horizontalLayout.addComponent(verticalLayout3);

		/*
		 * horizontalLayout.setExpandRatio(verticalLayout1, 0.45F);
		 * horizontalLayout.setExpandRatio(verticalLayout2, 0.45F);
		 * horizontalLayout.setExpandRatio(verticalLayout3, 0.45F);
		 */

		horizontalLayout.setSizeFull();

		layout.addComponent(horizontalLayout);

		// TODO Auto-generated constructor stub

		BeanItem<Bill> bean = new BeanItem<Bill>(item);

		binder = new FieldGroup();
		binder.setItemDataSource(bean);
		binder.bindMemberFields(this);
		setCompositionRoot(layout);

	}

	public FormLayout getLayout() {
		return layout;
	}

	public FieldGroup getBinder() {
		return binder;
	}

	public void setBinder(FieldGroup binder) {
		this.binder = binder;
	}

}
