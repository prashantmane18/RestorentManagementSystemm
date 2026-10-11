package com.restorentInfo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.restorentInfo.dto.RestorentDTO;
import com.restorentInfo.mapper.RestorentMapper;
import com.restorentInfo.model.Restorent;
import com.restorentInfo.repository.RestoRepository;

@Service
public class RestoService 
{
	@Autowired
	private RestoRepository restorepository;
	
	@Autowired
	private RestorentMapper mapper;
	
	
	public List<RestorentDTO> getAllResto() {

	    Iterable<Restorent> restaurants = restorepository.findAll();

	    List<Restorent> restaurantList = new ArrayList<>();
	    restaurants.forEach(restaurantList::add);

	    return restaurantList.stream()
	            .map(mapper::toDTO)
	            .collect(Collectors.toList());
	}
	
	public RestorentDTO getRestoById(String id) {
	    Restorent resto = restorepository.findById(id)
	        .orElseThrow(() -> new ResponseStatusException
	        		(
	            HttpStatus.NOT_FOUND,
	            "Restaurant not found with id: " + id
	        ));

	    return mapper.toDTO(resto);
	}
	
	public RestorentDTO saveResto(RestorentDTO dto) {
        Restorent resto = mapper.toEntity(dto);

        return mapper.toDTO(restorepository.save(resto));
    }
	
	public RestorentDTO updateResto(String id, RestorentDTO dto) {
        Restorent resto = restorepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Restaurant not found with id: " + id
                ));

        resto.setName(dto.name());
        resto.setAddress(dto.address());
        resto.setBest(dto.bestDish());
        resto.setRating(dto.rating());

        return mapper.toDTO(restorepository.save(resto));
    }
	
	 public void deleteRestoById(String id) {
	        Restorent resto = restorepository.findById(id)
	                .orElseThrow(() -> new ResponseStatusException(
	                        HttpStatus.NOT_FOUND,
	                        "Restaurant not found with id: " + id
	                ));

	        restorepository.delete(resto);
	    }
}
