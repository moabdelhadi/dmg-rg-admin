package com.dmg.admin.view;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dmg.admin.service.SendEmailService;
import com.dmg.admin.ui.ComponentUtil;
import com.dmg.admin.ui.SendMailsForm;
import com.dmg.admin.util.PropertiesManager;
import com.dmg.core.bean.BeansFactory;
import com.dmg.core.bean.Constants;
import com.dmg.core.bean.SendInv;
import com.dmg.core.bean.UserAccount;
import com.dmg.core.exception.DataAccessLayerException;
import com.dmg.core.persistence.FacadeFactory;
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

public class SendInvEmailTemplateView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(SendInvEmailTemplateView.class);
	private final Navigator navigator;
	public static final String NAME = "sendEmailTemplate";
	//private final SendEmailService sendEmailService;
	private String id;
//	private SendInv userAccount;
	//private Panel panel;
	private Button startSendBtn;
	private SendMailsForm sendMailForm;

	public SendInvEmailTemplateView(Navigator navigator) {
		this.navigator = navigator;
		sendEmailService = new SendEmailService();
		setSizeFull();
		setSpacing(true);
		setMargin(true);
		initView();
	}

	private void initView() {
		panel = new Panel("Send Template Email Form");
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
						
						String city = sendMailForm.getCityField().getValue().toString();
						String company = sendMailForm.getCompanyField().getValue().toString();
						String buildingCode = sendMailForm.getBuildingField().getValue();
						String pdfDirPath = sendMailForm.getPdfDirPathField().getValue();
						String prefix = sendMailForm.getPrefixField().getValue();
						//File mapFile = new File(mapFilePath);

						UserAccount user = BeansFactory.getInstance().getUserAccount(city);
						Map<String, Object> parameters = new HashMap<String, Object>();
						parameters.put(Constants.USER_BUILDING_NO, buildingCode.trim());
						parameters.put(Constants.USER_CITY, city);
						parameters.put(Constants.USER_COMPANY, company);
						List<? extends UserAccount> list = FacadeFactory.getFacade().list(user.getClass(), parameters);
						
						addrecordstoDB(list, city, company, pdfDirPath,prefix);
						Notification.show("User has been updated successfully!", Type.HUMANIZED_MESSAGE);
						
					}
				} catch (DataAccessLayerException e) {
					log.error("DataAccessLayerException",e);
					Notification.show("ERROR in reading Date From DB!", Type.ERROR_MESSAGE);
				} catch (IOException e) {
					log.error("error in coping files",e);
					Notification.show("ERROR in COPING FILES!", Type.ERROR_MESSAGE);
				} 
			}

		});
		

	}
	
	private void addrecordstoDB(List<? extends UserAccount> users, String city, String company, String pdfDirPath, String prefix) throws IOException {
//		Map<String, String> map = new HashMap<String, String>();
		String pdfBasePath = PropertiesManager.getInstance().getProperty("adnc.pdf.base.path");
		String pdfSendStatus = PropertiesManager.getInstance().getProperty("adnc.pdf.send.status");
		if(!pdfBasePath.endsWith("/")){
			pdfBasePath+="/";
		}
		Date time = Calendar.getInstance().getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM");
		String format = df.format(time);
		pdfBasePath+=format;
		File dir = new File(pdfBasePath);
		if(!dir.exists()){
			dir.mkdirs();
		}
//		BufferedReader reader = new BufferedReader(new FileReader(mapFile));
//		String line = reader.readLine();
		for (UserAccount userAccount : users) {
			if(userAccount== null || userAccount.getAdnocRefID()==null || userAccount.getAdnocRefID().isEmpty()){
				log.warn("Users Not Send becaus of missing data" + userAccount);
				continue;
			}
			
			if(!userAccount.getEnable()){
				log.warn("User Not Enabled" + userAccount.getContractNo());
				continue;
			}
			
			SendInv sendInv = new SendInv();
			sendInv.setCcbId(userAccount.getAdnocRefID());
			sendInv.setCity(city);
			sendInv.setCompany(company);
			sendInv.setContractNo(userAccount.getContractNo());
			sendInv.setFileName(pdfDirPath);
			sendInv.setCreationDate(time);
			sendInv.setStatus(pdfSendStatus); //"PENDING"
			sendInv.setPrefix(prefix);
			try {
				sendEmailService.store(sendInv);
				
				File sorc = new File(pdfDirPath + "/" + prefix + userAccount.getAdnocRefID() + ".pdf");
				File dest = new File( pdfBasePath+"/"+userAccount.getAdnocRefID()+".pdf");
				if(sorc.exists()){
					FileUtils.copyFile(sorc, dest);
				}
				

			} catch (DataAccessLayerException e) {
				log.error("ERROR in save line "+userAccount.getContractNo());
				//Notification.show("ERROR in reading Mapping File!", Type.ERROR_MESSAGE);
			} catch (Exception e) {
				log.error("ERROR in copyFile line "+userAccount.getContractNo());
			}
		}
//		return map;
	}

	private boolean validateFields() {
		
		
		Object city = sendMailForm.getCityField().getValue();
		Object company = sendMailForm.getCompanyField().getValue();
		String mapFilePath = sendMailForm.getBuildingField().getValue();
		String pdfDirPath = sendMailForm.getPdfDirPathField().getValue();

		if(city==null || city.toString().isEmpty()){
			Notification.show("ERROR", "Please Select City !", Type.ERROR_MESSAGE);
			return false;
		}
		
		if(company==null || company.toString().isEmpty()){
			Notification.show("ERROR", "Please Select Company Value !", Type.ERROR_MESSAGE);
			return false;
		}
		
		if(mapFilePath ==null || mapFilePath.isEmpty()){
			Notification.show("ERROR", "Please Fill Map File !", Type.ERROR_MESSAGE);
			return false;
		}
		
		if(pdfDirPath ==null || pdfDirPath.isEmpty()){
			Notification.show("ERROR", "Please PDF Dir!", Type.ERROR_MESSAGE);
			return false;
		}
		
		if(mapFilePath ==null || mapFilePath.isEmpty()){
			Notification.show("ERROR", "Builing in empty !", Type.ERROR_MESSAGE);
			return false;
		}

		File pdfDir = new File(pdfDirPath);
		if(!pdfDir.exists() || !pdfDir.isDirectory()){
			Notification.show("ERROR", "PDF Dir Is not correct !", Type.ERROR_MESSAGE);
			return false;
		}
		
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
