package com.cdgs.temple.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cdgs.temple.entity.CourseEntity;
import com.cdgs.temple.entity.NotificationsEntity;

public interface NotificationsRepository extends CrudRepository<NotificationsEntity, Long> {
	@Query(value = "select * from courses"
			     + " where course_create_date < date_add(now() , INTERVAL +1 MONTH)"
			     + " order by course_create_date desc", nativeQuery = true)
	List<CourseEntity> getAllDataPreviouspast();
}