package com.cts.customIdp.kafka.message;

public class IdpMessage {

	private long idpId;
	private String idpName;
	private String institutionName;

	public long getIdpId() {
		return idpId;
	}

	public void setIdpId(long idpId) {
		this.idpId = idpId;
	}

	public String getIdpName() {
		return idpName;
	}

	public void setIdpName(String idpName) {
		this.idpName = idpName;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

}
