package com.cts.customIdp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.customIdp.entity.IdpUser;

@Repository
public interface IdpUserRepository extends MongoRepository<IdpUser, String> {

	
	@Query("{ 'user.id': :#{#userId} }")
	List<IdpUser> findByUserId(@Param("userId") long userId);
	
	
	@Query("{ 'user.providerId': :#{#providerId} }")
	List<IdpUser> findByProviderId(@Param("providerId") String providerId);
}
