package com.cts.user.controller.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

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

import com.cts.user.dto.IdpDTO;
import com.cts.user.entity.Idp;
import com.cts.user.repository.IdpRepository;
import com.cts.user.service.impl.IdpCommandServiceImpl;
import com.cts.user.service.impl.KafkaClient;

@ExtendWith(MockitoExtension.class)
public class IdpCommandServiceImplTest {

	@InjectMocks
	private IdpCommandServiceImpl serviceImpl;

	@Mock
	private IdpRepository idpRepository;

	@Mock
	private KafkaClient kafkaClient;

	@Captor
	private ArgumentCaptor<Idp> idpArgumentCaptor;

	@Test
	void createUser_thenReturnUserEntity() {
		IdpDTO idpDTO = new IdpDTO("Oxford", "IDP1");
		Idp idpEntity = new Idp();
		idpEntity.setId(1L);
		Mockito.when(idpRepository.save(any(Idp.class))).thenReturn(idpEntity);
		serviceImpl.createIdp(idpDTO);
		Mockito.verify(idpRepository, Mockito.times(1)).save(idpArgumentCaptor.capture());
	}
	
	@Test
	void checkIfUserExist_whenUserNotExist_thenReturnFalse() {
		List<Idp> idpList = new ArrayList<>();
		idpList.add(new Idp());
		Mockito.when(idpRepository.findAll()).thenReturn(idpList);
		boolean userExists = serviceImpl.checkIfIdpExist("IDP1");
		assertFalse(userExists);
	}
	
	@Test
	void checkIfUserExist_whenUserExist_thenReturnTrue() {
		List<Idp> idpList = new ArrayList<>();
		Idp idp = new Idp();
		idp.setName("IDP1");
		idpList.add(idp);
		Mockito.when(idpRepository.findAll()).thenReturn(idpList);
		boolean userExists = serviceImpl.checkIfIdpExist("IDP1");
		assertTrue(userExists);
	}


}
