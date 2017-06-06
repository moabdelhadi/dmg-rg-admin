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
					String email = list.get(0).getEmail();
					if(email==null || email.isEmpty()){
						firstItem.setStatus("ERROR");
						firstItem.setMessage("NO EMAIL");
						service.store(firstItem);
						continue;
					}
					MailManager.getInstance().sendMail(email, "Bill for your Royal Gas Account no. "+firstItem.getContractNo(),
							"Your bill for "+firstItem.getContractNo() +" is ready. To view and save your bill, please double click on the attachment." , fileName);
					firstItem.setStatus("SENT");
					service.store(firstItem);
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

}
