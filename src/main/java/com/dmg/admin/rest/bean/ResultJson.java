package com.dmg.admin.rest.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResultJson implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5436903479533437977L;
	private String status;
	private String messages = new String();

	private List<BillJson> bills = new ArrayList<BillJson>();
	private List<UserAccountJson> users = new ArrayList<UserAccountJson>();

	public List<BillJson> getBills() {
		return bills;
	}

	public void setBills(List<BillJson> bills) {
		this.bills = bills;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public void addBill(BillJson billJson) {
		bills.add(billJson);
	}

	public List<UserAccountJson> getUsers() {
		return users;
	}

	public void setUsers(List<UserAccountJson> users) {
		this.users = users;
	}

	public void addUser(UserAccountJson userAccountJson) {
		users.add(userAccountJson);
	}

}
