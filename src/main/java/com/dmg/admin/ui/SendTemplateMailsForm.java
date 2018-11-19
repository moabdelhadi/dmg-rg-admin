package com.dmg.admin.ui;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class SendTemplateMailsForm extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 701948131350786451L;


	ComboBox cityField = new ComboBox("City");
	ComboBox companyField = new ComboBox("Company");
	TextField buildingNo = new TextField("Buildin No. or ALL");
	TextField accountNo = new TextField("Account No, or ALL");
	TextField titleField = new TextField("Email Title");
	ComboBox templateField = new ComboBox("Template");

	TextArea  messageText = new TextArea("Message Body");

	Label addDatalabel = new Label("Additional Data:");
	CheckBox nameCheck=new CheckBox("Contract Name:", false);
	CheckBox isActive=new CheckBox("Is Active:", true);
	CheckBox isBalaneMore=new CheckBox("Balance > 0:", true);
	CheckBox contractNoCheck=new CheckBox("Contract No:", false);
	CheckBox amountCheck=new CheckBox("Amount:", false);
	CheckBox passwordCheck=new CheckBox("Password:", false);

	FormLayout layout = new FormLayout();

	FieldGroup binder;

	public SendTemplateMailsForm() {
		
		layout.addStyleName("top-bottom-margins");
		layout.setWidth("100%");
		layout.setMargin(true);

		buildingNo.setWidth("100%");
		accountNo.setWidth("100%");
		cityField.setWidth("100%");
		companyField.setWidth("100%");
		
		List<String> itemIds=new ArrayList<String>();
		itemIds.add("DUBAI");
		itemIds.add("ABUDHABI");
		cityField.addItems(itemIds);
		
		List<String> itemIdsCo=new ArrayList<String>();
		itemIdsCo.add("01");
		itemIdsCo.add("02");
		companyField.addItems(itemIdsCo);
		
		List<String> itemTempId=new ArrayList<String>();
		itemTempId.add("firstReminder");
		itemTempId.add("secondReminder");
		itemTempId.add("free");
		templateField.addItems(itemTempId);
		messageText.setEnabled(true);
		
		layout.addComponent(cityField);
		layout.addComponent(companyField);
		
		layout.addComponent(buildingNo);
		layout.addComponent(accountNo);
		layout.addComponent(isActive);
		layout.addComponent(isBalaneMore);
		layout.addComponent(templateField);
		layout.addComponent(titleField);
		layout.addComponent(messageText);
		layout.addComponent(addDatalabel);
		layout.addComponent(nameCheck);
		layout.addComponent(contractNoCheck);
		layout.addComponent(amountCheck);
		layout.addComponent(passwordCheck);
		
//		BeanItem<SendInv> bean = new BeanItem<SendInv>(item);
//		binder = new FieldGroup();
//		binder.setItemDataSource(bean);
//		binder.bindMemberFields(this);
		setCompositionRoot(layout);

	}


	public ComboBox getCityField() {
		return cityField;
	}

	public ComboBox getCompanyField() {
		return companyField;
	}

	public TextField getBuildingNo() {
		return buildingNo;
	}

	public TextField getAccountNo() {
		return accountNo;
	}



	public TextArea getMessageText() {
		return messageText;
	}

	public CheckBox getAmountCheck() {
		return amountCheck;
	}


	public CheckBox getPasswordCheck() {
		return passwordCheck;
	}


	public ComboBox getTemplateField() {
		return templateField;
	}


	public CheckBox getNameCheck() {
		return nameCheck;
	}
	
	public CheckBox getIsActive() {
		return isActive;
	}


	public CheckBox getIsBalaneMore() {
		return isBalaneMore;
	}


	public TextField getTitleField() {
		return titleField;
	}


	public CheckBox getContractNoCheck() {
		return contractNoCheck;
	}


	public FormLayout getLayout() {
		return layout;
	}

	public void setBinder(FieldGroup binder) {
		this.binder = binder;
	}
}