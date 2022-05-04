package persistence;

import model.Batter;
import model.Pitcher;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkBatter(String name, int atbat, int runs, int hits, int doubles, int triples,
                               int hr, int bb, int rbi, Batter b) {
        assertEquals(name, b.getName());
        assertEquals(atbat, b.getAtBat());
        assertEquals(runs, b.getRuns());
        assertEquals(hits, b.getHits());
        assertEquals(doubles, b.getDoubles());
        assertEquals(triples, b.getTriples());
        assertEquals(hr, b.getHr());
        assertEquals(bb, b.getBb());
        assertEquals(rbi, b.getRbi());
    }

    protected void checkPitcher(String name, int wins, int losses, double innings, int er,
                                int hits, int bb, int so, Pitcher p) {
        assertEquals(name, p.getName());
        assertEquals(wins, p.getWins());
        assertEquals(losses, p.getLosses());
        assertEquals(innings, p.getInnings());
        assertEquals(er, p.getEr());
        assertEquals(hits, p.getHits());
        assertEquals(bb, p.getBb());
        assertEquals(so, p.getSo());
    }
}
