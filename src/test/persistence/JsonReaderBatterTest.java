package persistence;

import model.ListOfBatters;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class JsonReaderBatterTest extends JsonTest{

    @Test
    public void testBatterReaderNonExistenceFile() {
        JsonReaderBatter jsonReaderBatter = new JsonReaderBatter("./data/noFile.json");
        try {
            ListOfBatters lb = jsonReaderBatter.read();
            fail("IOException is expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testBatterReaderEmptyBatterData() {
        JsonReaderBatter jsonReaderBatter = new JsonReaderBatter("./data/testBatterReaderEmptyBatterData.json");
        try {
            ListOfBatters lb = jsonReaderBatter.read();
            assertEquals(0, lb.size());
        } catch (IOException e) {
            fail("Could not read from file");
        }
    }

    @Test
    public void testBatterReaderGeneralBD() {
        JsonReaderBatter jsonReaderBatter = new JsonReaderBatter("./data/testBatterReaderGeneralBD.json");
        try {
            ListOfBatters lb = jsonReaderBatter.read();
            assertEquals(2, lb.size());
            checkBatter("Sean Kim", 555, 123, 122, 33, 2, 22,
                    44, 77, lb.getBatterAtIndex(0));
            checkBatter("Mike Trout", 444, 55, 88,12,3,33,
                    55,99, lb.getBatterAtIndex(1));
        } catch (IOException e) {
            fail("Could not read from file");
        }
    }
}
