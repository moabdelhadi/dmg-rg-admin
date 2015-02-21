package com.dmg.admin.util;

public enum StatusEnum {
	APPROVED("Approved", 1), REJECTED("Rejected", 2), PENDING("Pending", 0);

	private String name;
	private int status;

	StatusEnum(String name, int status) {
		this.name = name;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public int getStatus() {
		return status;
	}

}
