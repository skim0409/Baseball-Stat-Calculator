package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BatterTest {
    private Batter b;
    @BeforeEach
    void runBefore() {
        b = new Batter("Mike Trout", 117, 23, 39,
                8,1,8,27, 18);
    }
    @Test
    void testConstructor() {
        assertEquals("Mike Trout", b.getName());
        assertEquals(117, b.getAtBat());
        assertEquals(23, b.getRuns());
        assertEquals(39, b.getHits());
        assertEquals(8, b.getDoubles());
        assertEquals(1, b.getTriples());
        assertEquals(8, b.getHr());
        assertEquals(27, b.getBb());
        assertEquals(18, b.getRbi());
        assertEquals(0.333, b.getAvg());
        assertEquals(0.458, b.getObp());
        assertEquals(0.624, b.getSlg());
        assertEquals(1.082, b.getOps());
    }

    @Test
    void testCalculateAvg() {
        assertEquals(0.333, b.calculateAvg(117, 39));
    }

    @Test
    void testCalculateOBP() {
        Batter b1 = new Batter("Patrick Horn", 432, 70, 101,
                34, 3, 18, 55, 66);
        assertEquals(0.32, b1.calculateOBP(101, 55, 432));

        assertEquals(0.458, b.calculateOBP(39, 27, 117));
    }

    @Test
    void testCalculateSLG() {
        assertEquals(0.624, b.calculateSLG(39, 8,1,8, 117));
    }

    @Test
    void testToString() {
        assertEquals("Mike Trout           117 23  39  27 18  8   1   8   0.333  0.458  0.624  1.082",
                b.toString());

    }


}
