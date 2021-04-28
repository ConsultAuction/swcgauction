package se.swcg.consultauction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.swcg.consultauction.entity.AuctionDateTime;
import se.swcg.consultauction.service.AuctionService;

@RestController
@RequestMapping(path = "/api/auction")
@PreAuthorize("hasAuthority('user:read')")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @GetMapping
    public ResponseEntity<AuctionDateTime> getDateTime() {
        return ResponseEntity.ok(auctionService.getDateTime());
    }
}
