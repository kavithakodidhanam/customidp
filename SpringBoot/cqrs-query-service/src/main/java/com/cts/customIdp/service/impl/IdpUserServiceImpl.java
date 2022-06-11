package com.cts.customIdp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cts.customIdp.entity.Idp;
import com.cts.customIdp.entity.IdpUser;
import com.cts.customIdp.repository.IdpRepository;
import com.cts.customIdp.repository.IdpUserRepository;
import com.cts.customIdp.service.IdpUserService;

@Service
public class IdpUserServiceImpl implements IdpUserService {

	@Autowired
	private IdpUserRepository idpUserRepository;

	@Autowired
	private IdpRepository idpRepository;

	@Override
	public Page<IdpUser> getIdpUsers(Pageable pageable) {
		return this.idpUserRepository.findAll(pageable);
	}

	@Override
	public List<Idp> getAllIdps() {
		return this.idpRepository.findAll();
	}

	@Override
	public List<IdpUser> getIdpByProviderId(String providerId) {
		return this.idpUserRepository.findByProviderId(providerId);
	}

}
