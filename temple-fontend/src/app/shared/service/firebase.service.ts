import { Injectable } from '@angular/core';
import { AngularFirestore } from '@angular/fire/firestore';
import { map } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class FirebaseService {

  constructor(private db: AngularFirestore) { }
  getCountNoticeByUserID(userId: number) {
    return this.db.collection('notification', param => param.where('memberID', '==', userId).where('notificationStatus', '==', 0))
      .snapshotChanges().pipe(map(data => data.length));
  }
  getDataNoticeByUserID(userId: number) {
    return this.db.collection('notification', param => param.where('memberID', '==', userId).where('notificationStatus', '==', 0))
      .snapshotChanges().pipe(map(data => data.map(dataMap=>dataMap.payload.doc.data())));
  }

}
