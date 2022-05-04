package persistence;

import model.ListOfBatters;
import model.ListOfPitchers;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderPitcherTest extends JsonTest{
    @Test
    public void testPitcherReaderNonExistenceFile() {
        JsonReaderPitcher jsonReaderPitcher = new JsonReaderPitcher("./data/noFile.json");
        try {
            ListOfPitchers lp = jsonReaderPitcher.read();
            fail("IOException is expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testPitcherReaderEmptyPD() {
        JsonReaderPitcher jsonReaderPitcher = new JsonReaderPitcher("./data/testPitcherReaderEmptyPD.json");
        try {
            ListOfPitchers lb = jsonReaderPitcher.read();
            assertEquals(0, lb.size());
        } catch (IOException e) {
            fail("Could not read from file");
        }
    }

    @Test
    public void testPitcherReaderGeneralPD() {
        JsonReaderPitcher jsonReaderPitcher = new JsonReaderPitcher("./data/testPitcherReaderGeneralPD.json");
        try {
            ListOfPitchers lp = jsonReaderPitcher.read();
            assertEquals(2, lp.size());
            checkPitcher("Max Scherzer", 15, 9, 202, 9, 130, 44,
                    150, lp.getPitcherAtIndex(0));
            checkPitcher("Clayton Kershaw", 10, 8, 121.2,48,103,21,
                    144, lp.getPitcherAtIndex(1));
        } catch (IOException e) {
            fail("Could not read from file");
        }
    }
}
