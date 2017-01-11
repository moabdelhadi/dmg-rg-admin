package com.dmg.admin.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dmg.admin.auth.SessionHandler;
import com.dmg.core.bean.BeansFactory;
import com.dmg.core.bean.MeterReading;
import com.dmg.core.bean.UserAccount;
import com.dmg.core.exception.DataAccessLayerException;
import com.dmg.core.persistence.FacadeFactory;

/**
 * 
 * @author Kareem Jabr
 * 
 */
public class MeterService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5307548729180567212L;
	/**
	 * 
	 */

	private static final Logger log = LoggerFactory.getLogger(MeterService.class);

	private static final String LIST_QUERY_ORDERED_BY_DATE = "SELECT u FROM MeterReadingDu u ORDER BY u.id DESC";
	/*private static final String FIND_USERS_TO_UPDATE = "SELECT u FROM UserAccountsDU u WHERE u.contractNo=:contractNo AND u.city=:city ";*/
	private static final String LIST_QUERY_ORDERED_BY_DATE_AUH = "SELECT u FROM MeterReadingAUH u ORDER BY u.id DESC";

	/*private static final String FIND_USERS_TO_UPDATE_AUH = "SELECT u FROM UserAccountsAUH u WHERE u.contractNo=:contractNo AND u.city=:city ";*/

	public List<MeterReading> lisReadings() throws DataAccessLayerException {

		String city = SessionHandler.get().getCity();

		String query = "";
		if ("DUBAI".equals(city)) {
			query = LIST_QUERY_ORDERED_BY_DATE;
		} else if ("ABUDHABI".equals(city)) {
			query = LIST_QUERY_ORDERED_BY_DATE_AUH;
		} else {
			log.error("Error in Get the City Value= : " + city);
			return null;
		}

		return FacadeFactory.getFacade().list(query, new HashMap<String, Object>());
	}

	public MeterReading getMeterReading(Long id) throws DataAccessLayerException {
		MeterReading meterReading = getMeterBean();
		return FacadeFactory.getFacade().find(meterReading.getClass(), id);
	}

	private MeterReading getMeterBean() {
		String city = SessionHandler.get().getCity();
		MeterReading userAccount = BeansFactory.getInstance().getMeterReading(city);
		return userAccount;
	}

	public void update(MeterReading meterReading) throws DataAccessLayerException {
		FacadeFactory.getFacade().store(meterReading);
	}

	//	public List<UserAccount> getModifiedUsers() throws DataAccessLayerException {
	//		Map<String, Object> parameters = new HashMap<String, Object>();
	//		parameters.put("status", 1);
	//		BeansFactory.getInstance().getUserAccount(city)
	//		return FacadeFactory.getFacade().list(UserAccount.class, parameters);
	//	}

	/*public UserAccount findUsersToUpdate(String contractNo, String city) throws DataAccessLayerException {

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("contractNo", contractNo);
		parameters.put("city", city);

		String query = "";

		if (city == "DUBAI") {
			query = FIND_USERS_TO_UPDATE;
		} else if (city == "ABUDHABI") {
			query = FIND_USERS_TO_UPDATE_AUH;
		} else {
			log.error("Error in Get the City Value= : " + city);
			return null;
		}

		return FacadeFactory.getFacade().find(query, parameters);
	}*/
}
