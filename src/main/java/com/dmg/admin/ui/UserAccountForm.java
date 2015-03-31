package com.dmg.admin.ui;

import com.dmg.core.bean.UserAccount;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;
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

	@PropertyId("enable")
	CheckBox enableField = new CheckBox("Enable");

	PasswordField passwordField = new PasswordField("New Password");

	PasswordField confirmPasswordField = new PasswordField("Confirm Password");

	FormLayout layout = new FormLayout();

	FieldGroup binder;

	public UserAccountForm(UserAccount item) {
		layout.addStyleName("top-bottom-margins");

		layout.setWidth("100%");
		layout.setMargin(true);

		nameField.setWidth("100%");
		emailField.setWidth("100%");
		cityField.setWidth("100%");
		buildingNumberField.setWidth("100%");
		appartmentNumberField.setWidth("100%");
		contractNoField.setWidth("100%");
		phoneField.setWidth("100%");
		mobileField.setWidth("100%");
		poboxField.setWidth("100%");
		poboxCityField.setWidth("100%");
		passwordField.setWidth("100%");
		confirmPasswordField.setWidth("100%");

		passwordField.setInputPrompt("password");
		confirmPasswordField.setInputPrompt("password");

		emailField.addValidator(new EmailValidator("Please enter a valid email"));

		layout.addComponent(cityField);
		layout.addComponent(contractNoField);
		layout.addComponent(buildingNumberField);
		layout.addComponent(appartmentNumberField);
		layout.addComponent(nameField);
		layout.addComponent(emailField);
		layout.addComponent(mobileField);
		layout.addComponent(phoneField);
		layout.addComponent(poboxField);
		layout.addComponent(poboxCityField);
		layout.addComponent(passwordField);
		layout.addComponent(confirmPasswordField);
		layout.addComponent(enableField);

		// TODO Auto-generated constructor stub

		BeanItem<UserAccount> bean = new BeanItem<UserAccount>(item);
		binder = new FieldGroup();
		binder.setItemDataSource(bean);
		binder.bindMemberFields(this);
		setCompositionRoot(layout);

	}

	public PasswordField getPasswordField() {
		return passwordField;
	}

	public PasswordField getConfirmPasswordField() {
		return confirmPasswordField;
	}

	public TextField getNameField() {
		return nameField;
	}

	public TextField getCityField() {
		return cityField;
	}

	public TextField getBuildingNumberField() {
		return buildingNumberField;
	}

	public TextField getAppartmentNumberField() {
		return appartmentNumberField;
	}

	public TextField getContractNoField() {
		return contractNoField;
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
