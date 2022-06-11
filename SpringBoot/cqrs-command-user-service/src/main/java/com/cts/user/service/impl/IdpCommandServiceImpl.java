package com.cts.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.user.dto.IdpDTO;
import com.cts.user.entity.Idp;
import com.cts.user.repository.IdpRepository;
import com.cts.user.service.IdpCommandService;

@Service
public class IdpCommandServiceImpl implements IdpCommandService {

	@Autowired
	private IdpRepository idpRepository;

	@Autowired
	private KafkaClient kafkaClient;

	@Override
	public void createIdp(IdpDTO idpDTO) {
		Idp entity = new Idp();
		entity.setInstitution(idpDTO.getInstitutionName());
		entity.setName(idpDTO.getIdpName());
		Long id = this.idpRepository.save(entity).getId();

		new Thread(new Runnable() {
			public void run() {
				System.out.println("Performing operation in Asynchronous Task");
				try{
				kafkaClient.raiseIdpEvent(idpDTO, id);
				}catch(Exception e) {
					System.out.println("Exception in intialisation");
				}
			}
		}).start();
	}

	@Override
	public boolean checkIfIdpExist(String name) {
		List<Idp> idpList = idpRepository.findAll();
		for (Idp idp : idpList) {
			if (name.equalsIgnoreCase(idp.getName())) {
				return true;
			}
		}
		return false;

	}

}
