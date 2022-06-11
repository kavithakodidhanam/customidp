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

import com.cts.user.dto.UserDTO;
import com.cts.user.service.UserCommandService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(consumes = "application/json", value = "Command API service that helps to create IDP users data ")
public class UserCommandController {

	@Autowired
	private UserCommandService userCommandService;

	@PostMapping(path = "/createUser", consumes = "application/json")
	@ApiOperation(value = "Creates the users")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Idp created successfully"),
			@ApiResponse(code = 400, message = "Validation issues in the request parameters"),
			@ApiResponse(code = 500, message = "Application failed to process the request") })
	public ResponseEntity<Object> createUser(@RequestBody UserDTO dto) {
		try {
			if (dto == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} else if (validateUser(dto) && !validateIfUserExist(dto)) {
				this.userCommandService.createUser(dto);
				return new ResponseEntity<>(HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private boolean validateUser(UserDTO dto) {

		if (StringUtils.isEmpty(dto.getEduPersonId()) || StringUtils.isEmpty(dto.getTargetId())
				|| StringUtils.isEmpty(dto.getFederation()) || StringUtils.isEmpty(dto.getIdpId())
				|| StringUtils.isEmpty(dto.getProviderId())) {
			return false;
		}
		
		return true;
	}

	private boolean validateIfUserExist(UserDTO dto) {
		return userCommandService.checkIfUserExist(dto.getProviderId());

	}
}
