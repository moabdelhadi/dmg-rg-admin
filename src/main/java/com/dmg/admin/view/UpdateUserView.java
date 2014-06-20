package com.dmg.admin.view;

import com.dmg.admin.bean.UserAccount;
import com.dmg.admin.service.UserAccountService;
import com.dmg.admin.ui.ComponentUtil;
import com.dmg.admin.ui.UserAccountForm;
import com.dmg.core.exception.DataAccessLayerException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class UpdateUserView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2710413237601726018L;
	private final Navigator navigator;
	public static final String NAME = "update-user";
	private final UserAccountService accountService;
	private String id;
	private UserAccount userAccount;
	private Panel panel;
	private Button updateBtn;
	private UserAccountForm userAccountForm;

	public UpdateUserView(Navigator navigator) {
		this.navigator = navigator;
		accountService = new UserAccountService();
		setSizeFull();
		setSpacing(true);
		setMargin(true);
		initView();
	}

	private void initView() {
		panel = new Panel("Update User Account");
		panel.setWidth("35%");
		addComponent(ComponentUtil.initMenuButton(navigator, UsersView.NAME, "Go back to users view"));
		addComponent(panel);
		setExpandRatio(panel, 0.95F);
		setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
		updateBtn = new Button("Update");

		updateBtn.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				try {
					userAccountForm.getBinder().commit();
					accountService.update(userAccount);
					Notification.show("User has been updated successfully!", Type.HUMANIZED_MESSAGE);
				} catch (CommitException e) {
					Notification.show("Error commiting changes - " + e.getCause().getMessage(), Type.ERROR_MESSAGE);
				} catch (DataAccessLayerException e) {
					Notification.show("DB ERROR when saving the changes!", Type.ERROR_MESSAGE);
				}
			}
		});
	}

	@Override
	public void enter(ViewChangeEvent event) {
		id = event.getParameters();
		try {
			userAccount = accountService.getUserAcount(Long.parseLong(id));

			/*
			 * FormLayout formLayout = new FormLayout(); TextField nameField =
			 * new TextField("Name"); nameField.setWidth("150%");
			 * formLayout.addComponent(nameField); TextField emailField = new
			 * TextField("Email"); emailField.setWidth("150%");
			 * formLayout.addComponent(emailField); TextField cityField = new
			 * TextField("City"); cityField.setWidth("150%");
			 * formLayout.addComponent(cityField); TextField buildingNumberField
			 * = new TextField("Building Number");
			 * buildingNumberField.setWidth("150%");
			 * formLayout.addComponent(buildingNumberField); TextField
			 * appartmentNumberField = new TextField("Apartment Number");
			 * appartmentNumberField.setWidth("150%");
			 * formLayout.addComponent(appartmentNumberField); TextField
			 * contractNoField = new TextField("Contract Number");
			 * contractNoField.setWidth("150%");
			 * formLayout.addComponent(contractNoField); TextField phoneField =
			 * new TextField("Phone"); phoneField.setWidth("150%");
			 * formLayout.addComponent(phoneField); TextField mobileField = new
			 * TextField("Mobile"); mobileField.setWidth("150%");
			 * formLayout.addComponent(mobileField); TextField poboxField = new
			 * TextField("P.O.Box"); poboxField.setWidth("150%");
			 * formLayout.addComponent(poboxField); TextField poboxCityField =
			 * new TextField("P.O.Box City"); poboxCityField.setWidth("150%");
			 * formLayout.addComponent(poboxCityField);
			 * formLayout.setWidth("100%"); formLayout.setMargin(true);
			 */

			PropertysetItem item = new PropertysetItem();
			item.addItemProperty("name", new ObjectProperty<String>(userAccount.getName() == null ? "" : userAccount.getName()));
			item.addItemProperty("email", new ObjectProperty<String>(userAccount.getEmail() == null ? "" : userAccount.getEmail()));
			item.addItemProperty("city", new ObjectProperty<String>(userAccount.getCity() == null ? "" : userAccount.getCity()));
			item.addItemProperty("buildingNumber", new ObjectProperty<String>(userAccount.getBuildingNumber() == null ? "" : userAccount.getBuildingNumber()));
			item.addItemProperty("apartmentNumber", new ObjectProperty<String>(userAccount.getAppartmentNumber() == null ? "" : userAccount.getAppartmentNumber()));
			item.addItemProperty("contractNumber", new ObjectProperty<String>(userAccount.getContractNo() == null ? "" : userAccount.getContractNo()));
			item.addItemProperty("phone", new ObjectProperty<String>(userAccount.getPhone() == null ? "" : userAccount.getPhone()));
			item.addItemProperty("mobile", new ObjectProperty<String>(userAccount.getMobile() == null ? "" : userAccount.getMobile()));
			item.addItemProperty("pobox", new ObjectProperty<String>(userAccount.getPobox() == null ? "" : userAccount.getPobox()));
			item.addItemProperty("poboxCity", new ObjectProperty<String>(userAccount.getPoboxCity() == null ? "" : userAccount.getPoboxCity()));

			// formLayout.setMargin(true);

			// Now create a binder that can also create the fields
			// using the default field factory
			/*
			 * FieldGroup binder = new FieldGroup(item); binder.bind(nameField,
			 * "name"); binder.bind(emailField, "email"); binder.bind(cityField,
			 * "city"); binder.bind(buildingNumberField, "buildingNumber");
			 * binder.bind(appartmentNumberField, "apartmentNumber");
			 * binder.bind(contractNoField, "contractNumber");
			 * binder.bind(phoneField, "phone"); binder.bind(mobileField,
			 * "mobile"); binder.bind(poboxField, "pobox");
			 * binder.bind(poboxCityField, "poboxCity");
			 */
			/*
			 * formLayout.addComponent(binder.buildAndBind("Name", "name"));
			 * formLayout.addComponent(binder.buildAndBind("Email", "email"));
			 * formLayout.addComponent(binder.buildAndBind("City", "city"));
			 * formLayout.addComponent(binder.buildAndBind("Building #",
			 * "buildingNumber"));
			 * formLayout.addComponent(binder.buildAndBind("Apartment #",
			 * "apartmentNumber"));
			 * formLayout.addComponent(binder.buildAndBind("Contract #",
			 * "contractNumber"));
			 * formLayout.addComponent(binder.buildAndBind("Phone", "phone"));
			 * formLayout.addComponent(binder.buildAndBind("Mobile", "mobile"));
			 * formLayout.addComponent(binder.buildAndBind("P.O.Box", "pobox"));
			 * formLayout.addComponent(binder.buildAndBind("P.O.Box City",
			 * "poboxCity"));
			 */
			userAccountForm = new UserAccountForm(userAccount);

			userAccountForm.getLayout().addComponent(updateBtn);

			panel.setContent(userAccountForm);
		} catch (NumberFormatException | DataAccessLayerException e) {
			if (e instanceof NumberFormatException) {
				Notification.show("ID entered is not correct", Type.ERROR_MESSAGE);
				navigator.navigateTo("");
			} else {
				Notification.show("DB error - Please contact system admin", Type.ERROR_MESSAGE);
			}
		}
	}
}
