package se.swcg.consultauction.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class AuctionDateTime {

    @Id
    private Long auctionDateTimeId = 1L;

    private LocalDateTime auctionStartDateTime;
    private LocalDateTime auctionEndDateTime;
    private LocalDateTime offerEndDateTime;

    public AuctionDateTime(LocalDateTime auctionStartDateTime, LocalDateTime auctionEndDateTime, LocalDateTime offerEndDateTime) {
        this.auctionStartDateTime = auctionStartDateTime;
        this.auctionEndDateTime = auctionEndDateTime;
        this.offerEndDateTime = offerEndDateTime;
    }

    public AuctionDateTime() {
    }

    public Long getAuctionDateTimeId() {
        return auctionDateTimeId;
    }

    public LocalDateTime getAuctionStartDateTime() {
        return auctionStartDateTime;
    }

    public void setAuctionStartDateTime(LocalDateTime auctionStartDateTime) {
        this.auctionStartDateTime = auctionStartDateTime;
    }

    public LocalDateTime getAuctionEndDateTime() {
        return auctionEndDateTime;
    }

    public void setAuctionEndDateTime(LocalDateTime auctionEndDateTime) {
        this.auctionEndDateTime = auctionEndDateTime;
    }

    public LocalDateTime getOfferEndDateTime() {
        return offerEndDateTime;
    }

    public void setOfferEndDateTime(LocalDateTime offerEndDateTime) {
        this.offerEndDateTime = offerEndDateTime;
    }
}
