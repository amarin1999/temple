package com.cdgs.temple.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cdgs.temple.entity.ForgetPassEntity;

import java.util.List;

public interface ForgetPassRepository extends CrudRepository<ForgetPassEntity, Long> {
	List<ForgetPassEntity> findAll();
	
	@Query(value = "SELECT COUNT(mb.member_id) as count"
			+ " FROM members mb"
			+ " WHERE mb.member_email = :email"
			+ " and mb.member_username = :userName", nativeQuery = true)
	Integer countUser(@Param("email") String email, @Param("userName") String username);

}
