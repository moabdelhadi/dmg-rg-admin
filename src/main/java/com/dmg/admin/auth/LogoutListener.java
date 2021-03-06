package com.dmg.admin.auth;

/**
 * Event which is dispatched when a user logs out.
 * 
 * @author Kim
 * 
 */
public interface LogoutListener {

	/**
	 * Called when a logout occurs in the application instance.
	 * 
	 * @param event
	 *            LogoutEvent containing the user who has been logged out
	 */
	public void logout(LogoutEvent event);

}