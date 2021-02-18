package se.swcg.consultauction.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.swcg.consultauction.dto.SkillsDto;
import se.swcg.consultauction.service.SkillsService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/skills")
public class SkillsController {

    @Autowired
    private SkillsService service;

    public SkillsController(SkillsService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<SkillsDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillsDto> findById(@PathVariable String id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<SkillsDto>> findByName(@PathVariable String name){
        return ResponseEntity.ok(service.findByName(name));
    }

}
