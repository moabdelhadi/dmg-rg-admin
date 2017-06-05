package com.dmg.admin.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dmg.admin.auth.SessionHandler;
import com.dmg.core.bean.BeansFactory;
import com.dmg.core.bean.SendInv;
import com.dmg.core.bean.UserAccount;
import com.dmg.core.exception.DataAccessLayerException;
import com.dmg.core.persistence.FacadeFactory;

/**
 * 
 * @author moabdelhadi
 *
 */
public class SendEmailService implements Serializable {


	private static final long serialVersionUID = -6755438274695617977L;

	private static final Logger log = LoggerFactory.getLogger(SendEmailService.class);

	private static final String LIST_QUERY_ORDERED_BY_DATE = "SELECT u FROM SendInv u ORDER BY u.creationDate DESC";

	public List<SendInv> listAll() throws DataAccessLayerException {

		String query =  LIST_QUERY_ORDERED_BY_DATE;
		return FacadeFactory.getFacade().list(query, new HashMap<String, Object>());
	
	}

	public void update(SendInv sendInv) throws DataAccessLayerException {
		FacadeFactory.getFacade().store(sendInv);
	}


}
