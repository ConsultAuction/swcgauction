package se.swcg.consultauction.service;

import se.swcg.consultauction.entity.CountdownDate;

public interface CountdownService {

    CountdownDate createCountdown(CountdownDate countdownDate);
    void deleteCountdown();

}
