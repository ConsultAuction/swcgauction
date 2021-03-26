package se.swcg.consultauction.model;

import org.springframework.beans.factory.annotation.Autowired;
import se.swcg.consultauction.entity.CountdownDate;
import se.swcg.consultauction.service.CountdownService;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.TimerTask;

public class DeleteAndSet  extends TimerTask {

    public DeleteAndSet() {}

    CountdownService service;
    @Autowired
    public DeleteAndSet(CountdownService service) {
        this.service = service;
    }

    @Override
    public void run(){
        service.deleteCountdown();
        LocalDateTime timeNow = LocalDateTime.now();
        LocalDateTime lastMonday = timeNow.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        LocalDateTime nextMonday = timeNow.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        LocalDateTime nextFriday = timeNow.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        LocalDateTime nextSaturday = timeNow.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        CountdownDate date;
        if (lastMonday.isAfter(LocalDateTime.now().minusDays(2))) {
            date = new CountdownDate(lastMonday.withHour(8), nextFriday.withHour(12), nextSaturday.withHour(1));
        } else {
            date = new CountdownDate(nextMonday.withHour(8), nextFriday.withHour(12), nextSaturday.withHour(1));
        }
        service.createCountdown(date);
    }
}
