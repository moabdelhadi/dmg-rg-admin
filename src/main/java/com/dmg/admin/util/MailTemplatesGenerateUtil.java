package com.dmg.admin.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dmg.admin.service.UserAccountService;
import com.dmg.core.bean.UserAccount;
import com.dmg.core.exception.DataAccessLayerException;

public class MailTemplatesGenerateUtil {
	
	private static final Logger log = LoggerFactory.getLogger(MailTemplatesGenerateUtil.class);

//	private static final String SENDER_EMAIL = PropertiesManager.getInstance().getProperty("email.sender.email");
//	private static final String SENDER_USER_NAME = PropertiesManager.getInstance().getProperty("email.semder.username");
//	private static final String SENDER_PASSWORD = PropertiesManager.getInstance().getProperty("email.senderPassword");
//	private static final String MAIL_SERVER_NAME = PropertiesManager.getInstance().getProperty("email.serverName");
//	private static final String MAIL_DEUG = PropertiesManager.getInstance().getProperty("email.debug");
//	private static final String MAIL_SERVER_PORT = PropertiesManager.getInstance().getProperty("email.server.port");
	
	private static Map<String, String> templatesMap; 
	private static final MailTemplatesGenerateUtil INSTANCE = new MailTemplatesGenerateUtil();
	

	private MailTemplatesGenerateUtil() {
		templatesMap= new HashMap<String, String>();
		templatesMap.put("firstReminder", getFirstTemp());
		templatesMap.put("secondReminder", getsecondTemp() );
		templatesMap.put("thirdReminder", getThierdTemp());
		templatesMap.put("lastReminder", getLastTemp());
	}

	private String getFirstTemp() {
		return "<p>First</p>";
	}

	private String getsecondTemp() {
		return "<p>Second</p>";
	}
	private String getThierdTemp() {
		return "<p>Thisrd</p>";
	}
	private String getLastTemp() {
		return "<p>Last</p>";
	}

	
	
	public static MailTemplatesGenerateUtil getInstance() {
		return INSTANCE;
	}
	
	
	public String createEmailTemplate(String templateType, String messageText, String title,  UserAccount userAccount, Boolean nameCheck, Boolean contractNoCheck, Boolean passwordCheck){
		
		StringBuilder builder = new StringBuilder();
		
		if(templateType.equals("free")){
			builder.append(appendFreeMessage(messageText));
		}else{
			builder.append(templatesMap.get(templateType));
		}
		
		String data=appendData(userAccount, nameCheck, contractNoCheck, passwordCheck);
		builder.append(data);
		builder.append(appendFooter());
		return builder.toString();
	
	}
	
	
	private String appendData(UserAccount userAccount,  Boolean nameCheck, Boolean contractNoCheck, Boolean password) {

		StringBuilder builder = new StringBuilder();
		if(nameCheck){
			builder.append("<p>Name:");
			builder.append(userAccount.getName());
			builder.append("</p>");
		}
		
		if(contractNoCheck){
			builder.append("<p>Account No:");
			builder.append(userAccount.getContractNo());
			builder.append("</p>");
		}
		
		if(password){
			builder.append(passwordResetLink(userAccount));
		}
		
		return builder.toString();
	}
	
	
	private String passwordResetLink(UserAccount account){
		
		String hashKey = SHAEncrypt.encryptKey(account.getCity() + "_" + account.getContractNo() + "_" + System.currentTimeMillis());
		account.setPassResetKey(hashKey);
		
		UserAccountService userAccountService = new UserAccountService();
		try {
			userAccountService.update(account);
		} catch (DataAccessLayerException e) {
			log.error("Error in save account");
			return "";
		}
		
		String link="https://pay.royalgas.com/dmg-rg-client-v2/ForgotPassword?pass_key=" + hashKey + "_" + account.getCity() + "_" + account.getContractNo();
		String resetPath="<p>Password Link: <a  href='"+link+"' >"+link+"</a><p>";
		return resetPath;
	}

	private String appendFreeMessage(String messageText) {
		
		if(messageText==null){
			return "";
		}
		String tmp = messageText.trim();
		tmp = tmp.replace("\n", "</p><p>");
		tmp = tmp.replace("\r", "</p><p>");
		return "<p>"+tmp+"</p>";
	
	}

	public String createSendAdnocInvMessageBody(String prefix, UserAccount user) {
		StringBuilder builder = new StringBuilder();
		builder.append("<p>Your bill for ");
		builder.append(prefix);
		builder.append(" is ready. To view and save your bill, please double click on the attachment.</p><p>You can make your gas bill payment through the following</p><p>1)      By website https://www.royalgas.com/online-payment</p><p>2)      By Mobile Application Both android and App store</p><p>3)      By collector - We will be sending a SMS in regards to the schedule of the gas bill payment collection. Our collector will make a door-to-door collection on the scheduled date. Any request for bill collection after the scheduled date is subject AED 30 fee.</p><p>Please use the below information to register online account</p><p> Royal Gas Account Number     - ");
		builder.append(user.getContractNo());
		builder.append("</p><p>  Building Number                     - ");
		builder.append(user.getBuildingNumber());
		builder.append("</p><p>  Apartment Number                  - ");
		builder.append(user.getAppartmentNumber());
		builder.append(appendFooter());
		return builder.toString();
	}
	
	public String appendFooter(){
		
		StringBuilder builder = new StringBuilder();
		builder.append("</p><br>");
		builder.append("<p>Best Regards,</p>");
		builder.append("<p>Royal Development for Gas Works & Cont.</p>");
		builder.append("<p>Tel. # : +971-2-6323236</p>");
		builder.append("<p>e-mail :support.ad@royalgas.ae</p>");
		builder.append("<p>website :www.royalgas.com</p>");
		return builder.toString();
	}

}