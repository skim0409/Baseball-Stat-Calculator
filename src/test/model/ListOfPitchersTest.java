package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfPitchersTest {
    ListOfPitchers listP;

    @BeforeEach
    void runBefore() {
        listP = new ListOfPitchers();
    }

    @Test
    void estConstructor() {
        assertEquals(0, listP.size());
    }

    @Test
    void testPitcherAtIndex() {
        Pitcher p1 = new Pitcher("Sean Kim", 20,4, 212.1,
                30, 120, 30, 192);
        listP.addPitcher(p1);
        assertEquals(p1, listP.getPitcherAtIndex(0));

        Pitcher p2 = new Pitcher("Clayton Kershaw", 10, 8, 121.2,
                48, 103, 21, 144);
        listP.addPitcher(p2);
        assertEquals(p2, listP.getPitcherAtIndex(1));
    }

    @Test
    void testAddPitcher() {
        assertEquals(0, listP.size());

        Pitcher p1 = new Pitcher("Sean Kim", 20,4, 212.1,
                30, 120, 30, 192);
        listP.addPitcher(p1);
        assertEquals(1, listP.size());
        assertEquals(p1, listP.getPitcherAtIndex(0));

        Pitcher p2 = new Pitcher("Clayton Kershaw", 10, 8, 121.2,
                48, 103, 21, 144);
        listP.addPitcher(p2);
        assertEquals(2, listP.size());
        assertEquals(p2, listP.getPitcherAtIndex(1));

    }

    @Test
    void testSize() {
        assertEquals(0, listP.size());

        Pitcher p1 = new Pitcher("Sean Kim", 20,4, 212.1,
                30, 120, 30, 192);
        listP.addPitcher(p1);
        assertEquals(1, listP.size());

        Pitcher p2 = new Pitcher("Clayton Kershaw", 10, 8, 121.2,
                48, 103, 21, 144);
        listP.addPitcher(p2);
        assertEquals(2, listP.size());

        Pitcher p3 = new Pitcher("Forrest Gump", 15, 9, 202.0,
                78,144, 60, 164);
        listP.addPitcher(p3);
        assertEquals(3, listP.size());
    }

    @Test
    void testRemovePitcher() {
        Pitcher p1 = new Pitcher("Sean Kim", 20,4, 212.1,
                30, 120, 30, 192);
        Pitcher p2 = new Pitcher("Clayton Kershaw", 10, 8, 121.2,
                48, 103, 21, 144);
        listP.addPitcher(p1);
        assertEquals(0, listP.removePitcher(p1).size());

        listP.addPitcher(p1);
        listP.addPitcher(p2);
        assertEquals(1, listP.removePitcher(p1).size());
        assertEquals(p2, listP.getPitcherAtIndex(0));

        listP.addPitcher(p1);
        assertEquals(1, listP.removePitcher(p2).size());
        assertEquals(p1, listP.getPitcherAtIndex(0));
    }

    @Test
    void testEditPitcher() {
        Pitcher p1 = new Pitcher("Sean Kim", 20,4, 212.1,
                30, 120, 30, 192);
        Pitcher np1 = new Pitcher("Sean Kim", 21,4, 213.1,
                30, 122, 31, 192);
        Pitcher p2 = new Pitcher("Clayton Kershaw", 10, 8, 121.2,
                48, 103, 21, 144);
        Pitcher np2 = new Pitcher("Clayton Kershaw", 11, 8, 124.2,
                48, 107, 21, 145);

        Pitcher p3 = new Pitcher("Forrest Gump", 15, 9, 202.0,
                78,144, 60, 164);

        listP.addPitcher(p1);
        listP.addPitcher(p2);

        listP.editPitcher(p3);
        assertEquals(p1, listP.getPitcherAtIndex(0));
        assertEquals(p2, listP.getPitcherAtIndex(1));

        listP.editPitcher(np1);
        assertEquals(np1, listP.getPitcherAtIndex(0));
        assertEquals(p2, listP.getPitcherAtIndex(1));

        listP.editPitcher(np2);
        assertEquals(np1, listP.getPitcherAtIndex(0));
        assertEquals(np2, listP.getPitcherAtIndex(1));

    }
    @Test
    void testSortByWins() {
        listP.sortByWins();
        assertEquals(0, listP.size());

        Pitcher p1 = new Pitcher("Sean Kim", 20,4, 212.1,
                30, 120, 30, 192);
        listP.addPitcher(p1);
        listP.sortByWins();
        assertEquals(1, listP.size());
        assertEquals(p1, listP.getPitcherAtIndex(0));

        Pitcher p2 = new Pitcher("Clayton Kershaw", 10, 8, 121.2,
                48, 103, 21, 144);
        listP.addPitcher(p2);
        listP.sortByWins();
        assertEquals(2, listP.size());
        assertEquals(p1, listP.getPitcherAtIndex(0));
        assertEquals(p2, listP.getPitcherAtIndex(1));

        Pitcher p3 = new Pitcher("Forrest Gump", 15, 9, 202.0,
                78,144, 60, 164);
        listP.addPitcher(p3);
        listP.sortByWins();
        assertEquals(3, listP.size());
        assertEquals(p1, listP.getPitcherAtIndex(0));
        assertEquals(p3, listP.getPitcherAtIndex(1));
        assertEquals(p2, listP.getPitcherAtIndex(2));
    }

    @Test
    void testSortByEra() {
        listP.sortByEra();
        assertEquals(0, listP.size());

        Pitcher p1 = new Pitcher("Sean Kim", 20,4, 212.1,
                30, 120, 30, 192);
        listP.addPitcher(p1);
        listP.sortByEra();
        assertEquals(1, listP.size());
        assertEquals(p1, listP.getPitcherAtIndex(0));

        Pitcher p2 = new Pitcher("Clayton Kershaw", 10, 8, 121.2,
                48, 103, 21, 144);
        listP.addPitcher(p2);
        listP.sortByEra();
        assertEquals(2, listP.size());
        assertEquals(p1, listP.getPitcherAtIndex(0));
        assertEquals(p2, listP.getPitcherAtIndex(1));

        Pitcher p3 = new Pitcher("Forrest Gump", 15, 9, 202.0,
                78,144, 60, 164);
        listP.addPitcher(p3);
        listP.sortByEra();
        assertEquals(3, listP.size());
        assertEquals(p1, listP.getPitcherAtIndex(0));
        assertEquals(p3, listP.getPitcherAtIndex(1));
        assertEquals(p2, listP.getPitcherAtIndex(2));
    }

    @Test
    void testSortByInnings() {
        listP.sortByWins();
        assertEquals(0, listP.size());

        Pitcher p1 = new Pitcher("Sean Kim", 20,4, 212.1,
                30, 120, 30, 192);
        listP.addPitcher(p1);
        listP.sortByInnings();
        assertEquals(1, listP.size());
        assertEquals(p1, listP.getPitcherAtIndex(0));

        Pitcher p2 = new Pitcher("Clayton Kershaw", 10, 8, 121.2,
                48, 103, 21, 144);
        listP.addPitcher(p2);
        listP.sortByInnings();
        assertEquals(2, listP.size());
        assertEquals(p1, listP.getPitcherAtIndex(0));
        assertEquals(p2, listP.getPitcherAtIndex(1));

        Pitcher p3 = new Pitcher("Forrest Gump", 15, 9, 202.0,
                78,144, 60, 164);
        listP.addPitcher(p3);
        listP.sortByInnings();
        assertEquals(3, listP.size());
        assertEquals(p1, listP.getPitcherAtIndex(0));
        assertEquals(p3, listP.getPitcherAtIndex(1));
        assertEquals(p2, listP.getPitcherAtIndex(2));
    }

    @Test
    void testSortbyWhip() {
        listP.sortByWins();
        assertEquals(0, listP.size());

        Pitcher p1 = new Pitcher("Sean Kim", 20,4, 212.1,
                30, 120, 30, 192);
        listP.addPitcher(p1);
        listP.sortByWhip();
        assertEquals(1, listP.size());
        assertEquals(p1, listP.getPitcherAtIndex(0));

        Pitcher p2 = new Pitcher("Clayton Kershaw", 10, 8, 121.2,
                48, 103, 21, 144);
        listP.addPitcher(p2);
        listP.sortByWhip();
        assertEquals(2, listP.size());
        assertEquals(p1, listP.getPitcherAtIndex(0));
        assertEquals(p2, listP.getPitcherAtIndex(1));

        Pitcher p3 = new Pitcher("Forrest Gump", 15, 9, 202.0,
                78,144, 60, 164);
        listP.addPitcher(p3);
        listP.sortByWhip();
        assertEquals(3, listP.size());
        assertEquals(p1, listP.getPitcherAtIndex(0));
        assertEquals(p3, listP.getPitcherAtIndex(1));
        assertEquals(p2, listP.getPitcherAtIndex(2));
    }

    @Test
    void testContainsPitcher() {
        Pitcher p1 = new Pitcher("Sean Kim", 20,4, 212.1,
                30, 120, 30, 192);
        Pitcher p2 = new Pitcher("Clayton Kershaw", 10, 8, 121.2,
                48, 103, 21, 144);
        Pitcher p3 = new Pitcher("Forrest Gump", 15, 9, 202.0,
                78,144, 60, 164);
        listP.addPitcher(p1);
        listP.addPitcher(p2);
        assertTrue(listP.containsPitcher(p1));
        assertTrue(listP.containsPitcher(p2));
        assertFalse(listP.containsPitcher(p3));
    }





}
