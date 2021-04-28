package se.swcg.consultauction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.swcg.consultauction.auction.AuctionConstants;
import se.swcg.consultauction.entity.AuctionDateTime;
import se.swcg.consultauction.entity.ProjectOffer;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.repository.AuctionDateTimeRepository;

import java.time.LocalDateTime;

import static se.swcg.consultauction.auction.AuctionConstants.*;

@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    private AuctionDateTimeRepository auctionRepository;

    @Autowired
    private ProjectOfferService projectOfferService;

    public AuctionDateTime getDateTime() {
        return auctionRepository.findById(AUCTION_ID)
                .orElseThrow(() -> new UsernameNotFoundException("Could not find an auction date time."));
    }

    @Override
    public void createNextAuctionDateTime(LocalDateTime previousLastDay) {
        auctionRepository.deleteAll();

        LocalDateTime auctionStartDateTime = previousLastDay
                .with(AUCTION_DAY_START)
                .with(AUCTION_TIME_START);

        // Takes the day from auctionStart so it will use the endDay in same week.
        LocalDateTime auctionEndDateTime = auctionStartDateTime
                .with(AUCTION_DAY_END)
                .with(AUCTION_TIME_END);

        // Uses same day as auctionEnd but a later time.
        LocalDateTime offerEndDateTime = auctionEndDateTime
                .with(OFFER_TIME_END);

        auctionRepository.save(new AuctionDateTime(auctionStartDateTime, auctionEndDateTime, offerEndDateTime));
    }

    public boolean checkIfAuctionIsAllowed() {
        LocalDateTime dateTimeNow = LocalDateTime.now();
        AuctionDateTime auctionDateTime = getDateTime();

        return dateTimeNow.isAfter(auctionDateTime.getAuctionStartDateTime())
                && dateTimeNow.isBefore(auctionDateTime.getAuctionEndDateTime());
    }

    @Override
    public void deleteAllOffersWithSelectedFalse() {
        projectOfferService.deleteAllSelectedWithFalse();
    }
}
