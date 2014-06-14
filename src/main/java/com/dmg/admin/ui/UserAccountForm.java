package com.dmg.admin.ui;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class UserAccountForm extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 701948131350786451L;

	@PropertyId("name")
	TextField nameField = new TextField("Name");

	@PropertyId("email")
	TextField emailField = new TextField("Email");

	@PropertyId("city")
	TextField cityField = new TextField("City");

	@PropertyId("buildingNumber")
	TextField buildingNumberField = new TextField("Building Number");

	@PropertyId("appartmentNumber")
	TextField appartmentNumberField = new TextField("Apartment Number");

	@PropertyId("contractNumber")
	TextField contractNoField = new TextField("Contract Number");

	@PropertyId("phone")
	TextField phoneField = new TextField("Phone");

	@PropertyId("mobile")
	TextField mobileField = new TextField("Mobile");

	@PropertyId("pobox")
	TextField poboxField = new TextField("P.O.Box");

	@PropertyId("poboxCity")
	TextField poboxCityField = new TextField("P.O.Box City");

	public UserAccountForm(Item item) {
		FormLayout layout = new FormLayout();
		layout.setMargin(true);
		layout.setWidth("100%");

		nameField.setWidth("150%");
		emailField.setWidth("150%");
		cityField.setWidth("150%");
		buildingNumberField.setWidth("150%");
		appartmentNumberField.setWidth("150%");
		contractNoField.setWidth("150%");
		phoneField.setWidth("150%");
		mobileField.setWidth("150%");
		poboxField.setWidth("150%");
		poboxCityField.setWidth("150%");

		layout.addComponent(nameField);
		layout.addComponent(emailField);
		layout.addComponent(cityField);
		layout.addComponent(buildingNumberField);
		layout.addComponent(appartmentNumberField);
		layout.addComponent(contractNoField);
		layout.addComponent(phoneField);
		layout.addComponent(mobileField);
		layout.addComponent(poboxField);
		layout.addComponent(poboxCityField);
		// TODO Auto-generated constructor stub

		FieldGroup binder = new FieldGroup(item);
		binder.bindMemberFields(this);
		setCompositionRoot(layout);

	}
}
