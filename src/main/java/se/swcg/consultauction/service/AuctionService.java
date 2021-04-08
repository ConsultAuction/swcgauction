package se.swcg.consultauction.service;

import se.swcg.consultauction.entity.AuctionDateTime;

import java.time.LocalDateTime;

public interface AuctionService {

    AuctionDateTime getDateTime ();
    void createNextAuctionDateTime(LocalDateTime previousLastDay);
    boolean checkIfAuctionIsAllowed();
}
