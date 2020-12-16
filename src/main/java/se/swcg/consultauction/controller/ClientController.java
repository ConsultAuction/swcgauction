package se.swcg.consultauction.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.swcg.consultauction.dto.ClientDto;
import se.swcg.consultauction.service.ClientService;

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

}
