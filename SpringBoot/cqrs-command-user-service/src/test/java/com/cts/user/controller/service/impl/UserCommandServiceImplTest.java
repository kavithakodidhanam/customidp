package com.cts.user.controller.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cts.user.dto.UserDTO;
import com.cts.user.entity.User;
import com.cts.user.repository.UserRepository;
import com.cts.user.service.impl.KafkaClient;
import com.cts.user.service.impl.UserCommandServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserCommandServiceImplTest {

	@InjectMocks
	private UserCommandServiceImpl serviceImpl;

	@Mock
	private UserRepository userRepository;

	@Mock
	private KafkaClient kafkaClient;

	@Captor
	private ArgumentCaptor<User> userArgumentCaptor;

	@Test
	void createUser_thenReturnUserEntity() {
		UserDTO userDTO = new UserDTO("idp_001", "password", "SHIB", "1", "E123");
		User userEntity = new User();
		userEntity.setId(1L);
		Mockito.when(userRepository.save(any(User.class))).thenReturn(userEntity);
		serviceImpl.createUser(userDTO);
		Mockito.verify(userRepository, Mockito.times(1)).save(userArgumentCaptor.capture());
		Mockito.verify(kafkaClient, Mockito.times(1)).raiseUserEvent(any(),any());
	}
	
	@Test
	void checkIfUserExist_whenUserNotExist_thenReturnFalse() {
		List<User> userList = new ArrayList<User>();
		userList.add(new User());
		Mockito.when(userRepository.findAll()).thenReturn(userList);
		boolean userExists = serviceImpl.checkIfUserExist("IDP_001");
		assertFalse(userExists);
	}
	
	@Test
	void checkIfUserExist_whenUserExist_thenReturnTrue() {
		List<User> userList = new ArrayList<User>();
		User user = new User();
		user.setProvider("IDP_001");
		userList.add(user);
		Mockito.when(userRepository.findAll()).thenReturn(userList);
		boolean userExists = serviceImpl.checkIfUserExist("IDP_001");
		assertTrue(userExists);
	}


}
