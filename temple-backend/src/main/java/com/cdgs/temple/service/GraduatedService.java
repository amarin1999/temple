package com.cdgs.temple.service;

import com.cdgs.temple.dto.GraduatedDto;
import com.cdgs.temple.dto.SpecialApproveDto;

import java.util.List;

public interface GraduatedService {
    List<GraduatedDto> getAll(Long courseId, Long monkId);
    
    boolean update(GraduatedDto body, Long id);
}
