package com.dmg.admin.util;

public enum ApproveStatusEnum {
	APPROVED("Approved", 1), REJECTED("Rejected", 2), PENDING("Pending", 0);

	private String name;
	private int status;

	ApproveStatusEnum(String name, int status) {
		this.name = name;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public int getStatus() {
		return status;
	}

	public static int findStatus(String name) {
		for (ApproveStatusEnum approveStatus : ApproveStatusEnum.values()) {
			if (name.equals(approveStatus.getName())) {
				return approveStatus.getStatus();
			}
		}
		return 0;
	}

}
