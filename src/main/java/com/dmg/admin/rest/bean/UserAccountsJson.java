package com.dmg.admin.rest.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
public class UserAccountsJson implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6629951318825003209L;
	/**
	 * @author Kareem Jabr
	 */

	@JsonProperty("users")
	private List<UserAccountJson> userAccountJsonList = new ArrayList<UserAccountJson>();

	public List<UserAccountJson> getUserAccountJsonList() {
		return userAccountJsonList;
	}

	public void setUserAccountJsonList(List<UserAccountJson> userAccountJsonList) {
		this.userAccountJsonList = userAccountJsonList;
	}

	public void addUserAccountJson(UserAccountJson userAccountJson) {
		userAccountJsonList.add(userAccountJson);
	}

}
