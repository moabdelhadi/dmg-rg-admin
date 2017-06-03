package com.dmg.admin.util;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailManager {
	
	private static final Logger log = LoggerFactory.getLogger(MailManager.class);

	private static final String SENDER_EMAIL = PropertiesManager.getInstance().getProperty("email.sender.email");
	private static final String SENDER_USER_NAME = PropertiesManager.getInstance().getProperty("email.semder.username");
	private static final String SENDER_PASSWORD = PropertiesManager.getInstance().getProperty("email.senderPassword");
	private static final String MAIL_SERVER_NAME = PropertiesManager.getInstance().getProperty("email.serverName");
	private static final String MAIL_DEUG = PropertiesManager.getInstance().getProperty("email.debug");
	private static final String MAIL_SERVER_PORT = PropertiesManager.getInstance().getProperty("email.server.port");
	
	
	private static final MailManager INSTANCE = new MailManager();

	private MailManager() {
	}

	public static MailManager getInstance() {
		return INSTANCE;
	}

	public void sendMail(String to, String subject, String body) {
		sendMail(to, subject, body, null);
	}
	
	public void sendMail(String to, String subject, String body, String fileName) {
		
		log.debug("MAIL_SERVER_PORT" +MAIL_SERVER_PORT);
		final String username = SENDER_EMAIL;
		final String password = SENDER_PASSWORD;
		Properties props = createEmailProperties();
		
		Session session = createSession(username, password, props);

		try {
			
			
			Multipart multipart = new MimeMultipart();
			
			if(fileName!=null && !fileName.isEmpty() ){
				MimeBodyPart messageBodyPart = new MimeBodyPart();
				//String filename = "/home/manisha/file.txt";
				DataSource source = new FileDataSource(fileName);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(fileName);
				multipart.addBodyPart(messageBodyPart);
			}
			
			
			
			BodyPart messageBodyPart2 = new MimeBodyPart();
			messageBodyPart2.setContent(body, "text/html");
			multipart.addBodyPart(messageBodyPart2);
			
			Message message = createMessage(to, subject, session, multipart);
			
			log.info("sent Html Body Message");

			Transport.send(message);

			log.info("Done");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	private Message createMessage(String to, String subject, Session session, Multipart multipart)
			throws MessagingException, AddressException {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(SENDER_USER_NAME));
		message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(SENDER_EMAIL));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		message.setSubject(subject);
//			message.setText(body);
		message.setSentDate(new Date());
		message.setContent(multipart);
		return message;
	}

	private Session createSession(final String username, final String password, Properties props) {
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		return session;
	}

	private Properties createEmailProperties() {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", MAIL_SERVER_NAME);
		props.put("mail.debug", MAIL_DEUG);
		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.socketFactory.port", MAIL_SERVER_PORT);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		
		props.put("mail.smtp.port", MAIL_SERVER_PORT);
		props.put("mail.smtp.socketFactory.fallback", "false");
		return props;
	}

	public static void main(String[] args) {
		MailManager instance2 = getInstance();
		instance2.sendMail("mome9@hotmail.com", "subject text", "text body text body text body text body text body text body text  ");
	}
}