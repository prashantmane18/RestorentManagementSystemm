
package com.restorentInfo.mapper;

import org.springframework.stereotype.Component;

import com.restorentInfo.dto.RestorentDTO;
import com.restorentInfo.model.Restorent;

@Component
public class RestorentMapper {

    public RestorentDTO toDTO(Restorent r) {
        return new RestorentDTO(
            r.getId(),
            r.getName(),
            r.getAddress(),
            r.getBest(),
            r.getRating()
        );
    }

    public Restorent toEntity(RestorentDTO dto) {
        Restorent r = new Restorent();

        r.setName(dto.name());
        r.setAddress(dto.address());
        r.setBest(dto.bestDish());
        r.setRating(dto.rating());

        return r;
    }
}
