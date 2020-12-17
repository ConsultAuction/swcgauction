package se.swcg.consultauction.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.swcg.consultauction.dto.ClientDto;
import se.swcg.consultauction.service.ClientService;

import java.util.Collection;
import java.util.Locale;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/client")
public class ClientController {

    public static final String ALL = "ALL";
    public static final String ID = "ID";

    private ClientService service;

    @Autowired
    public ClientController(ClientService service){
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable String id){

        return ResponseEntity.ok(service.findById(id));

    }


    @GetMapping
    public ResponseEntity<?> find(
            @RequestParam(name = "type", defaultValue = ALL) final String type,
            @RequestParam(name = "value",defaultValue = ALL) final String value
    ){
        switch (type.toUpperCase().trim()) {


            case ID:
                return ResponseEntity.ok(service.findById(value));

            case ALL:
                Collection<ClientDto> found = service.findAll();
                return found.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(found);

            default:
                return ResponseEntity.badRequest().body("Not a valid type: " + type);

        }

    }

}
