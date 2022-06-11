package com.cts.customIdp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.cts.customIdp.entity.Idp;
import com.cts.customIdp.entity.IdpUser;
import com.cts.customIdp.entity.User;
import com.cts.customIdp.kafka.message.IdpMessage;
import com.cts.customIdp.kafka.message.UserMessage;
import com.cts.customIdp.repository.IdpRepository;
import com.cts.customIdp.repository.IdpUserRepository;
import com.cts.customIdp.service.UserServiceEventHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserServiceEventHandlerImpl implements UserServiceEventHandler {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@Autowired
	private IdpUserRepository idpUserRepository;

	@Autowired
	private IdpRepository idpRepository;

	@KafkaListener(topics = "user-service")
	public void consumeUser(String userStr)  {
		try {
			System.out.println("Consumed message: " + userStr);
			UserMessage userMessage = OBJECT_MAPPER.readValue(userStr, UserMessage.class);
			createIdpUser(userMessage);
		} catch (Exception e) {
			System.out.println("Exception when consuming data");
		}
	}

	@KafkaListener(topics = "idp-service")
	public void consumeIdp(String idpStr) {
		try {
			System.out.println("idpStr Consumed message: " + idpStr);
			IdpMessage idpMessage = OBJECT_MAPPER.readValue(idpStr, IdpMessage.class);
			createIdp(idpMessage);
		} catch (Exception e) {
			System.out.println("Exception when consuming data");
		}
	}

	private void createIdp(IdpMessage idpMessage) {
		Idp idp = new Idp(idpMessage.getIdpId(),idpMessage.getIdpName(),idpMessage.getInstitutionName());
		this.idpRepository.save(idp);
	}

	public void createIdpUser(UserMessage message) {
		IdpUser idpUser = new IdpUser();
		idpUser.setUser(createUser(message));
		idpUser.setIdp(createIdp(message));
		idpUser.setId(message.getUserId());
		this.idpUserRepository.save(idpUser);
	}

	private Idp createIdp(UserMessage message) {
		List<Idp> idp = this.idpRepository.findByUserId(message.getIdpId());
		if (idp.size() > 0) {
			return idp.get(0);
		}
		return null;
	}

	private User createUser(UserMessage message) {
		User user = new User();
		user.setEduPersonId(message.getEduPersonId());
		user.setFederation(message.getFederation());
		user.setProviderId(message.getProviderId());
		user.setTargetId(message.getTargetId());
		user.setUserId(message.getUserId());
		return user;
	}
}
