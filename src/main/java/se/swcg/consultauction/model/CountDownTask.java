package se.swcg.consultauction.model;

import org.springframework.beans.factory.annotation.Autowired;
import se.swcg.consultauction.entity.CountdownDate;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class CountDownTask {

    @Autowired
    CountdownDate date;

    public void startTime() {
        Timer timer = new Timer();
        timer.schedule(new DeleteAndSet(), 1, 2000);
        //timer.schedule(new DeleteAndSet(), 0, TimeUnit.DAYS.toDays(date.getDeleteTime().getDayOfWeek().getValue()));
    }

}

   /* public void main(String[] args) {
        Timer timer = new Timer();
        LocalDateTime timeNow = LocalDateTime.now();
        LocalDateTime nextSaturday = timeNow.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        timer.schedule(new DeleteAndSet(), 0, TimeUnit.DAYS.toDays(nextSaturday.getDayOfWeek().getValue()));
    }*/

