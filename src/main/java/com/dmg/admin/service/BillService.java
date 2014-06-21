package com.dmg.admin.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.dmg.admin.bean.Bill;
import com.dmg.core.exception.DataAccessLayerException;
import com.dmg.core.persistence.FacadeFactory;

/**
 * 
 * @author Kareem Jabr
 * 
 */
public class BillService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 333126056493315802L;

	private static final String LIST_QUERY_ORDERED_BY_DATE = "SELECT b FROM Bill b ORDER BY b.creationDate DESC";

	public List<Bill> listBills() throws DataAccessLayerException {
		return FacadeFactory.getFacade().list(LIST_QUERY_ORDERED_BY_DATE, new HashMap<String, Object>());
	}

	public Bill getBill(long id) throws DataAccessLayerException {

		return FacadeFactory.getFacade().find(Bill.class, id);
	}

}
