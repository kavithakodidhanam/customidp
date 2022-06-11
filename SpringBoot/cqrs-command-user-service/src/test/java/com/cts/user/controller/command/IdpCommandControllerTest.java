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

import com.cts.user.dto.IdpDTO;
import com.cts.user.service.IdpCommandService;

@ExtendWith(MockitoExtension.class)
public class IdpCommandControllerTest {

	@Mock
	private IdpCommandService idpCommandService;

	@InjectMocks
	private IdpCommandController mockController;

	@Test
	void createIdp_whenRequestInvalid_then4xxReturned() {
		ResponseEntity<?> responseEntity = mockController.createIdp(null);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	void createIdp_whenInstnNameNotPassed_then4xxReturned() {
		IdpDTO idpDTO = new IdpDTO(null,"IDP_001");
		ResponseEntity<?> responseEntity = mockController.createIdp(idpDTO);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	void createIdp_whenIdpNameNotPassed_then4xxReturned() {
		IdpDTO idpDTO = new IdpDTO("Oxford",null);
		ResponseEntity<?> responseEntity = mockController.createIdp(idpDTO);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	void createIdp_whenIdpAlreadyExists_then4xxReturned() {
		IdpDTO idpDTO = new IdpDTO("Oxford", "IDP_001");
		Mockito.when(idpCommandService.checkIfIdpExist(idpDTO.getIdpName())).thenReturn(true);
		ResponseEntity<?> responseEntity = mockController.createIdp(idpDTO);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	void createIdp_whenInputsValid_thenIdpCreated() {
		IdpDTO idpDTO = new IdpDTO("Oxford", "IDP_001");
		Mockito.when(idpCommandService.checkIfIdpExist(idpDTO.getIdpName())).thenReturn(false);
		ResponseEntity<?> responseEntity = mockController.createIdp(idpDTO);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}

	@Test
	void createIdp_whenExceptionOccurs_then5xxReturned() {
		IdpDTO idpDTO = new IdpDTO("Oxford", "IDP_001");
		Mockito.when(idpCommandService.checkIfIdpExist(idpDTO.getIdpName())).thenThrow(RuntimeException.class);
		ResponseEntity<?> responseEntity = mockController.createIdp(idpDTO);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
	}
}
