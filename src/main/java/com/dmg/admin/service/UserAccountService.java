package com.dmg.admin.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dmg.admin.auth.SessionHandler;
import com.dmg.core.bean.BeansFactory;
import com.dmg.core.bean.UserAccount;
import com.dmg.core.exception.DataAccessLayerException;
import com.dmg.core.persistence.FacadeFactory;

/**
 * 
 * @author Kareem Jabr
 * 
 */
public class UserAccountService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5307548729180567212L;
	/**
	 * 
	 */

	private static final Logger log = LoggerFactory.getLogger(UserAccountService.class);

	private static final String LIST_QUERY_ORDERED_BY_DATE = "SELECT u FROM UserAccountsDU u ORDER BY u.creationDate DESC";
	/*private static final String FIND_USERS_TO_UPDATE = "SELECT u FROM UserAccountsDU u WHERE u.contractNo=:contractNo AND u.city=:city ";*/
	private static final String LIST_QUERY_ORDERED_BY_DATE_AUH = "SELECT u FROM UserAccountsAUH u ORDER BY u.creationDate DESC";

	/*private static final String FIND_USERS_TO_UPDATE_AUH = "SELECT u FROM UserAccountsAUH u WHERE u.contractNo=:contractNo AND u.city=:city ";*/

	public List<UserAccount> lisUsers() throws DataAccessLayerException {

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

	public UserAccount getUserAcount(Long id) throws DataAccessLayerException {
		UserAccount userAccount = getUserBean();
		return FacadeFactory.getFacade().find(userAccount.getClass(), id);
	}

	private UserAccount getUserBean() {
		String city = SessionHandler.get().getCity();
		UserAccount userAccount = BeansFactory.getInstance().getUserAccount(city);
		return userAccount;
	}

	public void update(UserAccount userAccount) throws DataAccessLayerException {
		FacadeFactory.getFacade().store(userAccount);
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
