package com.dmg.admin.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.dmg.core.bean.AbstractPojo;

@Entity
@Table(name = "appuser", uniqueConstraints = { @UniqueConstraint(columnNames = { "USERNAME" }) })
public class User extends AbstractPojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 711201638919389292L;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	protected String password;

	@Column(name = "NAME")
	private String fullName;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "ROLE")
	private String role;

	@Column(name = "FAILED_LOGIN_ATTEMPTS")
	private int failedLoginAttempts = 0;

	@Column(name = "ACCOUNT_LOCKED")
	private boolean accountLocked = false;

	@Column(name = "REASON_FOR_LOCKED_ACCOUNT")
	private String reasonForLockedAccount;

	@Transient
	private int failedPasswordChanges = 0;

	public User() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getFailedLoginAttempts() {
		return failedLoginAttempts;
	}

	public void setFailedLoginAttempts(int failedLoginAttempts) {
		this.failedLoginAttempts = failedLoginAttempts;
	}

	public boolean isAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public String getReasonForLockedAccount() {
		return reasonForLockedAccount;
	}

	public void setReasonForLockedAccount(String reasonForLockedAccount) {
		this.reasonForLockedAccount = reasonForLockedAccount;
	}

	public int getFailedPasswordChanges() {
		return failedPasswordChanges;
	}

	public void incrementFailedLoginAttempts() {
		failedLoginAttempts++;
	}

	public void incrementFailedPasswordChangeAttempts() {
		failedPasswordChanges++;
	}

	public void clearFailedLoginAttempts() {
		failedLoginAttempts = 0;
	}

	public void clearFailedPasswordChangeAttempts() {
		failedPasswordChanges = 0;
	}

}
