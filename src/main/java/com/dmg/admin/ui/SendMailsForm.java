package com.dmg.admin.ui;

import com.dmg.core.bean.SendInv;
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

public class SendMailsForm extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 701948131350786451L;

	@PropertyId("pdfDirPath")
	TextField pdfDirPathField = new TextField("PDF Dir Path");

	@PropertyId("mapFilePath")
	TextField mapFilePathField = new TextField("Map File");

	@PropertyId("city")
	TextField cityField = new TextField("City");
	
	@PropertyId("company")
	TextField companyField = new TextField("Company");


	FormLayout layout = new FormLayout();

	FieldGroup binder;

	public SendMailsForm() {
		
		layout.addStyleName("top-bottom-margins");
		layout.setWidth("100%");
		layout.setMargin(true);

		pdfDirPathField.setWidth("100%");
		mapFilePathField.setWidth("100%");
		cityField.setWidth("100%");
		companyField.setWidth("100%");

		layout.addComponent(cityField);
		layout.addComponent(pdfDirPathField);
		layout.addComponent(mapFilePathField);
		layout.addComponent(companyField);

//		BeanItem<SendInv> bean = new BeanItem<SendInv>(item);
//		binder = new FieldGroup();
//		binder.setItemDataSource(bean);
//		binder.bindMemberFields(this);
		setCompositionRoot(layout);

	}

	public TextField getCityField() {
		return cityField;
	}
	
	public TextField getPdfDirPathField() {
		return pdfDirPathField;
	}

	public TextField getMapFilePathField() {
		return mapFilePathField;
	}

	public TextField getCompanyField() {
		return companyField;
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