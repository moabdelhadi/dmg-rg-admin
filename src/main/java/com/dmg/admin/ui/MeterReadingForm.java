package com.dmg.admin.ui;

import com.dmg.core.bean.MeterReading;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.TextField;

public class MeterReadingForm extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 701948131350786451L;



	@PropertyId("creationDate")
	DateField dateField = new DateField("Date");

	@PropertyId("city")
	TextField cityField = new TextField("City");

	@PropertyId("meterReading")
	TextField readingField = new TextField("Reading");

//	@PropertyId("imageName")
//	Image imageField = new Image("Image");

	@PropertyId("contractNo")
	TextField contractNoField = new TextField("Contract Number");

	@PropertyId("status")
	TextField statusField = new TextField("status");

	FormLayout layout = new FormLayout();

	FieldGroup binder;

	public MeterReadingForm(MeterReading item) {
		layout.addStyleName("top-bottom-margins");

		layout.setWidth("100%");
		layout.setMargin(true);

		dateField.setWidth("100%");
		readingField.setWidth("100%");
		cityField.setWidth("100%");
//		imageField.setWidth("100%");
		statusField.setWidth("100%");
		contractNoField.setWidth("100%");
		
		
		layout.addComponent(cityField);
		layout.addComponent(contractNoField);
		layout.addComponent(readingField);
		layout.addComponent(dateField);
//		layout.addComponent(imageField);
		layout.addComponent(statusField);

		// TODO Auto-generated constructor stub

		BeanItem<MeterReading> bean = new BeanItem<MeterReading>(item);
		binder = new FieldGroup();
		binder.setItemDataSource(bean);
		binder.bindMemberFields(this);
		setCompositionRoot(layout);

	}


	public DateField getDateField() {
		return dateField;
	}

	public void setDateField(DateField dateField) {
		this.dateField = dateField;
	}

	public TextField getCityField() {
		return cityField;
	}

	public void setCityField(TextField cityField) {
		this.cityField = cityField;
	}

	public TextField getReadingField() {
		return readingField;
	}

	public void setReadingField(TextField readingField) {
		this.readingField = readingField;
	}

//	public Image getImageField() {
//		return imageField;
//	}
//
//	public void setImageField(Image imageField) {
//		this.imageField = imageField;
//	}

	public TextField getStatusField() {
		return statusField;
	}

	public void setStatusField(TextField statusField) {
		this.statusField = statusField;
	}


	public void setContractNoField(TextField contractNoField) {
		this.contractNoField = contractNoField;
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
