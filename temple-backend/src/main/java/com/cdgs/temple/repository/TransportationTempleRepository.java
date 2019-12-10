package com.cdgs.temple.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.cdgs.temple.entity.TransportationTempleEntity;

public interface TransportationTempleRepository  extends CrudRepository<TransportationTempleEntity, Long> {
	
	@Query(value ="SELECT * FROM transportation_temple WHERE transportation_temple_status = '1'", nativeQuery = true)
	List<TransportationTempleEntity> findAllByStatusIsTrue();
	
	Optional<TransportationTempleEntity> findById(Long id);
}
