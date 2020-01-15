package com.cdgs.temple.repository;

import com.cdgs.temple.entity.TempSpecialApproveEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TempSpecialApproveRepository extends CrudRepository<TempSpecialApproveEntity, Long> {

    @Query(value = "SELECT sa.special_approve_id,sa.member_id,sa.spa_detail, CONCAT(t.title_name,m.member_fname,' ',m.member_lname) AS display_name, "
    		+ "tr.tran_id, tr.tran_name AS transportation "
    		+ "FROM special_approve sa "
    		+ "INNER JOIN transportations tr ON sa.tran_id=tr.tran_id "
    		+ "INNER JOIN courses_teacher ct ON sa.course_id=ct.course_id "
    		+ "INNER JOIN members m ON sa.member_id=m.member_id "
    		+ "INNER JOIN title_names t ON m.member_title_id=t.title_id "
    		+ "WHERE sa.spa_status='2' AND ct.member_id=:memberId AND sa.course_id=:courseId", nativeQuery = true)
    List<TempSpecialApproveEntity> getAll(@Param("memberId") Long memberId, @Param("courseId") Long courseId);


    @Query(value = "SELECT sa.special_approve_id,sa.member_id,sa.spa_detail,"
    		+ "		CONCAT(t.title_name,m.member_fname,' ',m.member_lname) as display_name"
    		+ "		,(SELECT t.tran_name FROM transportations t "
    		+ "		inner join sensations s on t.tran_id=s.tran_id "
    		+ "		where s.sense_id=sa.sense_id) as transportation "
    		+ "		FROM special_approve sa "
    		+ "		LEFT JOIN courses_teacher ct ON ct.course_id = sa.course_id AND ct.member_id =:memberId"
    		+ "		LEFT JOIN members m ON m.member_id = sa.member_id"
    		+ "		LEFT JOIN title_names t ON m.member_title_id=t.title_id "
    		+ "		WHERE sa.spa_status = '4' AND sa.course_id =:courseId", nativeQuery = true)
    List<TempSpecialApproveEntity> getMemberOutTime(@Param("memberId") Long memberId, @Param("courseId") Long courseId);
}
