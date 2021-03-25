package se.swcg.consultauction.auction;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.swcg.consultauction.entity.AuctionDateTime;
import se.swcg.consultauction.repository.AuctionDateTimeRepository;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;

@Component
public class InitializingAuctionDateTime implements InitializingBean {

    @Autowired
    AuctionDateTimeRepository auctionRepository;

    @Override
    public void afterPropertiesSet() throws Exception {

        ZoneId z = ZoneId.of("Europe/Stockholm");
        LocalDateTime today = LocalDateTime.now(z);

        Optional<AuctionDateTime> foundDateTime = auctionRepository.findById(1L);

        if (foundDateTime.isPresent() && today.isAfter(foundDateTime.get().getOfferEndDateTime())) {

            System.out.println("AuctionDate found");
            saveDateTime(foundDateTime.get().getOfferEndDateTime());
            return;

        }

            System.out.println("AuctionDate not found");
            saveDateTime(today);

    }

    public void saveDateTime (LocalDateTime dateTime) {
        System.out.println("inside saveDateTime");

        LocalDateTime auctionStartDateTime = dateTime
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .with(LocalTime.of(8, 0, 0));

        LocalDateTime auctionEndDateTime = auctionStartDateTime
                .with(TemporalAdjusters.next(DayOfWeek.FRIDAY))
                .with(LocalTime.of(12, 0, 0));

        LocalDateTime offerEndDateTime = auctionStartDateTime
                .with(TemporalAdjusters.next(DayOfWeek.FRIDAY))
                .with(LocalTime.of(17, 0, 0));

        auctionRepository.save(new AuctionDateTime(auctionStartDateTime, auctionEndDateTime, offerEndDateTime));
    }
}
