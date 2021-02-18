package se.swcg.consultauction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.swcg.consultauction.dto.ProjectDto;
import se.swcg.consultauction.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/project")
public class ProjectController {

    @Autowired
    private ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<ProjectDto>> findAllByContactEmail(@PathVariable String email) {
        return ResponseEntity.ok(service.findAllByContactEmail(email));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> findById(@PathVariable String id){
        return ResponseEntity.ok(service.findById(id));
    }
}
