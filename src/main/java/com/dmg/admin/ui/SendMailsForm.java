package com.dmg.admin.ui;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class SendMailsForm extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 701948131350786451L;

	TextField pdfDirPathField = new TextField("PDF Dir Path");

	TextField mapFilePathField = new TextField("Map File");
	TextField prefixField = new TextField("PREFIX");


	ComboBox cityField = new ComboBox("City");
	
	ComboBox companyField = new ComboBox("Company");


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
		
		List<String> itemIds=new ArrayList<String>();
		itemIds.add("DUBAI");
		itemIds.add("ABUDHABI");
		cityField.addItems(itemIds);
		
		List<String> itemIdsCo=new ArrayList<String>();
		itemIdsCo.add("01");
		itemIdsCo.add("02");
		companyField.addItems(itemIdsCo);

		layout.addComponent(cityField);
		layout.addComponent(pdfDirPathField);
		layout.addComponent(mapFilePathField);
		layout.addComponent(companyField);
		layout.addComponent(prefixField);
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

	public TextField getPdfDirPathField() {
		return pdfDirPathField;
	}

	public TextField getMapFilePathField() {
		return mapFilePathField;
	}
	
	public TextField getPrefixField() {
		return prefixField;
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