package com.dmg.admin.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dmg.admin.auth.SessionHandler;
import com.dmg.core.bean.BeansFactory;
import com.dmg.core.bean.Transaction;
import com.dmg.core.exception.DataAccessLayerException;
import com.dmg.core.persistence.FacadeFactory;

/**
 * 
 * @author Kareem Jabr
 * 
 */
public class TransactionService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7647308411954100054L;
	
	
	/**
	 * 
	 */
	private static final Logger log = LoggerFactory.getLogger(TransactionService.class);

	private static final String LIST_QUERY_ORDERED_BY_DATE_DU = "SELECT t FROM TransactionDu t ORDER BY t.creationDate DESC";
	private static final String LIST_QUERY_ORDERED_BY_DATE_AUH = "SELECT t FROM TransactionAUH t ORDER BY t.creationDate DESC";

	public List<Transaction> listTransactions() throws DataAccessLayerException {
		
		String city = SessionHandler.get().getCity();
		String query = "";
		if ("DUBAI".equals(city)) {
			query = LIST_QUERY_ORDERED_BY_DATE_DU;
		} else if ("ABUDHABI".equals(city)) {
			query = LIST_QUERY_ORDERED_BY_DATE_AUH;
		} else {
			log.error("Error in Get the City Value= : " + city);
			return null;
		}

		
		return FacadeFactory.getFacade().list(query, new HashMap<String, Object>());
	}

	public void update(Transaction transaction) throws DataAccessLayerException {
		FacadeFactory.getFacade().store(transaction);
	}

	public Transaction getTransaction(Long id) throws DataAccessLayerException {
		
		String city = SessionHandler.get().getCity();
		Transaction txn = BeansFactory.getInstance().getTxn(city);
		return FacadeFactory.getFacade().find(txn.getClass(), id);

	}

}
