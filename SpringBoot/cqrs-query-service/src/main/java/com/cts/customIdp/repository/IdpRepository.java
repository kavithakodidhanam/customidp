package com.cts.customIdp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.customIdp.entity.Idp;

@Repository
public interface IdpRepository extends MongoRepository<Idp, String> {

	@Query("{ 'idpId': :#{#idp} }")
	List<Idp> findByUserId(@Param("idp") long idp);

}
