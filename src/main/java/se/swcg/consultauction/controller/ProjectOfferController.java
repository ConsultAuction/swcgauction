package se.swcg.consultauction.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.swcg.consultauction.dto.ProjectOfferDto;
import se.swcg.consultauction.dto.UserDto;
import se.swcg.consultauction.service.ProjectOfferService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/projectOffer")
public class ProjectOfferController {

    @Autowired
    private ProjectOfferService service;

    public ProjectOfferController(ProjectOfferService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProjectOfferDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectOfferDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/accepted/{user}")
    public ResponseEntity<List<ProjectOfferDto>> findByAcceptedByUserId(@PathVariable UserDto user){
        return ResponseEntity.ok(service.findByAcceptedByUserId(user));
    }

    @GetMapping("/rejected/{user}")
    public ResponseEntity<List<ProjectOfferDto>> findByRejectByUserId(@PathVariable UserDto user){
        return ResponseEntity.ok(service.findByRejectByUserId(user));
    }

    @GetMapping("/selected/{user}")
    public ResponseEntity<List<ProjectOfferDto>> findBySelectedByUserId(@PathVariable UserDto user){
        return ResponseEntity.ok(service.findBySelectedByUserId(user));
    }

}
