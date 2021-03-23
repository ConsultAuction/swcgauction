package se.swcg.consultauction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import se.swcg.consultauction.dto.UserDto;
import se.swcg.consultauction.entity.ConsultantDetails;
import se.swcg.consultauction.model.CreateClientRequest;
import se.swcg.consultauction.model.CreateConsultantRequest;
import se.swcg.consultauction.service.UserService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
@PreAuthorize("hasAuthority('user:read')")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    /*@GetMapping("/language/{language}")
    public ResponseEntity<List<UserDto>> findByLanguage(@PathVariable String language) {
        return ResponseEntity.ok(service.findByLanguage(language));
    }

    @GetMapping("/email/{id}")
    public ResponseEntity<UserDto> findByEmail(@PathVariable String id) {
        return ResponseEntity.ok(service.findByEmail(id));
    }

    @GetMapping("/lastActive/{date}")
    public ResponseEntity<List<UserDto>> findByLastActive(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return ResponseEntity.ok(service.findByLastActive(date));
    }

    @GetMapping("/active/{active}")
    public ResponseEntity<List<UserDto>> findAllByActive(@PathVariable boolean active) {
        return ResponseEntity.ok(service.findByActive(active));
    }

    @GetMapping("/available/{available}")
    public ResponseEntity<List<UserDto>> findByAvailable(@PathVariable boolean available) {
        return ResponseEntity.ok(service.findByAvailable(available));
    }*/

    @GetMapping("/consultant")
    @PreAuthorize("hasAuthority('client:read')")
    public ResponseEntity<List<UserDto>> findAllConsultants() {
        return ResponseEntity.ok(service.findAllConsultants());
    }

    @PostMapping("/client")
    public ResponseEntity<UserDto> createClient(@Valid @RequestBody CreateClientRequest clientRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createClient(clientRequest));
    }

    @PutMapping("/client/{id}")
    @PreAuthorize("hasAuthority('client:write')")
    public ResponseEntity<UserDto> updateClient(@PathVariable String id,@Valid @RequestBody CreateClientRequest clientRequest) {
        //if (!updatedDto.getUserId().equals(id)) throw new IllegalArgumentException("Id does not match.");

        return ResponseEntity.ok(service.updateClient(id, clientRequest));
    }

    @PostMapping("/consultant")
    public ResponseEntity<?> createConsultant(@Valid @RequestBody CreateConsultantRequest consultantRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createConsultant(consultantRequest));
    }

    @PutMapping("/consultant/{id}")
    @PreAuthorize("hasAuthority('consultant:write')")
    public ResponseEntity<?> updateConsultant(@PathVariable String id,@Valid @RequestBody CreateConsultantRequest consultantRequest) {
        //if (!updatedDto.getUserId().equals(id)) throw new IllegalArgumentException("Id does not match.");

        return ResponseEntity.ok(service.updateConsultant(id, consultantRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        /*boolean isRemoved = service.delete(id);
        if (!isRemoved) throw new IllegalArgumentException("Something went wrong trying to delete user with id: " + id);

        return new ResponseEntity<>("User with id: " + id + " was successfully removed.", HttpStatus.OK);*/

        /* service.delete(id);
        return ResponseEntity.noContent().build();*/
        return null;
    }
}
