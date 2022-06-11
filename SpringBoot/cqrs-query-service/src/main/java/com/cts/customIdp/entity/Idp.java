package com.cts.customIdp.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "idp")
public class Idp {

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

	public Idp(long idpId, String idpName, String institutionName) {
		super();
		this.idpId = idpId;
		this.idpName = idpName;
		this.institutionName = institutionName;
	}

}
