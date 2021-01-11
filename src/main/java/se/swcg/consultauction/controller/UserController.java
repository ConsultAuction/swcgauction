package se.swcg.consultauction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.swcg.consultauction.dto.UserDto;
import se.swcg.consultauction.service.UserService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    @Autowired
    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/language/{language}")
    public ResponseEntity<List<UserDto>> findByLanguage(@PathVariable String language) {
        return ResponseEntity.ok(service.findByLanguage(language));
    }

    @GetMapping("/lastActive/{date}")
    public ResponseEntity<List<UserDto>> findByLastActive(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return ResponseEntity.ok(service.findByLastActive(date));
    }

    @GetMapping("/active/{active}")
    public ResponseEntity<List<UserDto>> findAllByActive(@PathVariable boolean active) {
        return ResponseEntity.ok(service.findAllByActive(active));
    }

    @GetMapping("/available/{available}")
    public ResponseEntity<List<UserDto>> findByAvailable(@PathVariable boolean available) {
        return ResponseEntity.ok(service.findByAvailable(available));
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto dto){
       /* return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));*/
        UserDto newUser =  new UserDto(null, dto.getFirstName(), dto.getLastName(), dto.getEmail(),
                true, LocalDate.now(), LocalDate.now(),
                false, dto.getPassword(), "USER", dto.getPhoneNumber(),
                dto.getImage(), dto.getMinPrice(), dto.getAddress(), dto.getQualifications());
        dto = service.create(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable String id, @RequestBody UserDto updatedDto) {
        if (!updatedDto.getUserId().equals(id)) throw new IllegalArgumentException("Id does not match.");

        return ResponseEntity.ok(service.update(updatedDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        boolean isRemoved = service.delete(id);
        if (!isRemoved) throw new IllegalArgumentException("Something went wrong trying to delete user with id: " + id);

        return new ResponseEntity<>("User with id: " + id + " was successfully removed.", HttpStatus.OK);
       /* service.delete(id);
        return ResponseEntity.noContent().build();*/
    }
}
