package persistence;

import model.Batter;
import model.ListOfBatters;
import model.ListOfPitchers;
import model.Pitcher;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterPitcherTest extends JsonTest {
    @Test
    public void testPitcherWriterInvalidFile() {
        try {
            JsonWriterPitcher jsonWriterPitcher = new JsonWriterPitcher("./data/my\0illegal:fileName.json");
            jsonWriterPitcher.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testPitcherWriterEmptyPD() {
        try {
            ListOfPitchers lp = new ListOfPitchers();
            JsonWriterPitcher writer = new JsonWriterPitcher("./data/testPitcherWriterEmptyPD.json");
            writer.open();
            writer.write(lp);
            writer.close();

            JsonReaderPitcher reader = new JsonReaderPitcher("./data/testPitcherWriterEmptyPD.json");
            lp = reader.read();
            assertEquals(0, lp.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testBatterWriterGeneralBD() {
        try {
            ListOfPitchers lp = new ListOfPitchers();
            lp.addPitcher(new Pitcher("Max Scherzer", 15, 9, 202, 9,130,
                    44, 150));
            lp.addPitcher(new Pitcher("Clayton Kershaw", 10, 8, 121.2,48, 103,
                    21, 144));
            JsonWriterPitcher writer = new JsonWriterPitcher("./data/testPitcherWriterGeneralPD.json");
            writer.open();
            writer.write(lp);
            writer.close();

            JsonReaderPitcher reader = new JsonReaderPitcher("./data/testPitcherWriterGeneralPD.json");
            lp = reader.read();
            assertEquals(2, lp.size());
            checkPitcher("Max Scherzer", 15, 9, 202, 9, 130, 44,
                    150, lp.getPitcherAtIndex(0));
            checkPitcher("Clayton Kershaw", 10, 8, 121.2,48,103,21,
                    144, lp.getPitcherAtIndex(1));

        } catch (IOException e) {
            System.out.println("Exception should not have been thrown");
        }
    }
}
