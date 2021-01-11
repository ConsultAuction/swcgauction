package se.swcg.consultauction.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.swcg.consultauction.dto.AdminDto;
import se.swcg.consultauction.dto.ClientDto;
import se.swcg.consultauction.dto.ClientForm;
import se.swcg.consultauction.entity.Client;
import se.swcg.consultauction.service.AdminService;
import se.swcg.consultauction.service.ClientService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(path = "/api/client")
public class ClientController {

    @Autowired
    private ClientService  clientService;

    @GetMapping
    public ResponseEntity<List<ClientDto>> findAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ClientDto> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(clientService.findByEmail(email));
    }


    @PostMapping
    public ResponseEntity<ClientDto> create(@Valid @RequestBody ClientDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable String id, @RequestBody ClientDto updated) {
        if (!updated.getClientId().equals(id)) throw new IllegalArgumentException("Id does not match.");

        return ResponseEntity.ok(clientService.update(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        boolean isRemoved = clientService.delete(id);
        if (!isRemoved) throw new IllegalArgumentException("Something went wrong trying to delete client with id: " + id);

        return new ResponseEntity<>("Client with id: " + id + " was successfully removed.", HttpStatus.OK);
    }

}
