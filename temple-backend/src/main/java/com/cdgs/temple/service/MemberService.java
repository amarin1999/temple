package com.cdgs.temple.service;

import java.util.List;

import com.cdgs.temple.dto.MemberDto;

public interface MemberService {
	List<MemberDto> getMembers();
	MemberDto getMember(Long id);
	MemberDto createMember(MemberDto body);
	MemberDto updateMember(Long id,MemberDto body);
	MemberDto getCurrentMember();
	
    List<MemberDto> getTeacher();
    
    MemberDto createMemberByAdmin(MemberDto body);
    MemberDto updateMemberByAdmin(Long id,MemberDto body);
}
