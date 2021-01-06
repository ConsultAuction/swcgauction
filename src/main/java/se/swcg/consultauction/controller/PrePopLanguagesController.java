package se.swcg.consultauction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.swcg.consultauction.dto.PrePopLanguagesDto;
import se.swcg.consultauction.service.PrePopLanguagesService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/languages")
public class PrePopLanguagesController {

    @Autowired
    PrePopLanguagesService prePopLanguagesService;

    @GetMapping
    public ResponseEntity<List<PrePopLanguagesDto>> findAll() {
        return ResponseEntity.ok(prePopLanguagesService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrePopLanguagesDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(prePopLanguagesService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PrePopLanguagesDto> create(@Valid @RequestBody PrePopLanguagesDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(prePopLanguagesService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrePopLanguagesDto> update(@PathVariable String id, @RequestBody PrePopLanguagesDto updated) {
        if (!updated.getLanguagesId().equals(id)) throw new IllegalArgumentException("Id does not match.");

        return ResponseEntity.ok(prePopLanguagesService.update(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        boolean isRemoved = prePopLanguagesService.delete(id);

        if (!isRemoved) throw new IllegalArgumentException("Something went wrong trying to delete language with id: " + id);

        return new ResponseEntity<>("Language with id: " + id + " was successfully removed.", HttpStatus.OK);
    }
}
