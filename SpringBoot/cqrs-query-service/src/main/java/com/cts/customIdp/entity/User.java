package com.cts.customIdp.entity;

public class User {

	private Long userId;
	private String providerId;
	private String targetId;
	private String federation;
	private String eduPersonId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getFederation() {
		return federation;
	}

	public void setFederation(String federation) {
		this.federation = federation;
	}

	public String getEduPersonId() {
		return eduPersonId;
	}

	public void setEduPersonId(String eduPersonId) {
		this.eduPersonId = eduPersonId;
	}

}
