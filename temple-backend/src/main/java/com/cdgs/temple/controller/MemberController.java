package com.cdgs.temple.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdgs.temple.dto.MemberDto;
import com.cdgs.temple.service.MemberService;
import com.cdgs.temple.util.ResponseDto;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/members")
public class MemberController {
	
	// start member
	private	final MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping(path = "")
	@PreAuthorize("hasRole('admin') or hasRole('monk') ")
	public ResponseEntity<ResponseDto<MemberDto>> getMembers() {
		System.out.println("getAllUsers");
		List<MemberDto> dto ;
		ResponseDto<MemberDto> res = new ResponseDto<>();
		try {
			dto = memberService.getMembers();
			res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
			res.setData(dto);
			res.setCode(200);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {

			res.setErrorMessage(e.getMessage());
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());

			res.setCode(400);
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(path = "/getAllUsersWithOutImg")
	@PreAuthorize("hasRole('admin') or hasRole('monk') ")
	public ResponseEntity<ResponseDto<MemberDto>> getAllUsersWithOutImg() {
		System.out.println("getAllUsersWithOutImg");
		List<MemberDto> dto ;
		ResponseDto<MemberDto> res = new ResponseDto<>();
		try {
			dto = memberService.getMembers();
			res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
			res.setData(dto);
			res.setCode(200);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			res.setErrorMessage(e.getMessage());
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
			res.setCode(400);
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "/monk")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<ResponseDto<MemberDto>> getTeacher(){
		List<MemberDto> dto ;
		ResponseDto<MemberDto> res = new ResponseDto<>();
		try {
			dto = memberService.getTeacher();
			res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
			res.setData(dto);
			res.setCode(200);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {

			res.setErrorMessage(e.getMessage());
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());

			res.setCode(400);
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "/{id}")
	@PreAuthorize("hasRole('admin') or hasRole('monk') or hasRole('user')")
	public ResponseEntity<ResponseDto<MemberDto>> getMember(@PathVariable("id") Long id) {
		List<MemberDto> dto = new ArrayList<>();
		ResponseDto<MemberDto> res = new ResponseDto<>();
		MemberDto member = memberService.getCurrentMember();
		MemberDto member2;
		try {
			if(member.getRoleName().equals("user")){
				member2 = memberService.getMember(member.getId());
			}else{
				member2 = memberService.getMember(id);
			}
			dto.add(member2);
			res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
			res.setData(dto);
			res.setCode(200);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			res.setErrorMessage(e.getMessage());
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
			res.setCode(400);
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		}
	}


	@PutMapping(path = "/{id}")
	@PreAuthorize("hasRole('admin') or hasRole('monk') or hasRole('user')")
	public ResponseEntity<ResponseDto<MemberDto>> putMembers(@PathVariable("id") Long id,@Valid @RequestBody MemberDto body) {
		ResponseDto<MemberDto> res = new ResponseDto<>();
		List<MemberDto> members = new ArrayList<>();
		MemberDto member1 = memberService.getCurrentMember();
		MemberDto member ;
		try {
			if(member1.getRoleName().equals("admin")){
				member = memberService.updateMember(id,body);
			}else{
				member = memberService.updateMember(member1.getId(),body);
			}
			if (member != null) {
				members.add(member);
			} 
			res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
			res.setData(members);
			res.setCode(201);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
			res.setErrorMessage(e.getMessage());
			res.setCode(400);
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		}
	}
	// end member
	
    @PostMapping(path = "/registerByAdmin")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<ResponseDto<MemberDto>> registerByAdmin(@Valid @RequestBody MemberDto body) {
        ResponseDto<MemberDto> res = new ResponseDto<>();
        List<MemberDto> members = new ArrayList<>();
        MemberDto member;
        try {
            member = memberService.createMemberByAdmin(body);
            if (!(member == null)) {
                members.add(member);
            }
            res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
            res.setData(members);
            res.setCode(201);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
            res.setErrorMessage(e.getMessage());
            res.setCode(400);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping(path = "/updateByAdmin/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<ResponseDto<MemberDto>> updateByAdmin(@PathVariable("id") Long id,@Valid @RequestBody MemberDto body) {
        ResponseDto<MemberDto> res = new ResponseDto<>();
        List<MemberDto> members = new ArrayList<>();
        MemberDto member;
        try {
            member = memberService.updateMemberByAdmin(id, body);
            if (!(member == null)) {
                members.add(member);
            }
            res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
            res.setData(members);
            res.setCode(201);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
            res.setErrorMessage(e.getMessage());
            res.setCode(400);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }
}
