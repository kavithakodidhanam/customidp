package com.cts.user.controller.service.impl;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import com.cts.user.dto.IdpDTO;
import com.cts.user.dto.UserDTO;
import com.cts.user.service.impl.KafkaClient;

@ExtendWith(MockitoExtension.class)
public class KafkaClientTest {
	
	@Mock
	private KafkaTemplate<Long, String> kafkaTemplate;
	
	@InjectMocks
	private KafkaClient kafkaClient;
	
	@Test
	public void raiseUserEvent_whenUserDetailsPAssed() {
		UserDTO userDTO = new UserDTO("idp_001", "password", "SHIB", "1", "E123");
		kafkaClient.raiseUserEvent(userDTO, 1L);
		Mockito.verify(kafkaTemplate, Mockito.times(1)).send(any(),any(),any());
	}
	
	
	@Test
	public void raiseIdpEvent_whenIdpDetailsPAssed() {
		IdpDTO idpDTO = new IdpDTO("Oxford", "IDP1");
		kafkaClient.raiseIdpEvent(idpDTO, 1L);
		Mockito.verify(kafkaTemplate, Mockito.times(1)).send(any(),any(),any());
	}
}
