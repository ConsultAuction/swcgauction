package se.swcg.consultauction.auction;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class AuctionConstants {

    //Sätter id för AuctionDateTime så att det bara finns en
    public static final Long AUCTION_ID = 1L;

    public static final ZoneId ZONE_ID = ZoneId.of("Europe/Stockholm");

    public static final TemporalAdjuster AUCTION_DAY_START = TemporalAdjusters.next(DayOfWeek.MONDAY);
    public static final TemporalAdjuster AUCTION_DAY_END = TemporalAdjusters.next(DayOfWeek.FRIDAY);

    //Vilken tid auktionen startar
    public static final LocalTime AUCTION_TIME_START = LocalTime.of(8,0);
    //Vilken tid auktionen slutar och project offers börjar
    public static final LocalTime AUCTION_TIME_END = LocalTime.of(12,0);
    //Vilken tid project offers slutar
    public static final LocalTime OFFER_TIME_END = LocalTime.of(17,0);
}
