package com.dmg.admin.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
	private static final String LIST_QUERY = "SELECT b FROM Bill b ";

	public List<Bill> listBills() throws DataAccessLayerException {
		return FacadeFactory.getFacade().list(LIST_QUERY_ORDERED_BY_DATE, new HashMap<String, Object>());
	}

	public Bill getBill(long id) throws DataAccessLayerException {

		return FacadeFactory.getFacade().find(Bill.class, id);
	}

	public void storeBill(Bill bill) throws DataAccessLayerException {
		FacadeFactory.getFacade().store(bill);
	}

	public Bill findBill(Bill bill) throws DataAccessLayerException {
		String query = LIST_QUERY + "WHERE b.company=:company AND b.yearCode=:yearCode AND b.docNo=:docNo AND b.docType=:docType AND b.srNo=:srNo";
		Map<String, Object> parameters = new LinkedHashMap<String, Object>();
		parameters.put("company", bill.getCompany());
		parameters.put("yearCode", bill.getYearCode());
		parameters.put("docNo", bill.getDocNo());
		parameters.put("docType", bill.getDocType());
		parameters.put("srNo", bill.getSrNo());

		return FacadeFactory.getFacade().find(query, parameters);

	}

}
