package com.cts.user.service;

import com.cts.user.dto.IdpDTO;

public interface IdpCommandService {

	void createIdp(IdpDTO dto);

	public boolean checkIfIdpExist(String name);

}
