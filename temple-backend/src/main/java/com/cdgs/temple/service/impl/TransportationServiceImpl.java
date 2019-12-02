package com.cdgs.temple.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdgs.temple.dto.TransportationDto;
import com.cdgs.temple.entity.TransportationEntity;
import com.cdgs.temple.repository.TransportationRepository;
import com.cdgs.temple.service.TransportationService;

@Service
public class TransportationServiceImpl implements TransportationService {
	
	 private static final Logger log = LoggerFactory.getLogger(TransportationServiceImpl.class);
	 
	 @Autowired(required = true)
	 TransportationRepository transportationRepository;
	 
	 
	 /*
	     * getTransportationName 
	     * Description : this function get data of Transportation by status is true.
	     * Params : -
	     * create : 23/09/2562 By Korawit Ratyiam 
	     * */
	 public List<TransportationDto> getTransportationName(){
		 List<TransportationEntity> transportationEntity = new ArrayList<TransportationEntity>(); 
		 try {
			 transportationEntity = transportationRepository.findAllByStatusIsTrue();
	        } catch (Exception e) {
	            log.error(e.getMessage());
	        }
	        return mapListEntityToDto(transportationEntity);
	 }
	 
	 /*
	     * createTransportation 
	     * Description : this function insert data to DB.
	     * Params : transporatation : TransporatationDto
	     * create : 23/09/2562 By Korawit Ratyiam 
	     * */
	 public TransportationDto createTransportation(TransportationDto transporatation) {
		 TransportationEntity entity = new TransportationEntity();
		 try {
			 entity = transportationRepository.save(mapDtoToEntity(transporatation));
		 }catch (Exception e) {
			 log.error("CreateTransportation Error=>" + e.getMessage());
		}
		 return mapEntityToDto(entity);
	 }
	 
	 /*
	     * updateTransportation 
	     * Description : this function update data to DB.
	     * Params : id : Long, transporatation : TransporatationDto
	     * create : 23/09/2562 By Korawit Ratyiam 
	     * */
	 public TransportationDto updateTransportation (Long id,TransportationDto transportation) {
		 TransportationEntity entity = new TransportationEntity();
		 try {
			 entity = transportationRepository.save(mapDtoToEntity(transportation));
		 }catch (Exception e) {
			 log.error("UpdateTransportation Error=>" + e.getMessage());
		}
		 return mapEntityToDto(entity);
	 }
	 
	 /*
	     * deleteTransportation 
	     * Description : this function update status of transportation from 1 to 0.
	     * Params : body : TransportationDto, id : Long
	     * create : 23/09/2562 By Korawit Ratyiam
	     * PS. : This controller can't not delete data because this data is foreign Key of Sensation
	     * */
	 public TransportationDto deleteTransportation (Long id,TransportationDto transportation) {
		 TransportationEntity entity = new TransportationEntity();
		 TransportationEntity temp = new TransportationEntity();
		 try {
			 Optional<TransportationEntity> opTransportation = transportationRepository.findById(id);
			 temp = mapOptionToentity(opTransportation);
			 temp.setTransportationStatus(false);
			 entity = transportationRepository.save(temp);
		 }catch (Exception e) {
			 log.error("DeleteTransportation Error=>" + e.getMessage());
		}
		 return mapEntityToDto(entity);
	 }
	 
	 
	 /*
	     * mapListEntityToDto 
	     * Description : this function is mapping list data of Entity to Dto.
	     * Params : id : Long, transporatation : TransporatationDto
	     * create : 23/09/2562 By Korawit Ratyiam 
	     * */
	 private List<TransportationDto> mapListEntityToDto(List<TransportationEntity> entities) {
		 List<TransportationDto> dtoList = new ArrayList<TransportationDto>();
		 if(entities!=null) {
			 for(TransportationEntity entity : entities ) {
				 dtoList.add(mapEntityToDto(entity));
			 }
		 }
		 return dtoList;
	 }
	 
	 /*
	     * mapEntityToDto 
	     * Description : this function is mapping data of Entity to Dto.
	     * Params : entity : TransportationEntity
	     * create : 23/09/2562 By Korawit Ratyiam 
	     * */
	 private TransportationDto mapEntityToDto (TransportationEntity entity) {
		 TransportationDto dto = new TransportationDto();
		 if(entity!=null) {
			 dto.setId(entity.getTransportationId());
			 dto.setName(entity.getTransportationName());
			 dto.setStatus(entity.isTransportationStatus());
		 }
		 return dto;
	 }
	 
	 /*
	     * mapEntityToDto 
	     * Description : this function is mapping data of Dto to Entity.
	     * Params : dto : TransportationDto
	     * create : 23/09/2562 By Korawit Ratyiam 
	     * */
	 private TransportationEntity mapDtoToEntity(TransportationDto dto) {
		 TransportationEntity entity = new TransportationEntity();
		 if(dto != null) {
			 entity.setTransportationId(dto.getId());
			 entity.setTransportationName(dto.getName());
			 entity.setTransportationStatus(dto.isStatus());
		 }
		 return entity;
	 }
	 
	 /*
	     * mapOptionToentity 
	     * Description : this function is mapping data of Optional to Entity.
	     * Params : opEntity : Optional<TransportationEntity>
	     * create : 23/09/2562 By Korawit Ratyiam 
	     * */
	 private TransportationEntity mapOptionToentity(Optional<TransportationEntity> opEntity) {
		 TransportationEntity entity = new TransportationEntity();
		 if(opEntity != null) {
			 entity.setTransportationId(opEntity.get().getTransportationId());
			 entity.setTransportationName(opEntity.get().getTransportationName());
			 entity.setTransportationStatus(opEntity.get().isTransportationStatus());
		 }
		 return entity;
	 }

}
