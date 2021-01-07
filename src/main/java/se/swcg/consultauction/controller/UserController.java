package se.swcg.consultauction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.swcg.consultauction.dto.UserDto;
import se.swcg.consultauction.entity.User;
import se.swcg.consultauction.service.UserDtoConversionService;
import se.swcg.consultauction.service.UserService;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserDtoConversionService converter;

    private static final String ID = "id";
    private static final String LANGUAGE = "language";
    private static final String ALL = "all";

    public UserController(UserService service , UserDtoConversionService converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping
    public ResponseEntity<?> find(
            @RequestParam(name = "type", defaultValue = ALL) final String type,
            @RequestParam(name = "value", defaultValue = ALL) final String value
    ){
        switch (type.toLowerCase().trim()){
            case ID: ResponseEntity.ok().body(service.findById(value));

            case LANGUAGE: ResponseEntity.ok().body(service.findByLanguage(value));

            case ALL: ResponseEntity.ok().body(service.findAll());

            default: return ResponseEntity.badRequest().body("not a valid type: " + type.trim());
        }
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto dto){
        User newUser =  new User(dto.getFirstName(), dto.getLastName(), dto.getEmail(),
                true, LocalDate.now(), LocalDate.now(),
                false, dto.getPassword(), "USER", dto.getPhoneNumber(),
                dto.getImage(), dto.getMinPrice(), dto.getAddress(), dto.getQualifications());
        dto = service.create(converter.userToDto(newUser));
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable String id, @RequestBody UserDto updatedDto) {
        if (!updatedDto.getUserId().equals(id)) throw new IllegalArgumentException("Id does not match.");

        return ResponseEntity.ok(service.update(updatedDto));
    }
}
