package com.cdgs.temple.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdgs.temple.dto.MemberDto;
import com.cdgs.temple.dto.ReportGenDto;
import com.cdgs.temple.service.MemberService;
import com.cdgs.temple.service.ReportGenService;
import com.cdgs.temple.util.ResponseDto;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/dashboard")
public class DashboardController {
	private ReportGenService reportGenService;
	private MemberService memberService;

	@Autowired
	public DashboardController(ReportGenService reportGenService, MemberService memberService) {
		super();
		this.reportGenService = reportGenService;
		this.memberService = memberService;
	}

	@GetMapping(path = "")
	@PreAuthorize("hasRole('monk') or hasRole('user')")
	public ResponseEntity<ResponseDto<ReportGenDto>> getAllUsers() {
		ResponseDto<ReportGenDto> res = new ResponseDto<>();
		try {
			List<ReportGenDto> listDto = new ArrayList<>();
			MemberDto member = memberService.getCurrentMember();
			ReportGenDto reportGenDto = null;
			if (member.getRoleId() == Long.parseLong("2")) {
				reportGenDto = reportGenService.getReportDashboardMonkData();
			} else if (member.getRoleId() == Long.parseLong("3")) {
				reportGenDto = reportGenService.getReportDashboardUserData(member.getId());
			}
			res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
			listDto.add(reportGenDto);
			res.setData(listDto);
			res.setCode(200);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
			res.setErrorMessage(e.getMessage());
			res.setCode(400);
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		}
	}
}
