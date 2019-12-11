import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { NgxSpinnerService } from 'ngx-spinner';
import { ForgetPassService } from '../../shared/service/forget-pass.service';


@Component({
	selector: 'app-forget-password',
	templateUrl: './forget-password.component.html',
	styleUrls: ['./forget-password.component.css']
})
export class ForgetPasswordComponent implements OnInit {
	form: FormGroup;
	@Input() formTitle: string;
	@Output() data = new EventEmitter<Object>();
	constructor(
		private forgetPassService: ForgetPassService,
		private formBuilder: FormBuilder,
		private spinner: NgxSpinnerService,
		private messageService: MessageService,
	) { }

	ngOnInit() {
		this.createForm();
	}

	onSubmit(e) {
		this.spinner.show();
		e.preventDefault();
		const email = this.form.get('email').value;
		const username = this.form.get('username').value;
		// console.log(email, username);
		if (this.form.valid) {
			this.spinner.show();
			this.forgetPassService.getUserForgetInfo(email, username).toPromise().then(res => {
				// console.log(res['code']);
				if (res['result'] === 'Success') {
					if (res['code'] == '200') {
						// Do something
						this.messageService.clear();
						this.messageService.add({
							key: 'alert',
							sticky: true,
							severity: 'success',
							summary: "ยืนยันการเปลี่ยนรหัสผ่าน"
						});
					} else if (res['code'] == '204') {
						this.messageService.clear();
						this.messageService.add({
							key: 'alert',
							sticky: true,
							severity: 'warn',
							summary: 'ไม่มี Email หรือ Username นี้อยู่ในระบบ'
						});
					}
				}
			}).catch(err => {
				// Message Not found user or email in system
				if (err['error']['code'] == 401) {
					this.messageService.add({
						key: 'alert',
						sticky: true,
						severity: 'error',
						summary: err['error']['errorMessage']
					});
				} else {
					this.messageService.add({
						key: 'alert',
						sticky: true,
						severity: 'error',
						summary: 'ระบบขัดข้อง โปรดติดต่อผู้ดูแลระบบ'
					});
				}
			}).finally(() => this.spinner.hide());
			this.data.emit({ email, username })
		} else {
			this.spinner.hide();
		}
		
	}

	onConfirm() {
		this.messageService.clear('alert');
	}

	private createForm() {
		this.form = this.formBuilder.group({
			"email": ['', [Validators.required, Validators.email]],
			"username": ['', Validators.required],
		})
	}
}