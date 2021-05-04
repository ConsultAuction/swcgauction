package se.swcg.consultauction.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import se.swcg.consultauction.service.AuctionServiceImpl;

import java.time.LocalDateTime;

@Component
public class AuctionScheduler {

    @Autowired
    AuctionServiceImpl auctionService;

    //Körs varje fredag klockan 17
    //Tar bort alla offers som inte är selected = true, skapar nya datum för nästa vecka
    @Scheduled(cron = "0 0 17 ? * FRI", zone="Europe/Stockholm")
    public void newDateTimeTask() {
        auctionService.deleteAllOffersWithSelectedFalse();
        auctionService.createNextAuctionDateTime(LocalDateTime.now());
    }
}
