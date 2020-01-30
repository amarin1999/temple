export interface Notifications {
    courseID: number;
    detail: string;
    memberID: number;
    notificationStatus: number;
    notificationTime: firebase.firestore.Timestamp;
    specialApproveID: number;
}

