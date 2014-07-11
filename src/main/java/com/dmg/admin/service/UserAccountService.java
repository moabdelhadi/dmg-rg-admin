package com.dmg.admin.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dmg.admin.bean.UserAccount;
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

	private static final String LIST_QUERY_ORDERED_BY_DATE = "SELECT u FROM UserAccount u ORDER BY u.creationDate DESC";

	private static final String FIND_USERS_TO_UPDATE = "SELECT u FROM UserAccount u WHERE u.contractNo=:contractNo AND u.city=:city ";

	public List<UserAccount> lisUsers() throws DataAccessLayerException {
		return FacadeFactory.getFacade().list(LIST_QUERY_ORDERED_BY_DATE, new HashMap<String, Object>());
	}

	public UserAccount getUserAcount(Long id) throws DataAccessLayerException {
		return FacadeFactory.getFacade().find(UserAccount.class, id);
	}

	public void update(UserAccount userAccount) throws DataAccessLayerException {
		FacadeFactory.getFacade().store(userAccount);
	}

	public List<UserAccount> getModifiedUsers() throws DataAccessLayerException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("status", 1);
		return FacadeFactory.getFacade().list(UserAccount.class, parameters);
	}

	public UserAccount findUsersToUpdate(String contractNo, String city) throws DataAccessLayerException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("contractNo", contractNo);
		parameters.put("city", city);
		return FacadeFactory.getFacade().find(FIND_USERS_TO_UPDATE, parameters);
	}
}
