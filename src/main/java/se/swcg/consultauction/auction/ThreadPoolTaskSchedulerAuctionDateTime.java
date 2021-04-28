/*
package se.swcg.consultauction.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import se.swcg.consultauction.entity.AuctionDateTime;
import se.swcg.consultauction.repository.AuctionDateTimeRepository;
import se.swcg.consultauction.service.AuctionServiceImpl;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static se.swcg.consultauction.auction.AuctionConstants.*;

@Component
public class ThreadPoolTaskSchedulerAuctionDateTime {

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    @Autowired
    private AuctionServiceImpl auctionService;

    @PostConstruct
    public void scheduleRunnable() {

        AuctionDateTime auctionDateTime = null;

        try {
            auctionDateTime = auctionService.findById(AUCTION_ID);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        if (auctionDateTime != null) {
            System.out.println("inside if");
            taskScheduler.scheduleAtFixedRate(
                    new RunnableTask(auctionService, auctionDateTime.getOfferEndDateTime()),
                    Date.from(
                            auctionDateTime.getOfferEndDateTime().atZone(ZONE_ID).toInstant()
                    ),
                    1000
            );

        }
    }

    static class RunnableTask implements Runnable {

        private AuctionServiceImpl auctionService;
        private LocalDateTime newDateTime;

        public RunnableTask(AuctionServiceImpl auctionService, LocalDateTime newDateTime) {
            this.auctionService = auctionService;
            this.newDateTime = newDateTime;
        }

        @Override
        public void run() {
            auctionService.createNextAuctionDateTime(newDateTime);
            System.out.println("run");
            System.out.println("run");
            System.out.println("run");
            System.out.println("run");
            System.out.println("run");
            System.out.println("run");
        }
    }
}
*/
