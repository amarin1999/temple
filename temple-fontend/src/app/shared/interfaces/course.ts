import { TransportationTemple } from './transportation-temple';

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
  transportTemple?: TransportationTemple;
  status?: string;
  saStatus?: string;
  mhcStatus?: string;
  canRegister?: number;
}
