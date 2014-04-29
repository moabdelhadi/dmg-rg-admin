package com.dmg.admin.auth;

import java.io.Serializable;

import com.dmg.admin.bean.User;

/**
 * Interface for listening to logout events.
 * 
 * @author Kim
 * 
 */
public class LogoutEvent implements Serializable {

	private static final long serialVersionUID = -5497435495187618081L;

	private final User user;

	/**
	 * 
	 * @param user
	 *            The user who is been logged out
	 */
	public LogoutEvent(User user) {
		this.user = user;
	}

	/**
	 * Get the user who has been logged out
	 * 
	 * @return The user object of the logged out user
	 */
	public User getUser() {
		return user;
	}

}