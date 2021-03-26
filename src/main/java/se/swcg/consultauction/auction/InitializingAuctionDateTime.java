package se.swcg.consultauction.auction;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.swcg.consultauction.entity.AuctionDateTime;
import se.swcg.consultauction.service.AuctionServiceImpl;

import java.time.LocalDateTime;

import static se.swcg.consultauction.auction.AuctionConstants.*;

@Component
public class InitializingAuctionDateTime implements InitializingBean {

    @Autowired
    private AuctionServiceImpl auctionService;

    @Override
    public void afterPropertiesSet() throws Exception {

        LocalDateTime today = LocalDateTime.now(ZONE_ID);
        AuctionDateTime foundDateTime = null;

        try {

            foundDateTime = auctionService.findById(AUCTION_ID);

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        if (foundDateTime != null && today.isAfter(foundDateTime.getOfferEndDateTime())) {

            //auctionService.createNextAuctionDateTime(foundDateTime.getOfferEndDateTime());
            return;

        }

        //auctionService.createNextAuctionDateTime(today);
    }
}
