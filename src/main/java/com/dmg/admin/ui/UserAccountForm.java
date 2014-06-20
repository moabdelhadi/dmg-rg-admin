package com.dmg.admin.ui;

import com.dmg.admin.bean.UserAccount;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.EmailValidator;
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

	@PropertyId("contractNo")
	TextField contractNoField = new TextField("Contract Number");

	@PropertyId("phone")
	TextField phoneField = new TextField("Phone");

	@PropertyId("mobile")
	TextField mobileField = new TextField("Mobile");

	@PropertyId("pobox")
	TextField poboxField = new TextField("P.O.Box");

	@PropertyId("poboxCity")
	TextField poboxCityField = new TextField("P.O.Box City");

	FormLayout layout = new FormLayout();

	FieldGroup binder;

	public UserAccountForm(UserAccount item) {

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
		//setting default values for null
		nameField.setNullRepresentation("");
		emailField.setNullRepresentation("");
		cityField.setNullRepresentation("");
		buildingNumberField.setNullRepresentation("");
		appartmentNumberField.setNullRepresentation("");
		contractNoField.setNullRepresentation("");
		phoneField.setNullRepresentation("");
		mobileField.setNullRepresentation("");
		poboxField.setNullRepresentation("");
		poboxCityField.setNullRepresentation("");

		emailField.addValidator(new EmailValidator("Please enter a valid email"));

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

		BeanItem<UserAccount> bean = new BeanItem<UserAccount>(item);
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
