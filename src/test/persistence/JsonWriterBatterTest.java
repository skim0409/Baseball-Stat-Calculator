package persistence;

import model.Batter;
import model.ListOfBatters;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



public class JsonWriterBatterTest extends JsonTest{
    @Test
    public void testBatterWriterInvalidFile() {
        try {
            JsonWriterBatter jsonWriterBatter = new JsonWriterBatter("./data/my\0illegal:fileName.json");
            jsonWriterBatter.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testBatterWriterEmptyBD() {
        try {
            ListOfBatters lb = new ListOfBatters();
            JsonWriterBatter writer = new JsonWriterBatter("./data/testBatterWriterEmptyBD.json");
            writer.open();
            writer.write(lb);
            writer.close();

            JsonReaderBatter reader = new JsonReaderBatter("./data/testBatterWriterEmptyBD.json");
            lb = reader.read();
            assertEquals(0, lb.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testBatterWriterGeneralBD() {
        try {
            ListOfBatters lb = new ListOfBatters();
            lb.addBatter(new Batter("Sean Kim", 555, 123, 122, 33,2,
                    22, 44, 77));
            lb.addBatter(new Batter("Mike Trout", 444, 55, 88,12, 3,
                    33, 55,99));
            JsonWriterBatter writer = new JsonWriterBatter("./data/testBatterWriterGeneralBD.json");
            writer.open();
            writer.write(lb);
            writer.close();

            JsonReaderBatter reader = new JsonReaderBatter("./data/testBatterWriterGeneralBD.json");
            lb = reader.read();
            assertEquals(2, lb.size());
            checkBatter("Sean Kim", 555, 123, 122, 33, 2, 22,
                    44, 77, lb.getBatterAtIndex(0));
            checkBatter("Mike Trout", 444, 55, 88,12,3,33,
                    55,99, lb.getBatterAtIndex(1));

        } catch (IOException e) {
            System.out.println("Exception should not have been thrown");
        }
    }
}
