package com.dmg.admin.view;

import java.util.Date;

import com.dmg.admin.service.MeterService;
import com.dmg.admin.ui.ComponentUtil;
import com.dmg.admin.ui.MeterReadingForm;
import com.dmg.core.bean.MeterReading;
import com.dmg.core.exception.DataAccessLayerException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Resource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class UpdateGasMeterView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2710413237601726018L;
	private final Navigator navigator;
	public static final String NAME = "update-meter-reading";
	private final MeterService meterService;
	private String id;
	private MeterReading meterReading;
	private Panel panel;
	private Button updateBtn;
//	private Button activateUserBtn;
	private MeterReadingForm meterReadingForm;
	private String imageNameTemp = "";

	public UpdateGasMeterView(Navigator navigator) {
		this.navigator = navigator;
		meterService = new MeterService();
		setSizeFull();
		setSpacing(true);
		setMargin(true);
		initView();
	}

	private void initView() {
		panel = new Panel("Update User Account");
		panel.setWidth("35%");
		panel.setHeight("100%");
		addComponent(ComponentUtil.initMenuButton(navigator, GasMeterView.NAME, "Go back to Meter Reading List view"));
		addComponent(panel);
		setExpandRatio(panel, 0.95F);
		setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
		updateBtn = new Button("Update");
//		activateUserBtn = new Button("Activate User");
		updateBtn.addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				try {
//					if (validatePassword()) {
						meterReadingForm.getBinder().commit();
						meterReading.setSyncStatus(2);
						meterReading.setImageName(imageNameTemp);
						meterService.update(meterReading);
						Notification.show("Meter Marked As read successfully!", Type.HUMANIZED_MESSAGE);
//					}
				} catch (CommitException e) {
					Notification.show("Error commiting changes - " + e.getCause().getMessage(), Type.ERROR_MESSAGE);
				} catch (DataAccessLayerException e) {
					Notification.show("DB ERROR when saving the changes!", Type.ERROR_MESSAGE);
				}
			}
		});
		
//		activateUserBtn.addClickListener(new ClickListener() {
//			
//			private static final long serialVersionUID = 1L;
//			
//			@Override
//			public void buttonClick(ClickEvent event) {
//
//				try {
//
//						meterReading.setStatus(UserStatus.ACTIVE.value());
//						meterReading.setActivationKey("");
//						meterReading.setSyncStatus(2);
//						meterService.update(meterReading);
//						Notification.show("User has been activated successfully!", Type.HUMANIZED_MESSAGE);
//				} catch (DataAccessLayerException e) {
//					Notification.show("DB ERROR when saving the changes!", Type.ERROR_MESSAGE);
//				}
//				
//				
//				
//				
//			}
//		});
	}

	@Override
	public void enter(ViewChangeEvent event) {
		id = event.getParameters();
		try {
			meterReading = meterService.getMeterReading(Long.parseLong(id));

			PropertysetItem item = new PropertysetItem();
			
			imageNameTemp = meterReading.getImageName();
			item.addItemProperty("contractNumber", new ObjectProperty<String>(meterReading.getContractNo() == null ? "" : meterReading.getContractNo()));
			item.addItemProperty("date", new ObjectProperty<Date>(meterReading.getCreationDate()));
			item.addItemProperty("city", new ObjectProperty<String>(meterReading.getCity() == null ? "" : meterReading.getCity()));
			item.addItemProperty("reading", new ObjectProperty<String>(meterReading.getMeterReading() == null ? "" : meterReading.getMeterReading()));
			item.addItemProperty("status", new ObjectProperty<String>(meterReading.getStatus() == null ? "" : meterReading.getStatus()));

			meterReadingForm = new MeterReadingForm(meterReading);
			//here setting the read only fields
			meterReadingForm.getCityField().setReadOnly(true);
			meterReadingForm.getContractNoField().setReadOnly(true);
			meterReadingForm.getDateField().setReadOnly(true);
			meterReadingForm.getReadingField().setReadOnly(true);
			
			
			Resource externalResource = new ExternalResource("https://pay.royalgas.com/dmg-rg-client-v2/imgs/"+ meterReading.getImageName());
			Image img = new Image("", externalResource);
			img.setWidth("400px");
			meterReadingForm.getLayout().addComponent(img);
			meterReadingForm.getLayout().addComponent(updateBtn);
//			meterReadingForm.getLayout().addComponent(activateUserBtn);
			panel.setContent(meterReadingForm);
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
