package se.swcg.consultauction.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BidsTest {

    private Bids bid;

    @BeforeEach
    void setup(){
         bid = new Bids("321",400.80);
    }

    @Test
    void testBid_created_successfully() {

        assertEquals("321",bid.getBidId());
        assertEquals(400.80,bid.getPrice());

    }

    @Test
    void test_hashCode_and_equals() {

        Bids copy = bid;
        assertEquals(copy,bid);
        assertEquals(copy.hashCode(),bid.hashCode());

    }

    @Test
    public void test_toString_contains_correct_information() {

        String toString = bid.toString();

        assertTrue(toString.contains("321"));
        assertEquals(bid.getPrice(),400.80);

    }

}
