package com.cdgs.temple.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cdgs.temple.entity.ProvinceEntity;

public interface ProvinceRepository extends CrudRepository<ProvinceEntity, Long> {
	
	List<ProvinceEntity> findAll();

}
