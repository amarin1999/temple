package com.cdgs.temple.controller;

import com.cdgs.temple.dto.CourseDto;
import com.cdgs.temple.dto.CourseScheduleDto;
import com.cdgs.temple.dto.MemberDto;
import com.cdgs.temple.dto.SpecialApproveDto;
import com.cdgs.temple.service.CourseScheduleService;
import com.cdgs.temple.service.CourseService;
import com.cdgs.temple.service.EmailService;
import com.cdgs.temple.service.MemberService;
import com.cdgs.temple.service.NotificationsService;
import com.cdgs.temple.service.SmsService;
import com.cdgs.temple.service.SpecialApproveService;
import com.cdgs.temple.service.impl.EmailServiceImpl;
import com.cdgs.temple.service.impl.NotificationsServiceImpl;
import com.cdgs.temple.service.impl.SmsServiceImpl;
import com.cdgs.temple.util.ResponseDto;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/approve")
@Slf4j
public class SpecialApproveController {

	private SpecialApproveService specialApproveService;
	private MemberService memberService;
	private CourseService courseService;
	private CourseScheduleService courseScheduleService;
	private NotificationsService notificationsService = new NotificationsServiceImpl();
	private EmailService emailService = new EmailServiceImpl();
	private SmsService smsService = new SmsServiceImpl();

	@Autowired
	public SpecialApproveController(SpecialApproveService specialApproveService, MemberService memberService,
			CourseService courseService, CourseScheduleService courseScheduleService) {
		this.specialApproveService = specialApproveService;
		this.memberService = memberService;
		this.courseService = courseService;
		this.courseScheduleService = courseScheduleService;
	}

	@GetMapping(path = "/{courseId}")
	@PreAuthorize("hasRole('monk')")
	public ResponseEntity<ResponseDto<SpecialApproveDto>> getAll(@PathVariable("courseId") Long courseId) {
		List<SpecialApproveDto> dto;
		ResponseDto<SpecialApproveDto> res = new ResponseDto<>();
		MemberDto member = memberService.getCurrentMember();
		try {
			dto = specialApproveService.getAll(member.getId(), courseId);
			res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
			if (!dto.isEmpty()) {
				res.setData(dto);
				res.setCode(200);
				return new ResponseEntity<>(res, HttpStatus.OK);
			} else {
				res.setCode(204);
				return new ResponseEntity<>(res, HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			log.error("getAll", e);
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
			res.setErrorMessage(e.getMessage());
			res.setCode(400);
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "outTime/{courseId}")
	@PreAuthorize("hasRole('monk')")
	public ResponseEntity<ResponseDto<SpecialApproveDto>> getAllOutTime(@PathVariable("courseId") Long courseId) {
		List<SpecialApproveDto> dto;
		ResponseDto<SpecialApproveDto> res = new ResponseDto<>();
		MemberDto member = memberService.getCurrentMember();
		try {
			dto = specialApproveService.getMemberOutTime(member.getId(), courseId);
			res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
			res.setData(dto);
			res.setCode(200);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			log.error("getAllOutTime", e);
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
			res.setErrorMessage(e.getMessage());
			res.setCode(400);
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "/outTime")
	public ResponseEntity<ResponseDto<CourseDto>> getCourseOutTimeByMemberId(
			@RequestParam("approveType") String approveType) {
		MemberDto member = memberService.getCurrentMember();
		ResponseDto<CourseDto> res = new ResponseDto<>();
		List<CourseDto> listDto = new ArrayList<>();
		try {
			if (approveType.equals("Wait")) {
				listDto = courseService.getCourseOutTimeFromSpecialApproveIdByMemberId(member.getId());
			} else {
				listDto = courseService.getCourseOutTimeFromSpecialApproveSuccess(member.getId());
			}
			res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
			res.setData(listDto);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			log.error("getCourseOutTimeByMemberId", e);
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
			res.setErrorMessage(e.getMessage());
			res.setCode(400);
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(path = "")
	@PreAuthorize("hasRole('user')")
	public ResponseEntity<ResponseDto<SpecialApproveDto>> create(@Valid @RequestBody SpecialApproveDto body) {
		ResponseDto<SpecialApproveDto> res = new ResponseDto<>();
		List<SpecialApproveDto> specialApproves = new ArrayList<>();
		SpecialApproveDto spaDto;
		CourseDto courseDto;
		MemberDto member = memberService.getCurrentMember();
		try {
			body.setMemberId(member.getId());
			body.setStatus("2");
			spaDto = specialApproveService.create(body);
			if (spaDto == null) {
				throw new Exception("เงื่อนไขการสมัครไม่ถูกต้อง");
			}
			specialApproves.add(spaDto);
			res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
			res.setData(specialApproves);
			res.setCode(200);

			courseDto = courseService.getCourse(spaDto.getCourseId());

			// เพิ่มข้อมูล notification ไปให้ ผู้สอน
			notificationsService.createMonkNotifications(courseDto.getTeacher(), spaDto.getSpecialApproveId(),
					courseDto.getId(), spaDto.getStatus(), courseDto.getName());

			String subject = "รายงานการสมัครคอร์ส";
			String text = "มีผู้สมัครคอร์ส " + courseDto.getName();

			// ส่ง email ไปให้ผู้สอน
			for (MemberDto teacher : courseDto.getTeacherList()) {
				if (null != teacher.getEmail()) {
					emailService.sendEmail(teacher.getEmail(), subject, text);
				}

				// ส่ง sms ให้ผู้สอน
				if (null != teacher.getTel()) {
					smsService.sendSms(teacher.getTel(), text);
				}
			}

			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			log.error("create", e);
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
			res.setErrorMessage(e.getMessage());
			res.setCode(400);
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(path = "")
	@PreAuthorize("hasRole('monk')")
	public ResponseEntity<ResponseDto<SpecialApproveDto>> update(@Valid @RequestBody SpecialApproveDto body) {
		ResponseDto<SpecialApproveDto> res = new ResponseDto<>();
		SpecialApproveDto specialApprovesDto;
		CourseDto courseDto = new CourseDto();
		CourseScheduleDto courseSchedule = new CourseScheduleDto();
		List<SpecialApproveDto> listDto = new ArrayList<>();
		MemberDto member = memberService.getCurrentMember();

		try {
			for (long said : body.getSpaId()) {
				SpecialApproveDto bodydto = new SpecialApproveDto();
				bodydto.setStatus(body.getStatus());
				bodydto.setSpecialApproveId(said);
				listDto.add(specialApproveService.update(bodydto, member.getId()));
			}
			for (SpecialApproveDto dto : listDto) {
				specialApprovesDto = specialApproveService.getByCourseId(dto.getCourseId());
				courseDto = courseService.getCourse(specialApprovesDto.getCourseId());

				if (body.getStatus().equals("1")) {
					courseService.updateCourseToEnable(dto.getCourseId());
					/**
					 * in Time = null ,out Time != null
					 */
					// add course schedule
					if (null != specialApprovesDto) {
						courseSchedule.setCourseId(specialApprovesDto.getCourseId());
						courseSchedule.setCourseScheduleDate(courseDto.getStDate());
						courseScheduleService.createCourseSchedule(courseSchedule);
						courseSchedule.setCourseScheduleDate(courseDto.getEndDate());
						courseScheduleService.createCourseSchedule(courseSchedule);
					}

					registerCourse(dto);
				}
			}
			if (listDto.isEmpty()) {
				throw new Exception("ไม่สามารถทำรายการได้");
			}
			res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
			res.setData(listDto);
			res.setCode(200);

			for (long saId : body.getSpaId()) {
				SpecialApproveDto saDto = specialApproveService.getById(saId);
				CourseDto cDto = courseService.getCourseById(saDto.getCourseId());

				// เพิ่ม notification ให้ user
				notificationsService.createUserNotifications(saDto.getMemberId(), saId, saDto.getCourseId(),
						saDto.getStatus(), cDto.getName(), body.getRejectComment());

				String subject = "รายงานการสมัครคอร์ส";
				String text = "คอร์ส " + courseDto.getName();

				MemberDto user = memberService.getMember(saDto.getMemberId());

				if (body.getStatus().equals("1")) {
					text += " ได้รับการอนุมัติแล้ว";
				} else if (body.getStatus().equals("0")) {
					text += " ไม่ผ่านการอนุมัติ";
					if (null != body.getRejectComment() && !body.getRejectComment().isEmpty()) {
						text += " เนื่องจาก " + body.getRejectComment();
					}
				}

				// ส่ง email ไปให้ user
				if (null != user.getEmail()) {
					emailService.sendEmail(user.getEmail(), subject, text);
				}

				// ส่ง sms ให้ผู้สอน
				if (null != user.getTel()) {
					smsService.sendSms(user.getTel(), text);
				}
			}

			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (

		Exception e) {
			log.error("update", e);
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
			res.setErrorMessage(e.getMessage());
			res.setCode(400);
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(path = "/outTime")
	@PreAuthorize("hasRole('user') OR hasRole('monk')")
	public ResponseEntity<ResponseDto<SpecialApproveDto>> cancelApproveOutTime(
			@RequestParam("courseId") Long courseId) {
		ResponseDto<SpecialApproveDto> res = new ResponseDto<>();
		SpecialApproveDto dto = new SpecialApproveDto();
		MemberDto member = memberService.getCurrentMember();
		try {
			dto = specialApproveService.getApproveByCourseIdAndMemberId(courseId, member.getId());
			if (Boolean.TRUE.equals(specialApproveService.cancelApproveOutTime(dto.getSpecialApproveId()))) {
				res.setResult("Success");
				res.setCode(200);
				return new ResponseEntity<>(res, HttpStatus.OK);
			} else {
				res.setResult("Fail");
				return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("cancelApproveOutTime", e);
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
			res.setErrorMessage(e.getMessage());
			res.setCode(400);
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(path = "/{courseId}")
	@PreAuthorize("hasRole('user')")
	public ResponseEntity<ResponseDto<SpecialApproveDto>> userDelete(@PathVariable("courseId") Long courseId) {
		ResponseDto<SpecialApproveDto> res = new ResponseDto<>();
		List<SpecialApproveDto> specialApproves = new ArrayList<>();
		SpecialApproveDto dto;
		MemberDto member = memberService.getCurrentMember();
		System.out.println(courseId);
		try {
			dto = specialApproveService.delete(courseId, member.getId());
			resSuccess(res, specialApproves, dto);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			log.error("userDelete", e);
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
			res.setErrorMessage(e.getMessage());
			res.setCode(400);
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		}
	}

	private void resSuccess(ResponseDto<SpecialApproveDto> res, List<SpecialApproveDto> specialApproves,
			SpecialApproveDto dto) throws Exception {
		if (dto == null)
			throw new Exception("ไม่สามารถทำรายการได้");
		specialApproves.add(dto);
		res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
		res.setData(specialApproves);
		res.setCode(200);
	}

	private boolean registerCourse(SpecialApproveDto dto) {
		return specialApproveService.approve(dto);
	}
}
