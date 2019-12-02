export interface Course {
  id?: number;
  no?: number;
  name?: string;
  stDate?: Date;
  endDate?: Date;
  detail?: string;
  conditionMin?: number;
  memberId?: number;
  memberFname?: string;
  memberLname?: string;
  locationId?: number;
  locationName?: string;
  status?: string;
  saStatus?: string;
  mhcStatus?: string;
  canRegister?: number;
}
