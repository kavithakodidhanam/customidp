package com.cts.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.cts.user.dto.IdpDTO;
import com.cts.user.dto.UserDTO;
import com.cts.user.kafka.messages.IdpMessage;
import com.cts.user.kafka.messages.UserMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaClient {

	private static final String USER_TOPIC_NAME = "user-service";
	private static final String IDP_TOPIC_NAME = "idp-service";

	@Autowired
	private KafkaTemplate<Long, String> kafkaTemplate;

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	public void raiseUserEvent(UserDTO userDto, Long userId) {
		try {
			UserMessage message = new UserMessage();
			message.setUserId(userId);
			message.setIdpId(Long.valueOf(userDto.getIdpId()));
			message.setProviderId(userDto.getProviderId());
			message.setTargetId(userDto.getTargetId());
			message.setFederation(userDto.getFederation());
			message.setEduPersonId(userDto.getEduPersonId());

			String value = OBJECT_MAPPER.writeValueAsString(message);
			System.out.println("KAFKA msg : " + value);
			kafkaTemplate.send(USER_TOPIC_NAME, userId, value);
		} catch (Exception e) {
			System.out.println("Exception when sending data to Kafka");
		}
	}

	public void raiseIdpEvent(IdpDTO idpDTO, Long id) {
		try {
			IdpMessage message = new IdpMessage();
			message.setIdpId(id);
			message.setIdpName(idpDTO.getIdpName());
			message.setInstitutionName(idpDTO.getInstitutionName());

			String value = OBJECT_MAPPER.writeValueAsString(message);
			System.out.println("KAFKA msg : " + value);
			kafkaTemplate.send(IDP_TOPIC_NAME, id, value);
		} catch (Exception e) {
			System.out.println("Exception when sending data to Kafka");
		}
	}
}
