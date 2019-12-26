package com.cdgs.temple.controller;

import com.cdgs.temple.dto.GraduatedCourseDto;
import com.cdgs.temple.dto.GraduatedDto;
import com.cdgs.temple.dto.MemberDto;
import com.cdgs.temple.dto.MemberHasCourseStatus;
import com.cdgs.temple.dto.ResponseCountDto;
import com.cdgs.temple.service.GraduatedCourseService;
import com.cdgs.temple.service.GraduatedService;
import com.cdgs.temple.service.MemberService;
import com.cdgs.temple.util.ResponseDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/v1/graduated")
public class GraduatedController {
	private static final Logger log = LoggerFactory.getLogger(GraduatedController.class);
	
    private MemberService memberService;
    private GraduatedService graduatedService;
    private GraduatedCourseService graduatedCourseService;

    @Autowired
    public GraduatedController(
            MemberService memberService,
            GraduatedService graduatedService,
            GraduatedCourseService graduatedCourseService
    ) {
        this.memberService = memberService;
        this.graduatedService = graduatedService;
        this.graduatedCourseService = graduatedCourseService;
    }

    @GetMapping(path = "/{courseId}")
    @PreAuthorize("hasRole('monk')")
    public ResponseEntity<ResponseDto<GraduatedDto>> getAllStatus(@PathVariable("courseId") Long courseId) {
        ResponseDto<GraduatedDto> res = new ResponseDto<>();
        MemberDto member = memberService.getCurrentMember();
        List<GraduatedDto> dto;
        try {
            dto = graduatedService.getAll(courseId, member.getId());
            res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
            res.setData(dto);
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
	public ResponseEntity<ResponseDto<GraduatedDto>> Update(@Valid @RequestBody GraduatedDto body) {
		ResponseDto<GraduatedDto> res = new ResponseDto<>();
		MemberDto member = memberService.getCurrentMember();
		GraduatedDto bodydto = new GraduatedDto();
		System.out.println(body.toString());
		try {
			for (MemberHasCourseStatus mhc : body.getMhcList()) {
				bodydto.setcId(body.getcId());
				bodydto.setMhcId(mhc.getMhcId());
				bodydto.setStatus(mhc.getStatus());
				System.out.println("body => " +bodydto.toString());
				graduatedService.update(bodydto, member.getId());
			}
			res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
			res.setCode(200);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
			res.setErrorMessage(e.getMessage());
			res.setCode(400);
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		}
	}
	
    @GetMapping(path = "")
    @PreAuthorize("hasRole('monk')")
    public ResponseEntity<ResponseDto<GraduatedCourseDto>> getAll(@RequestParam("query") String query,@RequestParam("limit") Long limit,@RequestParam("offset") Long offset) {
    	System.out.println(query);
        ResponseDto<GraduatedCourseDto> res = new ResponseDto<>();
        MemberDto member = memberService.getCurrentMember();
        List<GraduatedCourseDto> dto;
        try {
            dto = graduatedCourseService.getAll(query,member.getId(),limit,offset);
            res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
            res.setData(dto);
            res.setCode(200);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
            res.setErrorMessage(e.getMessage());
            res.setCode(400);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }
    
    
    @GetMapping(value = "/count")
    @PreAuthorize("hasRole('monk')")
    public ResponseEntity<ResponseDto<ResponseCountDto>> CountTeacherCoursesApproval() {
        ResponseDto<ResponseCountDto> res = new ResponseDto<>();
        List<ResponseCountDto> listDto = new ArrayList<>();
        ResponseCountDto dto = new ResponseCountDto();
        int count;
        MemberDto member = memberService.getCurrentMember();
        try {
            count = graduatedCourseService.countCourses(member.getId());
            dto.setTotalRecord(count);
            listDto.add(dto);
            res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
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
