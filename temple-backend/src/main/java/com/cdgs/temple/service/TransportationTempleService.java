package com.cdgs.temple.service;

import java.util.List;

import com.cdgs.temple.dto.TransportationTempleDto;



public interface TransportationTempleService {
	List<TransportationTempleDto> getTransportationTempleName();
	
	List<TransportationTempleDto> getTransportationTempleNameCouse(Long id);
		
	TransportationTempleDto createTransportationTemple(TransportationTempleDto transportation);
		
	TransportationTempleDto updateTransportationTemple (Long id,TransportationTempleDto transportation);
		
	TransportationTempleDto deleteTransportationTemple (Long id,TransportationTempleDto transportation);

}
