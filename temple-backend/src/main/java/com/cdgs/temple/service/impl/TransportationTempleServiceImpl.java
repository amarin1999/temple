package com.cdgs.temple.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdgs.temple.dto.TransportationTempleDto;
import com.cdgs.temple.entity.TransportationTempleEntity;
import com.cdgs.temple.repository.TransportationTempleRepository;
import com.cdgs.temple.service.TransportationTempleService;

@Service
public class TransportationTempleServiceImpl implements TransportationTempleService {
	
	private static final Logger log = LoggerFactory.getLogger(TransportationTempleServiceImpl.class);
	
	 @Autowired(required = true)
	 TransportationTempleRepository transportationTempleRepository;
	 
	 /* 
		  * getTransportationTempleName
		  * the function get data Transportation of temple that status true .
		  * 
		  * */
	 public List<TransportationTempleDto> getTransportationTempleName(){
		 List<TransportationTempleEntity> transportationTempleEntity = new ArrayList<TransportationTempleEntity>(); 
		 try {
			 transportationTempleEntity = transportationTempleRepository.findAllByStatusIsTrue();
	        } catch (Exception e) {
	            log.error(e.getMessage());
	        }
	        return mapListEntityToDto(transportationTempleEntity);
	 }
	 
	 /*
	     * createTransportationTemple 
	     * this function insert data to DB.
	     * Params : transportation : TransportationTempleDto
	     * 
	     * */
	 public TransportationTempleDto createTransportationTemple(TransportationTempleDto transportation) {
		 TransportationTempleEntity entity = new TransportationTempleEntity();
		 try {
			 entity = transportationTempleRepository.save(mapDtoToEntity(transportation));
		 }catch (Exception e) {
			 log.error("CreateTransportation Error=>" + e.getMessage());
		}
		 return mapEntityToDto(entity);
	 }
	 
	 /*
	     * updateTransportation 
	     * this function update data to DB.
	     * Params : id : Long, transportation : TransportationTempleDto
	     *
	     * */
	 public TransportationTempleDto updateTransportationTemple (Long id,TransportationTempleDto transportation) {
		 TransportationTempleEntity entity = new TransportationTempleEntity();
		 try {
			 entity = transportationTempleRepository.save(mapDtoToEntity(transportation));
		 }catch (Exception e) {
			 log.error("UpdateTransportation Error=>" + e.getMessage());
		}
		 return mapEntityToDto(entity);
	 }
	 
	 /*
	     * deleteTransportation 
	     * Description : this function update status of transportation from 1 to 0.
	     * Params : transportation : TransportationTempleDto, id : Long
	     * 
	     * */
	 public TransportationTempleDto deleteTransportationTemple (Long id,TransportationTempleDto transportation) {
		 TransportationTempleEntity entity = new TransportationTempleEntity();
		 TransportationTempleEntity temp = new TransportationTempleEntity();
		 try {
			 Optional<TransportationTempleEntity> opTransportation = transportationTempleRepository.findById(id);
			 temp = mapOptionToEntity(opTransportation);
			 temp.setTransportationTempleStatus(false);
			 entity = transportationTempleRepository.save(temp);
		 }catch (Exception e) {
			 log.error("DeleteTransportation Error=>" + e.getMessage());
		}
		 return mapEntityToDto(entity);
	 }
	 
	 /* 
	  * mapListEntityToDto.
	  * this function is mapping list data of Entity to Dto.
	  * Params is id : Long, transportation : TransportationDto */
	 
		private List<TransportationTempleDto> mapListEntityToDto(
				List<TransportationTempleEntity> transportationTempleEntity) {
				List<TransportationTempleDto> TempledtoList = new ArrayList<TransportationTempleDto>();
					 if(transportationTempleEntity!=null) {
						 for(TransportationTempleEntity entity : transportationTempleEntity ) {
							 TempledtoList.add(mapEntityToDto(entity));
						 }
					 }
					 return TempledtoList;
		}
	
	 /*
     * mapEntityToDto 
     * this function is mapping data of Entity to Dto.
     * Params is entity : TransportationEntity
     * */
		 private TransportationTempleDto mapEntityToDto (TransportationTempleEntity entity) {
			 TransportationTempleDto Templedto = new TransportationTempleDto();
			 if(entity!=null) {
				 Templedto.setId(entity.getTransportationTempleId());
				 Templedto.setName(entity.getTransportationTempleName());
				 Templedto.setStatus(entity.isTransportationTempleStatus());
				 Templedto.setTimePickUp(entity.getTransportationTempleTimePickup());
				 Templedto.setTimeSend(entity.getTransportationTempleTimeSend());
				 
			 }
			 return Templedto;
		 }
		 
	/*
	 	* mapEntityToDto 
		* this function is mapping data of Dto to Entity.
		* Params : TempleDto : TransportationTempleDto
		* */
		 private TransportationTempleEntity mapDtoToEntity(TransportationTempleDto TempleDto) {
			 TransportationTempleEntity entity = new TransportationTempleEntity();
			 if(TempleDto != null) {
				 entity.setTransportationTempleId(TempleDto.getId());
				 entity.setTransportationTempleName(TempleDto.getName());
				 entity.setTransportationTempleStatus(TempleDto.isStatus());
				 entity.setTransportationTempleTimePickup(TempleDto.getTimePickUp());
				 entity.setTransportationTempleTimeSend(TempleDto.getTimeSend());
			 }
			 return entity;
		 }
		 
		 /*
		     * mapOptionToEntity 
		     * this function is mapping data of Optional to Entity.
		     * Params : opEntity : Optional<TransportationTempleEntity>
		     * 
		     * */
		 private TransportationTempleEntity mapOptionToEntity(Optional<TransportationTempleEntity> opEntity) {
			 TransportationTempleEntity entity = new TransportationTempleEntity();
			 if(opEntity != null) {
				 entity.setTransportationTempleId(opEntity.get().getTransportationTempleId());
				 entity.setTransportationTempleName(opEntity.get().getTransportationTempleName());
				 entity.setTransportationTempleStatus(opEntity.get().isTransportationTempleStatus());
				 entity.setTransportationTempleTimePickup(opEntity.get().getTransportationTempleTimePickup());
				 entity.setTransportationTempleTimeSend(opEntity.get().getTransportationTempleTimeSend());
			 }
			 return entity;
		 }

 
	
	
	
	
}
