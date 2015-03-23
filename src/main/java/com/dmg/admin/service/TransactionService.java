package com.dmg.admin.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

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

	private static final String LIST_QUERY_ORDERED_BY_DATE = "SELECT t FROM Transaction t ORDER BY t.creationDate DESC";

	public List<Transaction> listTransactions() throws DataAccessLayerException {
		return FacadeFactory.getFacade().list(LIST_QUERY_ORDERED_BY_DATE, new HashMap<String, Object>());
	}

	public void update(Transaction transaction) throws DataAccessLayerException {
		FacadeFactory.getFacade().store(transaction);
	}

	public Transaction getTransaction(Long id) throws DataAccessLayerException {
		return FacadeFactory.getFacade().find(Transaction.class, id);
	}

}
