package se.swcg.consultauction.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class CountdownDate {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String countDownId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime deleteTime;

    public CountdownDate(String countDownId, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime deleteTime) {
        this.countDownId = countDownId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.deleteTime = deleteTime;
    }

    public CountdownDate(LocalDateTime startTime, LocalDateTime endTime, LocalDateTime deleteTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.deleteTime = deleteTime;
    }

    public CountdownDate() {}

    public String getCountDownId() {
        return countDownId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(LocalDateTime deleteTime) {
        this.deleteTime = deleteTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountdownDate that = (CountdownDate) o;
        return Objects.equals(countDownId, that.countDownId) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(deleteTime, that.deleteTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countDownId, startTime, endTime, deleteTime);
    }

    @Override
    public String toString() {
        return "CountDownDate{" +
                "countDownId='" + countDownId + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", deleteTime=" + deleteTime +
                '}';
    }

    private void Create(){

    }

    private void end(){

    }

    private void delete(){

    }
}
