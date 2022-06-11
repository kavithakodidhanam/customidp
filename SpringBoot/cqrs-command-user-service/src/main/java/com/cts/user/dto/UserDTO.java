package com.cts.user.dto;

public class UserDTO {

	private String providerId;
	private String targetId;
	private String federation;
	private String idpId;
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

	

	public String getIdpId() {
		return idpId;
	}

	public void setIdpId(String idpId) {
		this.idpId = idpId;
	}

	public String getEduPersonId() {
		return eduPersonId;
	}

	public void setEduPersonId(String eduPersonId) {
		this.eduPersonId = eduPersonId;
	}

	@Override
	public String toString() {
		return "UserDTO [providerId=" + providerId + ", targetId=" + targetId + ", federation=" + federation
				+ ", idpId=" + idpId + ", eduPersonId=" + eduPersonId + "]";
	}

	public UserDTO(String providerId, String targetId, String federation, String idpId, String eduPersonId) {
		super();
		this.providerId = providerId;
		this.targetId = targetId;
		this.federation = federation;
		this.idpId = idpId;
		this.eduPersonId = eduPersonId;
	}

}
