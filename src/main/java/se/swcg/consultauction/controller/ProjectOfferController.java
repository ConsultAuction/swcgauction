package se.swcg.consultauction.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.swcg.consultauction.dto.ProjectOfferDto;
import se.swcg.consultauction.dto.UserDto;
import se.swcg.consultauction.model.CreateProjectOfferRequest;
import se.swcg.consultauction.service.ProjectOfferService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@Transactional
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

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<ProjectOfferDto>> findByClientId(@PathVariable String clientId){
        return ResponseEntity.ok(service.findByClientId(clientId));
    }

    @GetMapping("/user/{consultantId}")
    public ResponseEntity<List<ProjectOfferDto>> findBySelectedByUserId(@PathVariable String consultantId){
        return ResponseEntity.ok(service.findByConsultantId(consultantId));
    }

    @PostMapping("/accepted/{offerId}")
    public ResponseEntity<ProjectOfferDto> setAccepted(@PathVariable String offerId){
        return ResponseEntity.ok(service.setAccepted(offerId));
    }

    @PostMapping("/rejected/{offerId}")
    public ResponseEntity<ProjectOfferDto> setRejected(@PathVariable String offerId){
        return ResponseEntity.ok(service.setRejected(offerId));
    }

    @PostMapping("/selected/{offerId}")
    public ResponseEntity<ProjectOfferDto> setSelected(@PathVariable String offerId){
        return ResponseEntity.ok(service.setSelected(offerId));
    }

    @PostMapping
    public ResponseEntity<ProjectOfferDto> createProjectOffer(@Valid @RequestBody CreateProjectOfferRequest projectOfferRequest) {
        return ResponseEntity.ok(service.createProjectOffer(projectOfferRequest));
    }
}