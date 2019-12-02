package com.cdgs.temple.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cdgs.temple.entity.TransportationEntity;



public interface TransportationRepository extends CrudRepository<TransportationEntity, Long> {
	
	@Query(value ="SELECT * FROM transportations WHERE tran_status = '1'", nativeQuery = true)
	List<TransportationEntity> findAllByStatusIsTrue();
	
	Optional<TransportationEntity> findById(Long id);

}
