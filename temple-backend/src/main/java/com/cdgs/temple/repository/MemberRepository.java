package com.cdgs.temple.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdgs.temple.entity.MemberEntity;

@Repository
public interface MemberRepository extends CrudRepository<MemberEntity, Long> {

	List<MemberEntity> findAll();
	
	MemberEntity findByMemberUsername(String username);
	List<MemberEntity> findAllByRole_RoleName(String roleName);
	
	
}
