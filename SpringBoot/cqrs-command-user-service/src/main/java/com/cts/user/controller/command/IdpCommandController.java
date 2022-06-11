package com.cts.user.controller.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.user.dto.IdpDTO;
import com.cts.user.service.IdpCommandService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(consumes = "application/json", value = "Command API service that helps to create IDP data ")

public class IdpCommandController {

	@Autowired
	private IdpCommandService idpCommandService;

	@PostMapping(path = "/createIdp", consumes = "application/json")
	@ApiOperation(value = "Creates the Idp")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Idp created successfully"),
			@ApiResponse(code = 400, message = "Validation issues in the request parameters"),
			@ApiResponse(code = 500, message = "Application failed to process the request") })
	public ResponseEntity<?> createIdp(@RequestBody IdpDTO dto) {
		try {
			if (dto == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} else if (validateIdp(dto) && !validateIfIdpExist(dto)) {
				this.idpCommandService.createIdp(dto);
				return new ResponseEntity<>(HttpStatus.CREATED);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private boolean validateIfIdpExist(IdpDTO dto) {
		return idpCommandService.checkIfIdpExist(dto.getIdpName());
	}

	private boolean validateIdp(IdpDTO dto) {
		if (StringUtils.isEmpty(dto.getIdpName()) || StringUtils.isEmpty(dto.getInstitutionName())) {
			return false;
		}
		return true;
	}

}
