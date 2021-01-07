package se.swcg.consultauction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.swcg.consultauction.dto.ProgrammingLanguagesDto;
import se.swcg.consultauction.service.ProgrammingLanguagesService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/languages")
public class ProgrammingLanguagesController {

    @Autowired
    ProgrammingLanguagesService programmingLanguagesService;

    @GetMapping
    public ResponseEntity<List<ProgrammingLanguagesDto>> findAll() {
        return ResponseEntity.ok(programmingLanguagesService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgrammingLanguagesDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(programmingLanguagesService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProgrammingLanguagesDto> create(@Valid @RequestBody ProgrammingLanguagesDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(programmingLanguagesService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgrammingLanguagesDto> update(@PathVariable String id, @RequestBody ProgrammingLanguagesDto updated) {
        if (!updated.getLanguagesId().equals(id)) throw new IllegalArgumentException("Id does not match.");

        return ResponseEntity.ok(programmingLanguagesService.update(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        boolean isRemoved = programmingLanguagesService.delete(id);

        if (!isRemoved) throw new IllegalArgumentException("Something went wrong trying to delete language with id: " + id);

        return new ResponseEntity<>("Language with id: " + id + " was successfully removed.", HttpStatus.OK);
    }
}
