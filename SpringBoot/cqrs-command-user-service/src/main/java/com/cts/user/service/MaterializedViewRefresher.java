package com.cts.user.service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MaterializedViewRefresher {

	@Autowired
	private EntityManager entityManager;

	@Transactional
	@Scheduled(fixedRate = 5000L)
	public void refresh() {
		this.entityManager.createNativeQuery("call refresh_mat_view();").executeUpdate();
	}

}
