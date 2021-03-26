package se.swcg.consultauction.service;

import org.springframework.stereotype.Service;
import se.swcg.consultauction.entity.CountdownDate;
import se.swcg.consultauction.repository.CountdownRepository;

@Service
public class CountdownServiceImpl implements CountdownService{

    private final CountdownRepository countdownRepo;

    public CountdownServiceImpl(CountdownRepository countdownRepo) {
        this.countdownRepo = countdownRepo;
    }

    @Override
    public CountdownDate createCountdown(CountdownDate countdownDate) {
        return countdownRepo.save(countdownDate);
    }

    @Override
    public void deleteCountdown() {
        countdownRepo.deleteAll();
    }
}
