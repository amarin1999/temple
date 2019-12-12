package com.cdgs.temple.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cdgs.temple.entity.HistoryDharmaEntity;

@Repository
public interface HistoryDharmaRepository extends  CrudRepository<HistoryDharmaEntity, Long>{
	
	@Query(value = "SELECT * "
			+ "FROM history_dharma "
			+ "WHERE history_dharma_member_id =: memberId", nativeQuery = true)
	List<HistoryDharmaEntity> getHistoryDhamaByMemberId(@Param("memberId") String memberId);

}
