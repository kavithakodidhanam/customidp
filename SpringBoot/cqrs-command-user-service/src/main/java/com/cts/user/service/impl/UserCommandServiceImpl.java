package com.cts.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.user.dto.UserDTO;
import com.cts.user.entity.User;
import com.cts.user.repository.UserRepository;
import com.cts.user.service.UserCommandService;

@Service
public class UserCommandServiceImpl implements UserCommandService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private KafkaClient kafkaClient;

	@Override
	public void createUser(UserDTO userDto) {
		User entity = new User();
		entity.setIdp(Long.valueOf(userDto.getIdpId()));
		entity.setProvider(userDto.getProviderId());
		entity.setTarget(userDto.getTargetId());
		entity.setFederation(userDto.getFederation());
		entity.setPerson(userDto.getEduPersonId());
		Long userId = this.userRepository.save(entity).getId();
		
		new Thread(new Runnable() {
			public void run() {
				try {
				System.out.println("Performing operation in Asynchronous Task");
				kafkaClient.raiseUserEvent(userDto, userId);
				}catch(Exception e) {
					System.out.println("Exception in intialisation");
				}
			}
		}).start();
		

	}

	@Override
	public boolean checkIfUserExist(String providerId) {
		List<User> userList = userRepository.findAll();
		for (User user : userList) {
			if (providerId.equalsIgnoreCase(user.getProvider())) {
				return true;
			}
		}
		return false;
	}

}
