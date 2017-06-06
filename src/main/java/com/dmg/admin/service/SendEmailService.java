package com.dmg.admin.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dmg.admin.auth.SessionHandler;
import com.dmg.core.bean.AbstractPojo;
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

	private static final String LIST_QUERY_ORDERED_BY_DATE = "SELECT u FROM SendInv u ORDER BY u.id ";
	private static final String LIST_PENDING_QUERY_ORDERED_BY_id = "SELECT u FROM SendInv u where u.status='PENDING'  limit 1 ORDER BY u.id ";

	public List<SendInv> listAll() throws DataAccessLayerException {

		String query =  LIST_QUERY_ORDERED_BY_DATE;
		return FacadeFactory.getFacade().list(query, new HashMap<String, Object>());
	
	}
	
	public SendInv getFirstItem() throws DataAccessLayerException{
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("status", "PENDING");
		List<SendInv> list = FacadeFactory.getFacade().list(SendInv.class, parameters, "id", true, 1);
				
		if(list==null || list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	public void store(SendInv sendInv) throws DataAccessLayerException {
		FacadeFactory.getFacade().store(sendInv);
	}


}
