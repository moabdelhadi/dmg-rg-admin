package com.dmg.admin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dmg.core.bean.UserAccount;

public class MailTemplatesGenerateUtil {
	
	private static final Logger log = LoggerFactory.getLogger(MailTemplatesGenerateUtil.class);

//	private static final String SENDER_EMAIL = PropertiesManager.getInstance().getProperty("email.sender.email");
//	private static final String SENDER_USER_NAME = PropertiesManager.getInstance().getProperty("email.semder.username");
//	private static final String SENDER_PASSWORD = PropertiesManager.getInstance().getProperty("email.senderPassword");
//	private static final String MAIL_SERVER_NAME = PropertiesManager.getInstance().getProperty("email.serverName");
//	private static final String MAIL_DEUG = PropertiesManager.getInstance().getProperty("email.debug");
//	private static final String MAIL_SERVER_PORT = PropertiesManager.getInstance().getProperty("email.server.port");
	
	
	private static final MailTemplatesGenerateUtil INSTANCE = new MailTemplatesGenerateUtil();

	private MailTemplatesGenerateUtil() {
	}

	public static MailTemplatesGenerateUtil getInstance() {
		return INSTANCE;
	}
	
	
	public String createEmailTemplate(String templateField, String messageText, UserAccount userAccount, Boolean nameCheck, Boolean contractNoCheck, Boolean dueDateCheck){
		
		return "";
	}

}