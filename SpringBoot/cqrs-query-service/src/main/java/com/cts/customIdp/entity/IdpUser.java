package com.cts.customIdp.entity;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "idpuser")
public class IdpUser {

	@Id
	private Long id;
	@NotBlank
	private User user;
	@NotBlank
	private Idp idp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Idp getIdp() {
		return idp;
	}

	public void setIdp(Idp idp) {
		this.idp = idp;
	}

}
