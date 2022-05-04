package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfBattersTest {
    ListOfBatters listB;
    @BeforeEach
    void runBefore() {
        listB = new ListOfBatters();
    }

    @Test
    void testConstructor() {
        assertEquals(0, listB.size());
    }
    @Test
    void testSize() {
        assertEquals(0, listB.size());

        Batter b1 = new Batter("Sean Kim", 300, 40, 90, 30, 3, 20,
                39, 78);
        listB.addBatter(b1);
        assertEquals(1, listB.size());

        Batter b2 = new Batter("Steve Rogers", 320, 46, 80, 39, 3, 10,
                39, 50);
        listB.addBatter(b2);
        assertEquals(2, listB.size());


    }


    @Test
    void testAddBatter() {
        Batter b1 = new Batter("Sean Kim", 300, 40, 90, 30, 3, 20,
                39, 78);
        listB.addBatter(b1);
        assertEquals(1, listB.size());

        Batter b2 = new Batter("Steve Rogers", 320, 46, 80, 39, 3, 10,
                39, 50);
        listB.addBatter(b2);
        assertEquals(2, listB.size());

    }

    @Test
    void testGetBatterAtIndex() {
        Batter b1 = new Batter("Sean Kim", 300, 40, 90, 30, 3, 20,
                39, 78);
        listB.addBatter(b1);
        assertEquals(b1, listB.getBatterAtIndex(0));

        Batter b2 = new Batter("Steve Rogers", 320, 46, 80, 39, 3, 10,
                39, 50);
        listB.addBatter(b2);
        assertEquals(b2, listB.getBatterAtIndex(1));


    }

    @Test
    void testRemoveBatter() {
        Batter b1 = new Batter("Sean Kim", 300, 40, 90, 30, 3, 20,
                39, 78);
        Batter b2 = new Batter("Steve Rogers", 320, 46, 80, 39, 3, 10,
                39, 50);
        listB.addBatter(b1);
        assertEquals(0, listB.removeBatter(b1).size());

        listB.addBatter(b1);
        listB.addBatter(b2);
        assertEquals(1, listB.removeBatter(b2).size());
        assertEquals(b1, listB.getBatterAtIndex(0));
        listB.addBatter(b2);
        assertEquals(1, listB.removeBatter(b1).size());
        assertEquals(b2, listB.getBatterAtIndex(0));

    }

    @Test
    void testEditBatter() {
        Batter b1 = new Batter("Sean Kim", 300, 40, 90, 30, 3, 20,
                39, 78);
        Batter nb1 = new Batter("Sean Kim", 301, 40, 90, 30, 3, 20,
                39, 78);
        Batter b2 = new Batter("Steve Rogers", 320, 46, 80, 39, 3, 10,
                39, 50);
        Batter nb2 = new Batter("Steve Rogers", 321, 46, 81, 39, 3, 10,
                39, 50);
        assertEquals(0, listB.editBatter(b1).size());
        listB.addBatter(b1);
        listB.editBatter(b2);
        assertEquals(b1, listB.getBatterAtIndex(0));
        listB.editBatter(nb1);
        assertEquals(nb1,listB.getBatterAtIndex(0));

        listB.addBatter(b2);
        listB.editBatter(nb2);
        assertEquals(nb2, listB.getBatterAtIndex(1));

    }

    @Test
    void testSortByHits() {
        listB.sortByHits();
        assertEquals(0, listB.size());
        Batter b1 = new Batter("Sean Kim", 300, 40, 88, 30, 3, 20,
            39, 78);
        listB.addBatter(b1);
        listB.sortByHits();
        assertEquals(1, listB.size());
        assertEquals(b1, listB.getBatterAtIndex(0));

        Batter b2 = new Batter("Steve Rogers", 320, 46, 91, 39, 3, 10,
                39, 50);
        listB.addBatter(b2);
        listB.sortByHits();
        assertEquals(2, listB.size());
        assertEquals(b2, listB.getBatterAtIndex(0));
        assertEquals(b1, listB.getBatterAtIndex(1));

        Batter b3 = new Batter("Tony Stark", 399, 90, 130, 30, 10, 20,
                60, 80);

        listB.addBatter(b3);
        assertEquals(3, listB.size());
        listB.sortByHits();
        assertEquals(b3, listB.getBatterAtIndex(0));
        assertEquals(b2, listB.getBatterAtIndex(1));
        assertEquals(b1, listB.getBatterAtIndex(2));

    }

    @Test
    void testSortByRuns() {
        listB.sortByRuns();
        assertEquals(0, listB.size());
        Batter b1 = new Batter("Sean Kim", 300, 40, 88, 30, 3, 20,
                39, 78);
        listB.addBatter(b1);
        listB.sortByRuns();
        assertEquals(1, listB.size());
        assertEquals(b1, listB.getBatterAtIndex(0));

        Batter b2 = new Batter("Steve Rogers", 320, 46, 91, 39, 3, 10,
                39, 50);
        listB.addBatter(b2);
        listB.sortByRuns();
        assertEquals(b2, listB.getBatterAtIndex(0));
        assertEquals(b1, listB.getBatterAtIndex(1));

        Batter b3 = new Batter("Tony Stark", 399, 90, 130, 30, 10, 20,
                60, 80);

        listB.addBatter(b3);
        listB.sortByRuns();
        assertEquals(b3, listB.getBatterAtIndex(0));
        assertEquals(b2, listB.getBatterAtIndex(1));
        assertEquals(b1, listB.getBatterAtIndex(2));
    }

    @Test
    void testSortByRbi() {
        listB.sortByRbi();
        assertEquals(0, listB.size());
        Batter b1 = new Batter("Sean Kim", 300, 40, 88, 30, 3, 20,
                39, 78);
        listB.addBatter(b1);
        listB.sortByRbi();
        assertEquals(1, listB.size());
        assertEquals(b1, listB.getBatterAtIndex(0));

        Batter b2 = new Batter("Steve Rogers", 320, 46, 91, 39, 3, 10,
                39, 50);
        listB.addBatter(b2);
        listB.sortByRbi();
        assertEquals(2, listB.size());
        assertEquals(b1, listB.getBatterAtIndex(0));
        assertEquals(b2, listB.getBatterAtIndex(1));

        Batter b3 = new Batter("Tony Stark", 399, 90, 130, 30, 10, 20,
                60, 80);

        listB.addBatter(b3);
        listB.sortByRbi();
        assertEquals(3, listB.size());
        assertEquals(b3, listB.getBatterAtIndex(0));
        assertEquals(b1, listB.getBatterAtIndex(1));
        assertEquals(b2, listB.getBatterAtIndex(2));
    }

    @Test
    void testSortByHr() {
        listB.sortByHr();
        assertEquals(0, listB.size());
        Batter b1 = new Batter("Sean Kim", 300, 40, 88, 30, 3, 19,
                39, 78);
        listB.addBatter(b1);
        listB.sortByHr();
        assertEquals(1, listB.size());
        assertEquals(b1, listB.getBatterAtIndex(0));

        Batter b2 = new Batter("Steve Rogers", 320, 46, 91, 39, 3, 21,
                39, 50);
        listB.addBatter(b2);
        listB.sortByHr();
        assertEquals(2, listB.size());
        assertEquals(b2, listB.getBatterAtIndex(0));
        assertEquals(b1, listB.getBatterAtIndex(1));

        Batter b3 = new Batter("Tony Stark", 399, 90, 130, 30, 10, 20,
                60, 80);

        listB.addBatter(b3);
        listB.sortByHr();
        assertEquals(3, listB.size());
        assertEquals(b2, listB.getBatterAtIndex(0));
        assertEquals(b3, listB.getBatterAtIndex(1));
        assertEquals(b1, listB.getBatterAtIndex(2));
    }

    @Test
    void testSortByAvg() {
        listB.sortByAvg();
        assertEquals(0, listB.size());
        Batter b1 = new Batter("Sean Kim", 300, 40, 88, 30, 3, 19,
                39, 78);
        listB.addBatter(b1);
        listB.sortByAvg();
        assertEquals(1, listB.size());
        assertEquals(b1, listB.getBatterAtIndex(0));

        Batter b2 = new Batter("Steve Rogers", 320, 46, 91, 39, 3, 21,
                39, 50);
        listB.addBatter(b2);
        listB.sortByAvg();
        assertEquals(2, listB.size());
        assertEquals(b1, listB.getBatterAtIndex(0));
        assertEquals(b2, listB.getBatterAtIndex(1));

        Batter b3 = new Batter("Tony Stark", 399, 90, 130, 30, 10, 20,
                60, 80);

        listB.addBatter(b3);
        listB.sortByAvg();
        assertEquals(3, listB.size());
        assertEquals(b3, listB.getBatterAtIndex(0));
        assertEquals(b1, listB.getBatterAtIndex(1));
        assertEquals(b2, listB.getBatterAtIndex(2));
    }

    @Test
    void testContainsBatter() {
        Batter b1 = new Batter("Sean Kim", 300, 40, 90, 30, 3, 20,
                39, 78);
        Batter b2 = new Batter("Steve Rogers", 320, 46, 91, 39, 3, 21,
                39, 50);
        Batter b3 = new Batter("Tony Stark", 399, 90, 130, 30, 10, 20,
                60, 80);
        listB.addBatter(b1);
        listB.addBatter(b2);
        assertTrue(listB.containsBatter(b1));
        assertTrue(listB.containsBatter(b2));
        assertFalse(listB.containsBatter(b3));
    }



}
