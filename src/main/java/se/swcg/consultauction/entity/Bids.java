package se.swcg.consultauction.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Bids {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String bidId;

    private double price;

    public Bids(String bidId, double price) {
        this.bidId = bidId;
        this.price = price;
    }

    public Bids(double price) {
        this.price = price;
    }

    public Bids() {
    }

    public String getBidId() {
        return bidId;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bids bids = (Bids) o;
        return Double.compare(bids.price, price) == 0 && Objects.equals(bidId, bids.bidId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bidId, price);
    }

    @Override
    public String toString() {
        return "Bids{" +
                "bidId='" + bidId + '\'' +
                ", price=" + price +
                '}';
    }
}
