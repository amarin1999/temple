import { Time } from '@angular/common';

export interface Transportation {
    id?: number;
    name?: string;
    status?: number;
    typeTrans?: number;
    timePickUp?: Date;
    timeSend?: Date;
}
