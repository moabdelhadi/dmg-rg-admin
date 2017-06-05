package com.dmg.admin.view;

import java.io.File;

import com.dmg.admin.auth.util.PasswordUtil;
import com.dmg.admin.exception.PasswordRequirementException;
import com.dmg.admin.service.SendEmailService;
import com.dmg.admin.service.UserAccountService;
import com.dmg.admin.ui.ComponentUtil;
import com.dmg.admin.ui.SendMailsForm;
import com.dmg.admin.ui.UserAccountForm;
import com.dmg.core.bean.UserAccount;
import com.dmg.core.bean.UserStatus;
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

public class SendInvEmailView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3459100173504667518L;
	private final Navigator navigator;
	public static final String NAME = "send-inv-email";
	private final SendEmailService sendEmailService;
	private String id;
//	private SendInv userAccount;
	private Panel panel;
	private Button startSendBtn;
	private SendMailsForm sendMailForm;

	public SendInvEmailView(Navigator navigator) {
		this.navigator = navigator;
		sendEmailService = new SendEmailService();
		setSizeFull();
		setSpacing(true);
		setMargin(true);
		initView();
	}

	private void initView() {
		panel = new Panel("Send Email Form");
		panel.setWidth("35%");
		panel.setHeight("100%");
		addComponent(ComponentUtil.initMenuButton(navigator, StartView.NAME, "Go back to main view"));
		addComponent(panel);
		setExpandRatio(panel, 0.95F);
		setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
		startSendBtn = new Button("Send Start");
		startSendBtn.addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				try {
					if (validateFields()) {
						
						//sendMailForm.getBinder().commit();
						
						sendEmailService.update(userAccount);
						
						
						Notification.show("User has been updated successfully!", Type.HUMANIZED_MESSAGE);
					}
				} catch (CommitException e) {
					Notification.show("Error commiting changes - " + e.getCause().getMessage(), Type.ERROR_MESSAGE);
				} catch (DataAccessLayerException e) {
					Notification.show("DB ERROR when saving the changes!", Type.ERROR_MESSAGE);
				}
			}
		});
		

	}

	private boolean validateFields() {
		
		
		String city = sendMailForm.getCityField().getValue();
		String company = sendMailForm.getCompanyField().getValue();
		String mapFilePath = sendMailForm.getMapFilePathField().getValue();
		String pdfDirPath = sendMailForm.getPdfDirPathField().getValue();

		File mapFile = new File("")
		
		return true;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		//id = event.getParameters();
		try {
			
//			userAccount = accountService.getUserAcount(Long.parseLong(id));

//			PropertysetItem item = new PropertysetItem();
//			item.addItemProperty("name", new ObjectProperty<String>(userAccount.getName() == null ? "" : userAccount.getName()));
//			item.addItemProperty("email", new ObjectProperty<String>(userAccount.getEmail() == null ? "" : userAccount.getEmail()));
//			item.addItemProperty("city", new ObjectProperty<String>(userAccount.getCity() == null ? "" : userAccount.getCity()));
//			item.addItemProperty("buildingNumber", new ObjectProperty<String>(userAccount.getBuildingNumber() == null ? "" : userAccount.getBuildingNumber()));
//			item.addItemProperty("apartmentNumber", new ObjectProperty<String>(userAccount.getAppartmentNumber() == null ? "" : userAccount.getAppartmentNumber()));
//			item.addItemProperty("contractNumber", new ObjectProperty<String>(userAccount.getContractNo() == null ? "" : userAccount.getContractNo()));
//			item.addItemProperty("phone", new ObjectProperty<String>(userAccount.getPhone() == null ? "" : userAccount.getPhone()));
//			item.addItemProperty("mobile", new ObjectProperty<String>(userAccount.getMobile() == null ? "" : userAccount.getMobile()));
//			item.addItemProperty("pobox", new ObjectProperty<String>(userAccount.getPobox() == null ? "" : userAccount.getPobox()));
//			item.addItemProperty("poboxCity", new ObjectProperty<String>(userAccount.getPoboxCity() == null ? "" : userAccount.getPoboxCity()));
//			item.addItemProperty("enable", new ObjectProperty<Boolean>(userAccount.getEnable()));

			sendMailForm = new SendMailsForm();
			//here setting the read only fields
			sendMailForm.getLayout().addComponent(startSendBtn);
			panel.setContent(sendMailForm);
		
		} catch (NumberFormatException e) {
			if (e instanceof NumberFormatException) {
				Notification.show("ID entered is not correct", Type.ERROR_MESSAGE);
				navigator.navigateTo("");
			} else {
				Notification.show("DB error - Please contact system admin", Type.ERROR_MESSAGE);
			}
		}
	}
}
