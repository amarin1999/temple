package com.cdgs.temple.service.impl;

import com.cdgs.temple.dto.NotificationsDto;
import com.cdgs.temple.service.NotificationsService;
import com.cdgs.temple.util.FirebaseConnection;
import com.google.cloud.firestore.Firestore;

public class NotificationsServiceImpl implements NotificationsService {
	private Firestore database = new FirebaseConnection().getFirestoreDatabase();

	public NotificationsServiceImpl() {
		super();
	}

	@Override
	public void createNotifications(NotificationsDto body) {
		try {
			database.collection("notification").add(body);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
