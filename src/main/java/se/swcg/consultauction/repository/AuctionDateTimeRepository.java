package se.swcg.consultauction.repository;

import org.springframework.data.repository.CrudRepository;
import se.swcg.consultauction.entity.AuctionDateTime;

public interface AuctionDateTimeRepository extends CrudRepository<AuctionDateTime, Long> {
}
