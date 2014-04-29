package com.dmg.admin.auth.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.dmg.admin.auth.SessionHandler;
import com.dmg.admin.bean.User;
import com.dmg.admin.exception.EmailInvalidException;
import com.dmg.admin.exception.InvalidCredentialsException;
import com.dmg.admin.exception.PasswordRequirementException;
import com.dmg.admin.exception.PasswordsDoNotMatchException;
import com.dmg.admin.exception.TooShortPasswordException;
import com.dmg.admin.exception.TooShortUsernameException;
import com.dmg.admin.exception.UsernameExistsException;
import com.dmg.admin.util.PropertiesManager;
import com.dmg.core.exception.DataAccessLayerException;
import com.dmg.core.persistence.FacadeFactory;

/**
 * Controller for managing user accounts
 * 
 * @author Kim
 * 
 */
public class UserUtil implements Serializable {

	private static final long serialVersionUID = 6394812141386916155L;

	/**
	 * Get the user object with the given primary key
	 * 
	 * @param id
	 * @return An instance of User
	 * @throws DataAccessLayerException
	 */
	public static User getUser(Long id) throws DataAccessLayerException {
		return FacadeFactory.getFacade().find(User.class, id);
	}

	/**
	 * This method tries to register a new user
	 * 
	 * @param username
	 *            Desired username
	 * @param password
	 *            Desired password
	 * @param verifyPassword
	 *            Verification of the desired password
	 * @return The created user instance
	 * @throws TooShortPasswordException
	 *             Thrown if the given password is too short
	 * @throws TooShortUsernameException
	 *             Thrown i the given username is too short
	 * @throws PasswordsDoNotMatchException
	 *             Thrown i the password verification fails
	 * @throws UsernameExistsException
	 *             Thrown i the username already exists
	 * @throws EmailInvalidException
	 *             Thrown if the email is invalid
	 * @throws DataAccessLayerException
	 */
	public static User registerUser(String username, String password, String verifyPassword, String email, String fullName) throws TooShortPasswordException, TooShortUsernameException,
			PasswordsDoNotMatchException, UsernameExistsException, PasswordRequirementException, EmailInvalidException, DataAccessLayerException {

		verifyUsernameLength(username);
		verifyPasswordLength(password);
		validatePassword(password);
		checkPasswordVerification(password, verifyPassword);
		verifyUsernameAvailability(username);
		verifyEmailValidity(email);

		// Everything is ok, create the user
		User user = createUser(username, password, email, fullName);

		return user;
	}

	private static void verifyEmailValidity(String email) throws EmailInvalidException {
		EmailUtil.validateEmail(email);

	}

	/**
	 * Validates that the given password fulfills all the set requirements
	 * 
	 * @param password
	 *            Password to check
	 * @throws Exception
	 *             Exception thrown if the password doesn't meet the
	 *             requirements
	 */
	private static void validatePassword(String password) throws PasswordRequirementException {
		PasswordUtil.isValid(password);
	}

	/**
	 * Creates and stores a user with the given username and password
	 * 
	 * @param username
	 *            The username for the user
	 * @param password
	 *            The user's password
	 * @return The created {@link User} object
	 * @throws DataAccessLayerException
	 */
	private static User createUser(String username, String password, String email, String fullName) throws DataAccessLayerException {
		User user = new User();
		user.setUsername(username);
		user.setPassword(PasswordUtil.generateHashedPassword(password));
		user.setEmail(email);
		user.setFullName(fullName);
		user.setCreationDate(new Date());

		FacadeFactory.getFacade().store(user);
		return user;
	}

	/**
	 * Verifies that the given username doesn't exist
	 * 
	 * @param username
	 *            The username to check
	 * @throws UsernameExistsException
	 *             Thrown if username already exists
	 * @throws DataAccessLayerException
	 */
	private static void verifyUsernameAvailability(String username) throws UsernameExistsException, DataAccessLayerException {
		if (!checkUsernameAvailability(username)) {
			throw new UsernameExistsException();
		}
	}

	/**
	 * Makes sure the given username is long enough
	 * 
	 * @param username
	 *            Username to check
	 * @throws TooShortUsernameException
	 *             Thrown if username is null or too short
	 */
	private static void verifyUsernameLength(String username) throws TooShortUsernameException {// Make
																								// sure
																								// that
																								// the
																								// username
																								// and
		if (username == null || username.length() < getMinUsernameLength()) {
			throw new TooShortUsernameException();
		}
	}

	/**
	 * Makes sure the given password is long enough
	 * 
	 * @param password
	 *            Password to check
	 * @throws TooShortPasswordException
	 *             Thrown if password is null or too short
	 */
	private static void verifyPasswordLength(String password) throws TooShortPasswordException {
		if (password == null || password.length() < getMinPasswordLength()) {
			throw new TooShortPasswordException();
		}
	}

	/**
	 * Verifies that the given passwords match with eachother
	 * 
	 * @param password
	 *            Password to check
	 * @param verifyPassword
	 *            Verification of the first password
	 * @throws PasswordsDoNotMatchException
	 *             Thrown if the given parameters do not equal eachother
	 */
	private static void checkPasswordVerification(String password, String verifyPassword) throws PasswordsDoNotMatchException {
		if (!password.equals(verifyPassword)) {
			throw new PasswordsDoNotMatchException();
		}
	}

	/**
	 * Checks if the given username is available.
	 * 
	 * @param username
	 *            Desired username
	 * @return Returns true if the username doesn't exist, false if it exists
	 * @throws DataAccessLayerException
	 */
	private static boolean checkUsernameAvailability(String username) throws DataAccessLayerException {
		String query = "SELECT u FROM User u WHERE u.username = :username";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("username", username);

		User user = FacadeFactory.getFacade().find(query, parameters);

		return user != null ? false : true;
	}

	/**
	 * Change the password of the given user. Verifies that the new password
	 * meets all the set conditions.
	 * 
	 * @param user
	 *            User object for which we want to change the password
	 * @param currentPassword
	 *            Current password
	 * @param newPassword
	 *            Desired new password
	 * @param verifiedNewPassword
	 *            New password verification
	 * @throws InvalidCredentialsException
	 *             Thrown if the current password is incorrect
	 * @throws TooShortPasswordException
	 *             Thrown if the new password is too short
	 * @throws PasswordsDoNotMatchException
	 *             Thrown if the password verification fails
	 * @throws DataAccessLayerException
	 */
	public static void changePassword(User user, String currentPassword, String newPassword, String verifiedNewPassword) throws InvalidCredentialsException, TooShortPasswordException,
			PasswordsDoNotMatchException, PasswordRequirementException, DataAccessLayerException {

		// Verify that the current password is correct
		if (!PasswordUtil.verifyPassword(user, currentPassword)) {
			incrementFailedPasswordChangeAttempts(user);
			throw new InvalidCredentialsException();
		}

		user.clearFailedPasswordChangeAttempts();

		// Check the new password's constraints
		verifyPasswordLength(newPassword);
		validatePassword(newPassword);

		checkPasswordVerification(newPassword, verifiedNewPassword);

		// Password is ok, hash it and change it
		user.setPassword(PasswordUtil.generateHashedPassword(newPassword));
		// Store the user

		FacadeFactory.getFacade().store(user);
	}

	/**
	 * Increments the number of failed login attempts by one and if maximum is
	 * reached, logs out the user.
	 * 
	 * @param user
	 */
	private static void incrementFailedPasswordChangeAttempts(User user) {
		user.incrementFailedPasswordChangeAttempts();

		if (user.getFailedPasswordChanges() > numberOfAllowedFailedPasswordChangeAttempts()) {
			SessionHandler.logout();
		}
	}

	/**
	 * Stores to the database any changes made to the user object
	 * 
	 * @param user
	 *            An instance of the User object
	 * @throws DataAccessLayerException
	 */
	public static void storeUser(User user) throws DataAccessLayerException {
		FacadeFactory.getFacade().store(user);
	}

	/**
	 * Returns the minimum length of a username
	 * 
	 * @return Minimum username length
	 */
	public static int getMinUsernameLength() {
		String minLenghtStr = PropertiesManager.getInstance().getProperty("authentication.username.validation.length");
		int minLenght = 4;
		if (minLenghtStr == null) {
			//			System.setProperty("authentication.username.validation.length", "4");
			return minLenght;
		}

		try {
			minLenght = Integer.valueOf(minLenghtStr);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("authentication.username.validation.length must be an integer");
		}

		return minLenght;
	}

	/**
	 * Returns the minimum length of a password
	 * 
	 * @return Minimum password length
	 */
	public static int getMinPasswordLength() {
		return PasswordUtil.getMinPasswordLength();
	}

	/**
	 * Returns the maximum number of consecutive failed login attempts.
	 * 
	 * @return
	 */
	private static int numberOfAllowedFailedPasswordChangeAttempts() {
		String maxAttemptsStr = PropertiesManager.getInstance().getProperty("authentication.maxFailedPasswordChangeAttempts");
		int maxAttempts = 5;
		if (maxAttemptsStr == null) {
			//System.setProperty("authentication.maxFailedPasswordChangeAttempts", "5");
			return maxAttempts;
		}

		try {
			maxAttempts = Integer.valueOf(maxAttemptsStr);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("authentication.maxFailedPasswordChangeAttempts must be an integer");
		}

		return maxAttempts;
	}

}
