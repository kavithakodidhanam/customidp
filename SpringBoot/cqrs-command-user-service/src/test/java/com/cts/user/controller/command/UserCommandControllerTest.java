package com.cts.user.controller.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cts.user.dto.UserDTO;
import com.cts.user.service.UserCommandService;

@ExtendWith(MockitoExtension.class)
public class UserCommandControllerTest {

	@Mock
	private UserCommandService userCommandService;

	@InjectMocks
	private UserCommandController mockController;

	@Test
	void createIdp_whenRequestInvalid_then4xxReturned() {
		ResponseEntity<?> responseEntity = mockController.createUser(null);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	void createIdp_whenPersonIdNotPassed_then4xxReturned() {
		UserDTO userDTO = new UserDTO("IDP_001", "password", "SHIB", "1", null);
		ResponseEntity<?> responseEntity = mockController.createUser(userDTO);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	void createIdp_whenIdpIdNotPassed_then4xxReturned() {
		UserDTO userDTO = new UserDTO("IDP_001", "password", "SHIB", null, "E123");
		ResponseEntity<?> responseEntity = mockController.createUser(userDTO);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	void createIdp_whenFederationNotPassed_then4xxReturned() {
		UserDTO userDTO = new UserDTO("IDP_001", "password", null, "1", null);
		ResponseEntity<?> responseEntity = mockController.createUser(userDTO);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	void createIdp_whenTargetIdNotPassed_then4xxReturned() {
		UserDTO userDTO = new UserDTO("IDP_001", null, "SHIB", "1", "E123");
		ResponseEntity<?> responseEntity = mockController.createUser(userDTO);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	void createIdp_whenProviderIdNotPassed_then4xxReturned() {
		UserDTO userDTO = new UserDTO(null, "password", "SHIB","1", "E123");
		ResponseEntity<?> responseEntity = mockController.createUser(userDTO);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	void createIdp_whenIdpAlreadyExists_then4xxReturned() {

		UserDTO userDTO = new UserDTO("IDP_001", "password", "SHIB", "1", "E123");
		Mockito.when(userCommandService.checkIfUserExist(userDTO.getProviderId())).thenReturn(true);
		ResponseEntity<?> responseEntity = mockController.createUser(userDTO);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	void createIdp_whenInputsValid_thenIdpCreated() {

		UserDTO userDTO = new UserDTO("IDP_001", "password", "SHIB", "1", "E123");
		Mockito.when(userCommandService.checkIfUserExist(userDTO.getProviderId())).thenReturn(false);
		ResponseEntity<?> responseEntity = mockController.createUser(userDTO);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}

	@Test
	void createIdp_whenExceptionOccurs_then5xxReturned() {
		UserDTO userDTO = new UserDTO("IDP_001", "password", "SHIB", "1", "E123");
		Mockito.when(userCommandService.checkIfUserExist(userDTO.getProviderId())).thenThrow(RuntimeException.class);
		ResponseEntity<?> responseEntity = mockController.createUser(userDTO);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
	}
}
