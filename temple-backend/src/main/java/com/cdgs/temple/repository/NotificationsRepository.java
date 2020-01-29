package com.cdgs.temple.repository;

import org.springframework.data.repository.CrudRepository;

import com.cdgs.temple.entity.NotificationsEntity;

public interface NotificationsRepository extends CrudRepository<NotificationsEntity, Long> {
	
}