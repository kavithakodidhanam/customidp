package com.cts.customIdp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.customIdp.entity.Idp;
import com.cts.customIdp.entity.IdpUser;
import com.cts.customIdp.payload.PagedResponse;
import com.cts.customIdp.service.IdpUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(produces = "application/json", value = "Query API service that helps to fetch the IDP and IDP users data ")
public class CustomIdpQueryController {

	private static final String DEFAULT_PAGE_NO = "0";
	private static final String DEFAULT_PAGE_SIZE = "30";
	private static final String DEFAULT_PAGE_SORT_DIRECTION = "DESC";
	private static final String DEFAULT_PAGE_SORT_FIELD = "id";
	private static final String DEFAULT_SEARCH_KEY = "";

	@Autowired
	private IdpUserService idpUserService;
	private Pageable pageable1;

	@GetMapping(path = "/getAllIdps", produces = "application/json")
	@ApiOperation(value = "Retrieves all the Idps")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Idps to retrieve"),
			@ApiResponse(code = 200, message = "Returning all the idps"),
			@ApiResponse(code = 500, message = "Application failed to process the request") })
	public ResponseEntity<List<Idp>> getAllIdpUsers() {
		try {
			List<Idp> idps = idpUserService.getAllIdps();
			if (idps == null || idps.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(idps, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(path = "/getIdpuser", produces = "application/json")
	@ApiOperation(value = "Retrieves all the IdpUsers with pagination and sorting feasibilty")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Idps to retrieve"),
			@ApiResponse(code = 200, message = "Returning all the idps"),
			@ApiResponse(code = 500, message = "Application failed to process the request") })
	public ResponseEntity<PagedResponse<IdpUser>> getIdpUsers(
			@RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NO) int page,
			@RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) int size,
			@RequestParam(value = "sort", defaultValue = DEFAULT_PAGE_SORT_DIRECTION) String sort,
			@RequestParam(value = "sortField", defaultValue = DEFAULT_PAGE_SORT_FIELD) String sortField,
			@RequestParam(value = "keyword", defaultValue = DEFAULT_SEARCH_KEY) String searchKey) {

		try {

			Direction sortDirection = sort.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;

			Pageable pageable = PageRequest.of(page, size, sortDirection, sortField);

			Page<IdpUser> resultPage = null;
			if(searchKey!=null && !searchKey.isEmpty()){
				List<IdpUser> idpUser = idpUserService.getIdpByProviderId(searchKey);
				Page pg= new PageImpl(idpUser);
				resultPage = pg;
			//	resultPage = (Page<IdpUser>) idpUserService.getIdpByProviderId(searchKey);
			}else{
				resultPage = idpUserService.getIdpUsers(pageable);
			}

			if (resultPage == null || resultPage.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(
					new PagedResponse<>(resultPage.getContent(), resultPage.getNumber(), resultPage.getSize(),
							resultPage.getTotalPages(), resultPage.getTotalElements(), resultPage.isLast()),
					HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
