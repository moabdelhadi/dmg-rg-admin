package com.dmg.admin.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dmg.admin.service.BillService;
import com.dmg.admin.service.UserAccountService;
import com.dmg.core.bean.Bill;
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
	
//	private static Map<String, String> templatesMap; 
	private static Map<String, String> regionsMap ;
	private static final MailTemplatesGenerateUtil INSTANCE = new MailTemplatesGenerateUtil();
	

	private MailTemplatesGenerateUtil() {
//		templatesMap= new HashMap<String, String>();
//		templatesMap.put("firstReminder", getFirstTemp());
//		templatesMap.put("secondReminder", getsecondTemp() );
//		templatesMap.put("thirdReminder", getThierdTemp());
//		templatesMap.put("lastReminder", getLastTemp());
		
		regionsMap = new HashMap<String, String>();
		regionsMap.put("DUBAI", "Dubai & Northern Emirates");
		regionsMap.put("ABUDHABI", "Abu dhabi, Alain & Western Region");
	}

	private String getFirstTemp(UserAccount userAccount) {
		StringBuilder builder = genericRemainder(userAccount, "First Payment Reminder", "التذكير الأول  للسداد");
		return builder.toString();
	}

	private String getsecondTemp(UserAccount userAccount) {
		
		StringBuilder builder = genericRemainder(userAccount, "Second Payment Reminder", "التذكير الثاني للسداد");
		return builder.toString();
	}

	private StringBuilder genericRemainder(UserAccount userAccount, String en, String ar) {
		BillService billService = new BillService();
		Bill latestBill = billService.getLatestBill(userAccount.getContractNo(), userAccount.getCity(), userAccount.getCompany());

		Date timeNow = Calendar.getInstance().getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		StringBuilder builder = new StringBuilder();
		builder.append("<p>Date:");
		builder.append(df.format(timeNow));
		builder.append("</p>");
		
		builder.append("<p>Ref. No.: ");
		builder.append(userAccount.getContractNo());
		builder.append("</p>");
		
		builder.append("<p><strong>");
		builder.append(en);
		builder.append("</strong></p>");
		builder.append("<p>"+userAccount.getName()+"</p>");
		
		builder.append("<p>Account ID.:");
		builder.append(userAccount.getContractNo());
		builder.append("</p>");
		builder.append("<p>Dear Customer,</p>");
		builder.append("<p>This is to inform you that your ADNOC Distribution bill due date is nearing. An amount of AED ");
		builder.append(userAccount.getBalance());
		builder.append(" is due on ");
		if(latestBill!=null){
		builder.append(latestBill.getBillDate());
		}
		builder.append("</p>");
		builder.append("<p>Kindly arrange for the payment of the amount in order to avoid any late payment charges and/or disconnection of Gas supply to your premise.</p>");
		builder.append("<p>Failing to pay such amount by the due date will result in charging your account with late payment charges and/or disconnection of Gas supply to your premise following the due date.</p>");
		builder.append("<p>Please ignore this message if the amount due has already been paid.</p>");
		builder.append("<p>Regards,</p>");
		builder.append("<p>Natural Gas Sales and Customer Service Department.</p>");
		builder.append("<p>ADNOC Distribution</p>");
		builder.append("<br><br>");
		
//		builder.append("<p align='right' >التاريخ:");
//		builder.append(df.format(timeNow));
//		builder.append("</p>");
//		
//		builder.append("<p align='right' >Ref. No.: ");
//		builder.append(userAccount.getContractNo());
//		builder.append("</p>");
//		
//		builder.append("<p align='right' ><strong>");
//		builder.append(ar);
//		builder.append("</strong></p>");
//		builder.append("<p align='right' >"+userAccount.getName()+"</p>");
//		
//		builder.append("<p align='right' >رقم الحساب:");
//		builder.append(userAccount.getContractNo());
//		builder.append("</p>");
//		builder.append("<p align='right' >عزيزي العميل،</p>");
//		builder.append("<p align='right' >نود ابلاغكم باقتراب موعد استحقاق فاتورة أدنوك للتوزيع الخاصة بكم. إن مبلغ");
//		builder.append(userAccount.getBalance());
//		builder.append(" درهم مستحق السداد في تاريخ ");
//		if(latestBill!=null){
//			builder.append(latestBill.getBillDate());
//			}
//		builder.append("</p>");
//		builder.append("<p align='right' >يرجى القيام بسداد اجمالي المبلغ المستحق لتفادي أي رسوم تأخير و/أو فصل توريد الغاز إلى العقار الخاص بكم.</p>");
//		builder.append("<p align='right' >إن عدم سداد هذا المبلغ بتاريخ الاستحقاق سيؤدي إلى فرض رسوم تأخير على حسابكم و/أو فصل توريد الغاز إلى العقار الخاص بكم بعد تاريخ الاستحقاق.</p>");
//		builder.append("<p align='right' >يرجى تجاهل هذه الرسالة إذا كان المبلغ الإجمالي المستحق قد تم سداده.</p>");
//		builder.append("<p align='right' >وتفضلوا بقبول فائق الاحترام والتقدير،</p>");
//		builder.append("<p align='right' >إدارة مبيعات  الغاز الطبيعي   وخدمة العملاء</p>");
//		builder.append("<p align='right' >أدنوك للتوزيع</p>");
//		builder.append("<br><br>");
//		builder.append(appendFooter());
		return builder;
	}

//	private String getDisconniction(UserAccount userAccount) {
//		BillService billService = new BillService();
//		Bill latestBill = billService.getLatestBill(userAccount.getContractNo(), userAccount.getCity(), userAccount.getCompany());
//
//		Date timeNow = Calendar.getInstance().getTime();
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		StringBuilder builder = new StringBuilder();
//		builder.append("<p>Date:");
//		builder.append(df.format(timeNow));
//		builder.append("</p>");
//		
//		builder.append("<p>Ref. No.: ");
//		builder.append(userAccount.getContractNo());
//		builder.append("</p>");
//		
//		builder.append("<p><strong>");
//		builder.append("Gas Supply Dissconnection Notice");
//		builder.append("</strong></p>");
//		builder.append("<p>"+userAccount.getName()+"</p>");
//		
//		builder.append("<p>Account ID.:");
//		builder.append(userAccount.getContractNo());
//		builder.append("</p>");
//		builder.append("<p>Dear Customer,</p>");
//		builder.append("<p>This is to inform you that your ADNOC Distribution bill due date is nearing. An amount of AED ");
//		builder.append(userAccount.getBalance());
//		builder.append(" is due on ");
//		builder.append(latestBill.getBillDate());
//		builder.append("</p>");
//		builder.append("<p>Kindly arrange for the payment of the amount in order to avoid any late payment charges and/or disconnection of Gas supply to your premise.</p>");
//		builder.append("<p>Failing to pay such amount by the due date will result in charging your account with late payment charges and/or disconnection of Gas supply to your premise following the due date.</p>");
//		builder.append("<p>Please ignore this message if the amount due has already been paid.</p>");
//		builder.append("<p>Regards,</p>");
//		builder.append("<p>Natural Gas Sales and Customer Service Department.</p>");
//		builder.append("<p>ADNOC Distribution</p>");
//		builder.append("<br><br>");
//		
//		builder.append("<p align='right' >التاريخ:");
//		builder.append(df.format(timeNow));
//		builder.append("</p>");
//		
//		builder.append("<p align='right' >Ref. No.: ");
//		builder.append(userAccount.getContractNo());
//		builder.append("</p>");
//		
//		builder.append("<p align='right' ><strong>");
//		builder.append(ar);
//		builder.append("</strong></p>");
//		builder.append("<p align='right' >"+userAccount.getName()+"</p>");
//		
//		builder.append("<p align='right' >رقم الحساب:");
//		builder.append(userAccount.getContractNo());
//		builder.append("</p>");
//		builder.append("<p align='right' >عزيزي العميل،</p>");
//		builder.append("<p align='right' >نود ابلاغكم باقتراب موعد استحقاق فاتورة أدنوك للتوزيع الخاصة بكم. إن مبلغ");
//		builder.append(userAccount.getBalance());
//		builder.append(" درهم مستحق السداد في تاريخ ");
//		builder.append(latestBill.getBillDate());
//		builder.append("</p>");
//		builder.append("<p align='right' >يرجى القيام بسداد اجمالي المبلغ المستحق لتفادي أي رسوم تأخير و/أو فصل توريد الغاز إلى العقار الخاص بكم.</p>");
//		builder.append("<p align='right' >إن عدم سداد هذا المبلغ بتاريخ الاستحقاق سيؤدي إلى فرض رسوم تأخير على حسابكم و/أو فصل توريد الغاز إلى العقار الخاص بكم بعد تاريخ الاستحقاق.</p>");
//		builder.append("<p align='right' >يرجى تجاهل هذه الرسالة إذا كان المبلغ الإجمالي المستحق قد تم سداده.</p>");
//		builder.append("<p align='right' >وتفضلوا بقبول فائق الاحترام والتقدير،</p>");
//		builder.append("<p align='right' >إدارة مبيعات  الغاز الطبيعي   وخدمة العملاء</p>");
//		builder.append("<p align='right' >أدنوك للتوزيع</p>");
//		builder.append("<br><br>");
//		builder.append(appendFooter());
//		return builder;
//	}
	
	private String getLastTemp(UserAccount userAccount) {
		return "<p>Last</p>";
	}
	
	public String getNewRegisterTemp(UserAccount userAccount) {
		
		StringBuilder builder = new StringBuilder();
		builder.append("<p>Dear Resident,</p>");
		builder.append("<p>This is to inform you that, from (1st of August 2018) onwards under ADNOC Distribution, Royal Gas are responsible for, Metering, billing & collection services as well as activities related to connection/reconnection and disconnection of the gas supply.</p>");
		builder.append("<p>Your Outstanding Balance as of today is - ");
		builder.append(userAccount.getBalance().toString());
		builder.append(" AED (please use the online option or pay at our office - you can find our location on Royal Gas website). </p>");
		builder.append("<p>Note: your previous paid Deposit and any previous payments done before 1st of August will be automatically updated on your account statement.</p>");
		builder.append("<p>Q3 Invoice will also receive at the earliest to your registered Email & Mobile Number.</p>");
		builder.append("<p></p>");
		builder.append("<p>The online system is also active for ADNOC customers, customers can login to online account for paying the outstanding balance and Self meter Reading.</p>");
		builder.append("<p>Please use the below option to login to your online account</p>");
		builder.append("<p>1) By website https://www.royalgas.com/online-payment</p>");
		builder.append("<p>2) By Mobile Application Both android and App store (Search for royalgas) – Use Account management option</p>");
		builder.append("<p></p>");
		builder.append("<p><b>Online Login Information</b></p>");
		builder.append("<p></p>");
		String data=appendData(userAccount, false, true, true, true, true, false);
		builder.append(data);
		builder.append("<p>Note: the above Password link has been automatically generated for you to ease your online access; meanwhile we urge you to update your personal password accordingly.</p>");
		builder.append("<p></p>");
		builder.append("<p>And also you can send your self-gas meter reading with attached gas meter photo after login your online account (it’s available in both Web & Mobile applications).</p>");
		builder.append("<p>https://www.royalgas.com/meter-reading-form</p>");
		builder.append("<p></p>");
		builder.append("<p>Assuring you our best services.</p>");
		return builder.toString();
	}

	
	
	public static MailTemplatesGenerateUtil getInstance() {
		return INSTANCE;
	}
	
	
	public String createEmailTemplate(String templateType, String messageText, String title,  UserAccount userAccount, Boolean nameCheck, Boolean contractNoCheck, Boolean passwordCheck){
		
		
		String createEmailTemplate = createEmailTemplate(templateType, messageText, title, userAccount, nameCheck, contractNoCheck, passwordCheck, false, false, false);
		return createEmailTemplate;
		

	}
	
	
	public String createEmailTemplate(String templateType, String messageText, String title,  UserAccount userAccount, Boolean nameCheck, Boolean contractNoCheck, Boolean passwordCheck, Boolean regionCheck, Boolean apartmentNoCheck, Boolean balanceCheck ){
		
		StringBuilder builder = new StringBuilder();
		
		if(templateType.equals("free")){
			builder.append(appendFreeMessage(messageText));
		}else if(templateType.equals("firstReminder")){
			builder.append(getFirstTemp(userAccount));
		}else if(templateType.equals("secondReminder")){
			builder.append(getsecondTemp(userAccount));
		}else if(templateType.equals("lastReminder")){
			builder.append(getLastTemp(userAccount));
		}
		
		String data=appendData(userAccount, nameCheck, contractNoCheck, passwordCheck, regionCheck, apartmentNoCheck, balanceCheck);
		builder.append(data);
		builder.append(appendFooter());
		return builder.toString();
	
	}
	
	
	
	private String appendData(UserAccount userAccount, Boolean nameCheck, Boolean contractNoCheck, Boolean password, Boolean regionCheck, Boolean apartmentNoCheck, Boolean balanceCheck) {

		StringBuilder builder = new StringBuilder();
		if(regionCheck){
			builder.append("<p><b>Region:</b>");
			builder.append(regionsMap.get(userAccount.getCity()));
			builder.append("</p>");
		}
		
		if(nameCheck){
			builder.append("<p><b>Name:</b>");
			builder.append(userAccount.getName());
			builder.append("</p>");
		}
		
		if(contractNoCheck){
			builder.append("<p><b>Account No:</b>");
			builder.append(userAccount.getContractNo());
			builder.append("</p>");
		}
		
		if(apartmentNoCheck){
			builder.append("<p><b>Apartment Number:</b>");
			builder.append(userAccount.getAppartmentNumber());
			builder.append("</p>");
		}
		
		if(balanceCheck){
			builder.append("<p><b>Outstanding Balance:</b>");
			builder.append(userAccount.getBalance());
			builder.append("</p>");
		}
		
		if(password){
			builder.append(passwordResetLink(userAccount));
		}
		
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
		String resetPath="<p><b>Password Link: </b><a  href='"+link+"' >"+link+"</a></p>";
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
		builder.append("</p>");
		builder.append(appendFooter());
		return builder.toString();
	}
	
	public String appendFooter(){
		
		StringBuilder builder = new StringBuilder();
		builder.append("<br>");
		builder.append("<p>Best Regards,</p>");
		builder.append("<p>Royal Development for Gas Works & Cont.</p>");
		builder.append("<p>Tel. # : +971-2-6323236</p>");
		builder.append("<p>e-mail :support.ad@royalgas.ae</p>");
		builder.append("<p>website :www.royalgas.com</p>");
		return builder.toString();
	}

}