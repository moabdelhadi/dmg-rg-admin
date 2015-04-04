package com.dmg.admin.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dmg.admin.auth.SessionHandler;
import com.dmg.core.bean.BeansFactory;
import com.dmg.core.bean.Bill;
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

	private static final String LIST_QUERY_ORDERED_BY_DATE_DU = "SELECT b FROM BillDu b ORDER BY b.creationDate DESC";
	private static final String LIST_QUERY_ORDERED_BY_DATE_AUH = "SELECT b FROM BillAUH b ORDER BY b.creationDate DESC";
	private static final String LIST_QUERY_DU = "SELECT b FROM BillDu b ";
	private static final String LIST_QUERY_AUH = "SELECT b FROM BillAUH b ";

	private static final Logger log = LoggerFactory.getLogger(BillService.class);

	public List<Bill> listBills() throws DataAccessLayerException {

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

	public Bill getBill(long id) throws DataAccessLayerException {

		String city = SessionHandler.get().getCity();
		Bill bill = BeansFactory.getInstance().getBill(city);
		return FacadeFactory.getFacade().find(bill.getClass(), id);
	}

	public void storeBill(Bill bill) throws DataAccessLayerException {
		FacadeFactory.getFacade().store(bill);
	}

	public Bill findBill(Bill bill) throws DataAccessLayerException {

		String city = SessionHandler.get().getCity();
		String query = "";
		if (city == "DUBAI") {
			query = LIST_QUERY_DU;
		} else if (city == "ABUDHABI") {
			query = LIST_QUERY_AUH;
		} else {
			log.error("Error , findBill,  in Get the City Value= : " + city);
			return null;
		}

		query += "WHERE b.company=:company AND b.yearCode=:yearCode AND b.docNo=:docNo AND b.docType=:docType AND b.srNo=:srNo";
		Map<String, Object> parameters = new LinkedHashMap<String, Object>();
		parameters.put("city", bill.getCity());
		parameters.put("yearCode", bill.getYearCode());
		parameters.put("docNo", bill.getDocNo());
		parameters.put("docType", bill.getDocType());
		parameters.put("serialNo", bill.getSerialNo());

		return FacadeFactory.getFacade().find(query, parameters);

	}

}
