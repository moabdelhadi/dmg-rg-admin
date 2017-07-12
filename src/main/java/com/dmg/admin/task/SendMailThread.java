package com.dmg.admin.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dmg.admin.service.SendEmailService;
import com.dmg.admin.service.UserAccountService;
import com.dmg.admin.util.MailManager;
import com.dmg.core.bean.BeansFactory;
import com.dmg.core.bean.Constants;
import com.dmg.core.bean.SendInv;
import com.dmg.core.bean.UserAccount;
import com.dmg.core.exception.DataAccessLayerException;
import com.dmg.core.persistence.FacadeFactory;

public class SendMailThread implements Runnable {

	private static Logger log = LoggerFactory.getLogger(SendMailThread.class);

	@Override
	public void run() {
		SendEmailService service = new SendEmailService();
		while (true) {
			SendInv firstItem = null;
			log.info("START THREAD LOOP");
			try {
				firstItem = service.getFirstItem();
				if (firstItem == null) {
					Thread.sleep(36000);
					continue;
				}
				UserAccount user = BeansFactory.getInstance().getUserAccount(firstItem.getCity());
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put(Constants.USER_ACCOUNT_ID, firstItem.getContractNo());
				parameters.put(Constants.USER_CITY, firstItem.getCity());
				parameters.put(Constants.USER_COMPANY, firstItem.getCompany());
				List<? extends UserAccount> list = FacadeFactory.getFacade().list(user.getClass(), parameters);
				if (list != null && !list.isEmpty()) {
					String fileName = firstItem.getFileName() + "/" + firstItem.getPrefix() + firstItem.getCcbId()
							+ ".pdf";
					UserAccount userAccountItem = list.get(0);
					String email = userAccountItem.getEmail();
					if(email==null || email.isEmpty()){
						log.info("NO EMAIL");
						firstItem.setStatus("ERROR");
						firstItem.setMessage("NO EMAIL");
						service.store(firstItem);
						continue;
					}
<<<<<<< HEAD
					log.info("BEFOR SEND EMAIL");
					String msgB = getMessageBody(firstItem.getPrefix(), userAccountItem);
					MailManager.getInstance().sendMail(email, "Royal Gas bill for "+firstItem.getPrefix(),	msgB , fileName);
					log.info("AFTER SEND EMAIL");
=======
					String msgB = getMessageBody(firstItem.getPrefix(), userAccountItem);
					MailManager.getInstance().sendMail(email, "Royal Gas bill for "+firstItem.getPrefix(),	msgB , fileName);
>>>>>>> branch 'master' of https://github.com/moabdelhadi/dmg-rg-admin.git
					firstItem.setStatus("SENT");
					service.store(firstItem);
					log.info("AFTER SAVE");
				}else{
					firstItem.setStatus("ERROR");
					firstItem.setMessage("NO CONTRACT");
					service.store(firstItem);
					continue;
				}

				Thread.sleep(36000);
			} catch (DataAccessLayerException e) {
				log.error("Error in get Data", e);
				if (firstItem != null) {
					firstItem.setStatus("ERROR");
					firstItem.setMessage(e.getMessage());
					try {
						service.store(firstItem);
					} catch (DataAccessLayerException e1) {
						log.error("Error in get Data", e);
					}
				}

			} catch (InterruptedException e) {
				log.error("Error in sleep", e);
				if (firstItem != null) {
					firstItem.setStatus("ERROR");
					firstItem.setMessage(e.getMessage());
					try {
						service.store(firstItem);
					} catch (DataAccessLayerException e1) {
						log.error("Error in get Data", e);
					}
				}
			} catch (Exception e) {
				log.error("Error in sleep", e);
				if (firstItem != null) {
					firstItem.setStatus("ERROR");
					firstItem.setMessage(e.getMessage());
					try {
						service.store(firstItem);
					} catch (DataAccessLayerException e1) {
						log.error("Error in get Data", e);
					}
				}
			}

		}

	}

	private String getMessageBody(String prefix, UserAccount user) {
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
		return builder.toString();
	}

}