package com.cts.user.service;

import com.cts.user.dto.UserDTO;

public interface UserCommandService {
	void createUser(UserDTO dto);

	boolean checkIfUserExist(String providerId);

}
