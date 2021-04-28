package se.swcg.consultauction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import se.swcg.consultauction.dto.ProjectDto;
import se.swcg.consultauction.model.CreateProjectRequest;
import se.swcg.consultauction.model.UpdateProjectRequest;
import se.swcg.consultauction.service.ProjectService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/project")
@PreAuthorize("hasAuthority('client:read')")
public class ProjectController {

    @Autowired
    private ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<ProjectDto>> findAllProjects(){
        return ResponseEntity.ok(service.findAll());
    }

    /*@GetMapping("/{email}")
    public ResponseEntity<List<ProjectDto>> findAllByContactEmail(@PathVariable String email) {
        return ResponseEntity.ok(service.findAllByContactEmail(email));
    }*/

    @GetMapping("/{clientId}")
    public ResponseEntity<ProjectDto> findByClientId(@PathVariable String clientId){
        return ResponseEntity.ok(service.findById(clientId));
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<ProjectDto>> findProjectByClientId(@PathVariable String clientId) {
        return ResponseEntity.ok(service.findAllProjectByClientId(clientId));
    }

    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@Valid @RequestBody CreateProjectRequest projectRequest) {
        return ResponseEntity.ok(service.createProject(projectRequest));
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable String projectId, @Valid @RequestBody UpdateProjectRequest projectRequest) {
        return ResponseEntity.ok(service.updateProject(projectId, projectRequest));
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId) {
        boolean isRemoved = service.deleteProject(projectId);
        if (!isRemoved) throw new IllegalArgumentException("Something went wrong trying to delete user with id: " + projectId);

        return new ResponseEntity<>("User with id: " + projectId + " was successfully removed.", HttpStatus.OK);
    }
}
