package com.quovantis.recruit.util;

/**
 * Enum to store application status
 * @author Hp
 *
 */
public enum ApplicationStatus {
	APPLIED(1), INVITED(2), REJECTED(3), HIRED(4);

	int appStatus;

	private ApplicationStatus(int appStatus) {
		this.appStatus = appStatus;
	}
	
	public void setAppStatus(int appStatus) {
		this.appStatus = appStatus;
	}

	public int getAppStatus() {
		return appStatus;
	}

}
