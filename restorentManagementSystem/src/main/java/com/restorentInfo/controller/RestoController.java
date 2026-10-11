
package com.restorentInfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restorentInfo.dto.RestorentDTO;
import com.restorentInfo.service.RestoService;

@RestController
@RequestMapping("/restoapi")
public class RestoController {

	@Autowired
    private RestoService restoService;



    @GetMapping("/viewall")
    public List<RestorentDTO> fetchAllResto() {
        return restoService.getAllResto();
    }

    @GetMapping("/{id}")
    public RestorentDTO fetchRestoById(@PathVariable String id) {
        return restoService.getRestoById(id);
    }

    @PostMapping("/addresto")
    public RestorentDTO save(@RequestBody RestorentDTO dto) {
        return restoService.saveResto(dto);
    }

    @PutMapping("/updateresto/{id}")
    public RestorentDTO updateResto(
            @PathVariable String id,
            @RequestBody RestorentDTO dto) {
        return restoService.updateResto(id, dto);
    }

    @DeleteMapping("/deleteresto/{id}")
    public void deleteResto(@PathVariable String id) {
        restoService.deleteRestoById(id);
    }
}
