package com.cts.customIdp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cts.customIdp.entity.Idp;
import com.cts.customIdp.entity.IdpUser;

public interface IdpUserService {
	Page<IdpUser> getIdpUsers(Pageable pageable);

	List<Idp> getAllIdps();
	
	List<IdpUser> getIdpByProviderId(String searchKey);
	
}
