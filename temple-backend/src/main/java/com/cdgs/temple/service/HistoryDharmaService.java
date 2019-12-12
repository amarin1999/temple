package com.cdgs.temple.service;

import java.util.List;

import com.cdgs.temple.dto.HistoryDharmaDto;

public interface HistoryDharmaService {
	HistoryDharmaDto createHistoryDhama(HistoryDharmaDto body);
	
	List<HistoryDharmaDto> getAll();
	List<HistoryDharmaDto> getHistoryDhamaByMemberId();
	
	HistoryDharmaDto createHistoryDharma(HistoryDharmaDto body) throws Exception;
}
