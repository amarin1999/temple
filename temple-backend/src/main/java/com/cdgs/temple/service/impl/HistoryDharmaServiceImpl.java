package com.cdgs.temple.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdgs.temple.dto.HistoryDharmaDto;
import com.cdgs.temple.entity.HistoryDharmaEntity;
import com.cdgs.temple.repository.HistoryDharmaRepository;
import com.cdgs.temple.service.HistoryDharmaService;

@Service
public class HistoryDharmaServiceImpl implements HistoryDharmaService {
	
	@Autowired(required = true)
	private final HistoryDharmaRepository historyDhamaRepository;
	
	public HistoryDharmaServiceImpl(HistoryDharmaRepository historyDharmaRepository) {
		this.historyDhamaRepository = historyDharmaRepository;
	}
	
	@Override
	public List<HistoryDharmaDto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HistoryDharmaDto> getHistoryDhamaByMemberId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HistoryDharmaDto createHistoryDharma(HistoryDharmaDto body) throws Exception {
		HistoryDharmaEntity entity = new HistoryDharmaEntity();
		try {
			if (body != null) {
				entity = historyDhamaRepository.save(mapDtoToEntity(body));
			}
			return mapEntityToDto(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	
	private HistoryDharmaDto mapEntityToDto(HistoryDharmaEntity entity) throws Exception {
		HistoryDharmaDto dto = new HistoryDharmaDto();
		try {
			if(entity != null) {
				dto.setId(entity.getHistoryDharmaId());
				dto.setCourseName(entity.getHistoryDharmaDesc());
				dto.setLocation(entity.getHistoryDharmaLocation());
				dto.setMemberId(entity.getHistoryDharmaMemberId());
			}
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	
	private HistoryDharmaEntity mapDtoToEntity(HistoryDharmaDto dto) throws Exception {
		HistoryDharmaEntity entity = new HistoryDharmaEntity();
		System.out.println(dto.toString());
		try {
//				entity.setHistoryDharmaId(dto.getId());
				entity.setHistoryDharmaDesc(dto.getCourseName());
				entity.setHistoryDharmaLocation(dto.getLocation());
				entity.setHistoryDharmaMemberId(dto.getMemberId());
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public HistoryDharmaDto createHistoryDhama(HistoryDharmaDto body) {
		// TODO Auto-generated method stub
		return null;
	}

}
