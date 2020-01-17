package com.cdgs.temple.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdgs.temple.entity.ReportGenEntity;

@Repository
public interface ReportGenRepository extends CrudRepository<ReportGenEntity, Long> {

//	@Query(value = "SELECT c.course_id AS courseId, c.course_name AS courseName, "
//			+ "COUNT(CASE WHEN g.gender_id = '2' THEN 'หญิง' END) AS genderM, "
//			+ "COUNT(CASE WHEN g.gender_id = '2' THEN 'หญิง' END) AS genderF, "
//			+ "COUNT(CASE WHEN g.gender_id = '3' THEN 'อื่นๆ' END) AS genderOther, "
//			+ "COUNT(CASE WHEN t.tran_time_id is Null THEN 'อื่นๆ' END) AS transSelf, "
//			+ "COUNT(CASE WHEN t.tran_time_id is NOT Null THEN 'อื่นๆ' END) AS transTemple, "
//			+ "COUNT(CASE WHEN (SELECT COUNT(*) FROM members_has_courses mhcs WHERE mhcs.mhc_status IN ('0','1') AND mhcs.member_id = mhc.member_id) > 0 THEN NULL ELSE 1 END) as newStudent, "
//			+ "COUNT(CASE WHEN p.province_id = 69 AND r.region_id = '4' THEN 1 ELSE NULL END) as bangkok, "
//			+ "COUNT(CASE WHEN p.province_id != 69 AND r.region_id = '4' THEN 1 ELSE NULL END) as center, "
//			+ "COUNT(CASE WHEN r.region_id = '2' AND p.province_id = '19' THEN 1 ELSE NULL END) as sakonnakhon, "
//			+ "COUNT(CASE WHEN r.region_id = '2' AND p.province_id != '19' THEN 1 ELSE NULL END) as northeast, "
//			+ "COUNT(CASE WHEN r.region_id = '1' THEN 1 ELSE NULL END) as north, "
//			+ "COUNT(CASE WHEN r.region_id = '5' THEN 1 ELSE NULL END) as east, "
//			+ "COUNT(CASE WHEN r.region_id = '3' THEN 1 ELSE NULL END) as west, "
//			+ "COUNT(CASE WHEN r.region_id = '6' THEN 1 ELSE NULL END) as south "
//			+ "FROM members_has_courses mhc\r\n" + "LEFT JOIN members m ON mhc.member_id = m.member_id "
//			+ "LEFT JOIN courses c ON mhc.course_id = c.course_id "
//			+ "LEFT JOIN gender g ON m.member_gender_id = g.gender_id "
//			+ "LEFT JOIN transportations t ON mhc.tran_id = t.tran_id "
//			+ "LEFT JOIN province p ON m.member_province_id = p.province_id "
//			+ "LEFT JOIN region r ON p.region_id = r.region_id "
//			+ "WHERE 1=1 "
//			+ "GROUP BY c.course_id", nativeQuery = true)
	@Query(nativeQuery = true, name = "getAllDataReport")
	List<ReportGenEntity> getAllDataReport();
	
	@Query(nativeQuery = true, name = "getDataReportByCourseId")
	List<ReportGenEntity> getDataReportByCourseId(Long CourseId);

}
