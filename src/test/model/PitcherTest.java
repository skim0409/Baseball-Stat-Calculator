package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PitcherTest {
    private Pitcher p;

    @BeforeEach
    void runBefore() {
        p = new Pitcher("Clayton Kershaw", 10, 8, 121.2,
                48, 103, 21, 144);
    }

    @Test
    void testConstructor() {
        assertEquals("Clayton Kershaw", p.getName());
        assertEquals(10, p.getWins());
        assertEquals(8, p.getLosses());
        assertEquals(121.2, p.getInnings());
        assertEquals(48, p.getEr());
        assertEquals(103, p.getHits());
        assertEquals(21, p.getBb());
        assertEquals(144, p.getSo());
        assertEquals(3.55, p.getEra());
        assertEquals(1.019, p.getWhip());
    }

    @Test
    void testCalculateEra() {
        assertEquals(3.55, p.calculateEra(48, 121.2));
    }

    @Test
    void testCalculateWhip() {
        assertEquals(1.019, p.calculateWhip(103, 21, 121.2));
    }

    @Test
    void testToString() {
        assertEquals("Clayton Kershaw     10  8   3.55  121.2  48   103  21  144  1.019",
                p.toString());

    }
}
