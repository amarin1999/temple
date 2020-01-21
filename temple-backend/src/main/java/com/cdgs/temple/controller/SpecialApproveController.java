package com.cdgs.temple.controller;

import com.cdgs.temple.dto.CourseDto;
import com.cdgs.temple.dto.CourseScheduleDto;
import com.cdgs.temple.dto.MemberDto;
import com.cdgs.temple.dto.SpecialApproveDto;
import com.cdgs.temple.service.CourseScheduleService;
import com.cdgs.temple.service.CourseService;
import com.cdgs.temple.service.MemberService;
import com.cdgs.temple.service.SpecialApproveService;
import com.cdgs.temple.util.ResponseDto;
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
public class SpecialApproveController {

	private SpecialApproveService specialApproveService;
	private MemberService memberService;
	private CourseService courseService;
	private CourseScheduleService courseScheduleService;

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
			res.setData(dto);
			res.setCode(200);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
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
		SpecialApproveDto dto;
		MemberDto member = memberService.getCurrentMember();
		try {
			body.setMemberId(member.getId());
			body.setStatus("2");
			dto = specialApproveService.create(body);
			if (dto == null) {
				throw new Exception("เงื่อนไขการสมัครไม่ถูกต้อง");
			}
			specialApproves.add(dto);
			res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
			res.setData(specialApproves);
			res.setCode(200);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
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
		SpecialApproveDto specialApprovesDto = new SpecialApproveDto();
		CourseDto courseDto = new CourseDto();
		CourseScheduleDto courseSchedule = new CourseScheduleDto();
		List<SpecialApproveDto> listDto = new ArrayList<>();
		MemberDto member = memberService.getCurrentMember();
		SpecialApproveDto bodydto = new SpecialApproveDto();
		try {
			bodydto.setStatus(body.getStatus());
			for (long said : body.getSpaId()) {
				System.out.println(said);
				bodydto.setSpecialApproveId(said);
				listDto.add(specialApproveService.update(bodydto, member.getId()));
			}
			if (body.getStatus().equals("1")) {
				for (SpecialApproveDto dto : listDto) {
					courseService.updateCourseToEnable(dto.getCourseId());
					specialApprovesDto = specialApproveService.getByCourseIdAndMemberId(dto.getCourseId(),
							dto.getMemberId());
					/**
					 * in Time = null ,out Time != null
					 */
					if (specialApprovesDto != null) {
						courseDto = courseService.getCourse(specialApprovesDto.getCourseId());
						courseSchedule.setCourseId(specialApprovesDto.getCourseId());

						courseSchedule.setCourseScheduleDate(courseDto.getStDate());
						courseScheduleService.createCourseSchedule(courseSchedule);
						courseSchedule.setCourseScheduleDate(courseDto.getEndDate());
						courseScheduleService.createCourseSchedule(courseSchedule);
					}

					registerCourse(dto);
				}
			}
			if (listDto.isEmpty())
				throw new Exception("ไม่สามารถทำรายการได้");
			res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
			res.setData(listDto);
			res.setCode(200);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
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
