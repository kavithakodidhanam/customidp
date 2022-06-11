package com.cts.customIdp.kafka.message;

public class UserMessage {

	private Long userId;
	private String providerId;
	private String targetId;
	private String federation;
	private Long idpId;
	private String eduPersonId;

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

	public Long getIdpId() {
		return idpId;
	}

	public void setIdpId(Long idpId) {
		this.idpId = idpId;
	}

	public String getEduPersonId() {
		return eduPersonId;
	}

	public void setEduPersonId(String eduPersonId) {
		this.eduPersonId = eduPersonId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
