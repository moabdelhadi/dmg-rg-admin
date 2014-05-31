package com.dmg.admin.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

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

	public List<UserAccount> lisUsers() throws DataAccessLayerException {
		return FacadeFactory.getFacade().list(LIST_QUERY_ORDERED_BY_DATE, new HashMap<String, Object>());
	}

}
