package com.cts.user.dto;

public class IdpDTO {

	private String institutionName;
	private String idpName;

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getIdpName() {
		return idpName;
	}

	public void setIdpName(String idpName) {
		this.idpName = idpName;
	}

	@Override
	public String toString() {
		return "IdpDTO [institutionName=" + institutionName + ", idpName=" + idpName + "]";
	}

	public IdpDTO(String institutionName, String idpName) {
		super();
		this.institutionName = institutionName;
		this.idpName = idpName;
	}

}
