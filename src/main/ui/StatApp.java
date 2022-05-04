package ui;

import model.*;
import persistence.JsonReaderBatter;
import persistence.JsonReaderPitcher;
import persistence.JsonWriterBatter;
import persistence.JsonWriterPitcher;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Baseball stat calculating and managing application
public class StatApp {
    public static final String JSON_STORE_BAT = "./data/batterdata.json";
    public static final String JSON_STORE_PIT = "./data/pitcherdata.json";
    private ListOfPitchers pitcherList;
    private ListOfBatters batterList;
    private Scanner input;
    private JsonReaderBatter jsonReaderBatter;
    private JsonWriterBatter jsonWriterBatter;
    private JsonReaderPitcher jsonReaderPitcher;
    private JsonWriterPitcher jsonWriterPitcher;

    // Runs the Stat application
    // REF: Followed Similar structure to Teller Application
    public StatApp() throws FileNotFoundException {
        runStat();
    }

    // MODIFIES: This
    // EFFECTS: Processes user input
    // REF: Followed similar structure to Teller app
    private void runStat() {
        boolean keepGoing = true;
        String command = null;

        init();
        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
                printLog(EventLog.getInstance());
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: This
    // EFFECTS: Processes user command
    // REF: Structure from Teller app
    private void processCommand(String command) {
        if (command.equals("s")) {
            enterStat();
        } else if (command.equals("v")) {
            viewPlayer();
        } else if (command.equals("t")) {
            viewStatTable();
        } else if (command.equals("r")) {
            removePlayer();
        } else if (command.equals("e")) {
            editPlayerStat();
        } else if (command.equals("k")) {
            saveList();
        } else if (command.equals("l")) {
            loadList();
        } else {
            System.out.println("Invalid selection. Please choose again");
        }
    }

    // EFFECTS: Initializes batter list and pitcher list
    // REF: Teller App: similar structure
    private void init() {
        pitcherList = new ListOfPitchers();
        batterList = new ListOfBatters();
        input = new Scanner(System.in);
        jsonReaderBatter = new JsonReaderBatter(JSON_STORE_BAT);
        jsonWriterBatter = new JsonWriterBatter(JSON_STORE_BAT);
        jsonReaderPitcher = new JsonReaderPitcher(JSON_STORE_PIT);
        jsonWriterPitcher = new JsonWriterPitcher(JSON_STORE_PIT);
    }

    // EFFECTS: Displays option menu to the user
    // REF: Teller App: similar structure
    private void displayMenu() {
        System.out.println("Welcome to Baseball stat Calculator Program!");
        System.out.println("Options: Select one of the following");
        System.out.println("--------------------------------------");
        System.out.println("s --> Enter stat for a player");
        System.out.println("v --> View a player's stat");
        System.out.println("t --> View stat table");
        System.out.println("r --> Remove a player");
        System.out.println("e --> Edit a player stat");
        System.out.println("k --> Keep the new changes");
        System.out.println("l --> load data from file");
        System.out.println("q --> Quit the program");
        System.out.println("--------------------------------------");

    }

    // MODIFIES: this
    // EFFECTS: constructs a system where user can enter players stats.
    private void enterStat() {
        System.out.println("Choose Batter of Pitcher: ");
        String choice = selectPosition();
        if (choice.equals("p")) {
            Pitcher p = enterPitcherStat();
            pitcherList.addPitcher(p);
        } else {
            Batter b = enterBatterStat();
            batterList.addBatter(b);
        }
        System.out.println("Successfully added the player!");
        System.out.println("\n");
    }

    // EFFECTS: constructs a system that allows user to view the player
    private void viewPlayer() {
        System.out.println("Enter the Player's First and Last name");
        String first = input.next();
        String last = input.next();
        String full = (first + " " + last).toLowerCase();

        boolean b = printBatter(full);
        boolean p = printPitcher(full);

        if (!b && !p) {
            System.out.println("The player does not exist in our Data Base");
            System.out.println("\n");
        }
    }

    // EFFECTS: prints the Batter with the given name and returns a boolean indicating the
    //          batter with the name 'name' exists
    private boolean printBatter(String name) {
        boolean exists = false;
        for (int i = 0; i < batterList.size(); i++) {
            String currentB = batterList.getBatterAtIndex(i).getName().toLowerCase();
            if (currentB.equals(name)) {
                exists = true;
                System.out.println("Name                 AB  RUN HIT BB RBI 2B  3B  HR  AVG    OBP    SLG    OPS");
                System.out.println(batterList.getBatterAtIndex(i));
                System.out.println("\n");
            }
        }
        return exists;
    }

    // EFFECTS: prints the Patter with the given name and returns a boolean indicating the
    //          pitcher with the name 'name' exists
    private boolean printPitcher(String name) {
        boolean exists = false;
        for (int j = 0; j < pitcherList.size(); j++) {
            String currentP = pitcherList.getPitcherAtIndex(j).getName().toLowerCase();
            if (currentP.equals(name)) {
                exists = true;
                System.out.println("Name                W   L   ERA   IP     ERR  HIT  BB  SO   WHIP");
                System.out.println(pitcherList.getPitcherAtIndex(j));
                System.out.println("\n");
            }
        }
        return exists;
    }

    // EFFECTS: Constructs a viewing option where user can view the table according to players' stats
    private void viewStatTable() {
        System.out.println("Select the table");
        String pos = selectPosition();
        String choice;
        if (pos.equals("b")) {
            choice = batterStatOptions();
            sortBatterList(choice);
            printBatterTable();
        } else {
            choice = pitcherStatOptions();
            sortPitcherList(choice);
            printPitcherTable();
        }

    }

    // MODIFIES: this
    // EFFECTS: Constructs user ui for removing a player. Removes a player according to user inputs
    private void removePlayer() {
        System.out.println("Select Position");
        String pos = selectPosition();
        System.out.println("Enter the player's name");
        String first = input.next();
        String last = input.next();
        String full = (first + " " + last).toLowerCase();
        if (pos.equals("p")) {
            if (pitcherExists(full)) {
                pitcherList.removePitcher(pitcherWithName(full));
                System.out.println("Successfully removed the player!");
                System.out.println("\n");
            } else {
                printPlayerNotExist();
            }
        } else {
            if (batterExists(full)) {
                batterList.removeBatter(batterWithName(full));
                System.out.println("Successfully removed the player!");
                System.out.println("\n");

            } else {
                printPlayerNotExist();
            }
        }
    }


    // EFFECTS: Returns true if batter with name 'name' exists. False otherwise.
    private boolean batterExists(String name) {
        boolean exists = false;
        for (int i = 0; i < batterList.size(); i++) {
            String nameB = batterList.getBatterAtIndex(i).getName().toLowerCase();
            if (nameB.equals(name)) {
                exists = true;
                return exists;
            }
        }
        return exists;
    }

    // EFFECTS: Returns true if pitcher with name 'name' exists in the pitcher list. False otherwise.
    private boolean pitcherExists(String name) {
        boolean exists = false;
        for (int i = 0; i < pitcherList.size(); i++) {
            String nameP = pitcherList.getPitcherAtIndex(i).getName().toLowerCase();
            if (nameP.equals(name)) {
                exists = true;
                return exists;
            }
        }
        return exists;
    }

    // REQUIRES: There is a pitcher in pitcher list with name 'name'
    // EFFECTS: Returns the pitcher with name given in the parameter.
    private Pitcher pitcherWithName(String name) {
        for (int i = 0; i < pitcherList.size(); i++) {
            String nameP = pitcherList.getPitcherAtIndex(i).getName().toLowerCase();
            if (nameP.equals(name)) {
                return pitcherList.getPitcherAtIndex(i);
            }
        }
        return null;
    }

    // REQUIRES: There is a batter in the batter list with name 'name'
    // EFFECTS: Returns a batter with name given in the parameter.
    private Batter batterWithName(String name) {
        for (int i = 0; i < batterList.size(); i++) {
            String nameB = batterList.getBatterAtIndex(i).getName().toLowerCase();
            if (nameB.equals(name)) {
                return batterList.getBatterAtIndex(i);
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: Constructs ui for edit stat page and edits stats of a player according to inputs
    private void editPlayerStat() {
        System.out.println("Select Position");
        String pos = selectPosition();
        System.out.println("Enter the player's name");
        String first = input.next();
        String last = input.next();
        String full = (first + " " + last).toLowerCase();
        if (pos.equals("p")) {
            if (pitcherExists(full)) {
                pitcherList.editPitcher(enterPitcherStat());
                System.out.println("The stat is updated!");
                System.out.println("\n");
            } else {
                printPlayerNotExist();
            }
        } else if (pos.equals("b")) {
            if (batterExists(full)) {
                batterList.editBatter(enterBatterStat());
                System.out.println("The stat is updated!");
                System.out.println("\n");
            } else {
                printPlayerNotExist();
            }
        }
    }

    // EFFECTS: Prints a message that the player does not exist.
    private void printPlayerNotExist() {
        System.out.println("Player does not exist");
        System.out.println("\n");
    }

    // EFFECTS: Returns user's choice of batter stat
    private String batterStatOptions() {
        String choice = "";
        while (!(choice.equals("h") || choice.equals("run") || choice.equals("rbi")
                || choice.equals("a") || choice.equals("hr"))) {
            System.out.println("Choose one of the Following to see the leaders in that category");
            System.out.println("h   <-- Hits");
            System.out.println("run <-- Runs");
            System.out.println("rbi <-- RBI");
            System.out.println("a   <-- Batting Average");
            System.out.println("hr  <-- Home Run");
            choice = input.next();
            choice.toLowerCase();
        }
        return choice;
    }

    // EFFECTS: Returns user's choice of pitcher stat
    private String pitcherStatOptions() {
        String choice = "";
        while (!(choice.equals("w") || choice.equals("ip") || choice.equals("era")
                || choice.equals("whip"))) {
            System.out.println("Choose one of the Following to see the leaders in that category");
            System.out.println("w    <-- Wins");
            System.out.println("ip   <-- Innings Pitched");
            System.out.println("era  <-- Earned Run Average(ERA)");
            System.out.println("whip <-- Walks plus Hits per Inning(WHIP)");
            choice = input.next();
            choice.toLowerCase();
        }
        return choice;
    }


    // EFFECTS: Prints batter table of batters in batter list
    private void printBatterTable() {
        if (batterList.size() == 0) {
            System.out.println("Sorry! There is no player in the Data Base");
            System.out.println("\n");

        } else {
            String header = "  Name                 AB  RUN HIT BB RBI 2B  3B  HR  AVG    OBP    SLG    OPS";
            System.out.println(header);
            for (int i = 0; i < batterList.size(); i++) {
                System.out.println(String.valueOf(i + 1) + "." + batterList.getBatterAtIndex(i));
            }
            System.out.println("\n");
        }

    }

    // EFFECTS: Prints pitcher table of pitchers in pitcher list
    private void printPitcherTable() {
        if (pitcherList.size() == 0) {
            System.out.println("Sorry! There is no player in the Data Base");
            System.out.println("\n");
        } else {
            String stat = "  Name                W   L   ERA   IP     ERR  HIT  BB  SO   WHIP";
            System.out.println(stat);
            for (int i = 0; i < pitcherList.size(); i++) {
                System.out.println(String.valueOf(i + 1) + "." + pitcherList.getPitcherAtIndex(i));
            }
            System.out.println("\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: Sorts Batter list according to stat 'choice'
    private void sortBatterList(String choice) {
        if (choice.equals("h")) {
            batterList.sortByHits();
        } else if (choice.equals("run")) {
            batterList.sortByRuns();
        } else if (choice.equals("rbi")) {
            batterList.sortByRbi();
        } else if (choice.equals("a")) {
            batterList.sortByAvg();
        } else {
            batterList.sortByHr();
        }
    }

    // MODIFIES: This
    // EFFECTS: Sorts Pitcher list according to given stat 'choice'
    private void sortPitcherList(String choice) {
        if (choice.equals("w")) {
            pitcherList.sortByWins();
        } else if (choice.equals("ip")) {
            pitcherList.sortByInnings();
        } else if (choice.equals("era")) {
            pitcherList.sortByEra();
        } else {
            pitcherList.sortByWhip();
        }
    }

    // Returns user's choice of position: "p" or "b"
    private String selectPosition() {
        String selection = "";

        while (!(selection.equals("p") || selection.equals("b"))) {
            System.out.println("p --> Pitcher");
            System.out.println("b --> Batter");
            selection = input.next();
            selection.toLowerCase();
        }
        return selection;
    }

    // EFFECTS: Returns a new pitcher instance from user inputs
    private Pitcher enterPitcherStat() {
        System.out.println("Enter First and Last name of the player(String) ");
        String first = input.next();
        String last = input.next();
        System.out.println("Enter number of Wins(int)");
        int w = input.nextInt();
        System.out.println("Enter number of Losses(int)");
        int l = input.nextInt();
        System.out.println("Enter total innings pitched(double)");
        double ip = input.nextDouble();
        System.out.println("Enter total earned runs(int)");
        int er = input.nextInt();
        System.out.println("Enter total hit allowed(int)");
        int hit = input.nextInt();
        System.out.println("Enter total walks allowed(int)");
        int bb = input.nextInt();
        System.out.println("Enter total strikeouts(int)");
        int so = input.nextInt();

        String fullName = first + " " + last;
        Pitcher p = new Pitcher(fullName, w, l, ip, er, hit, bb, so);
        return p;
    }

    // EFFECTS: Returns a new Batter instance given appropriate inputs from the user
    private Batter enterBatterStat() {
        System.out.println("Enter First and Last name of the player: ");
        String first = input.next();
        String last = input.next();
        System.out.println("Enter the number of At Bats(int)");
        int ab = input.nextInt();
        System.out.println("Enter the number of Runs(int)");
        int run = input.nextInt();
        System.out.println("Enter the number of Hits(int)");
        int hit = input.nextInt();
        System.out.println("Enter the number of Doubles(int)");
        int d = input.nextInt();
        System.out.println("Enter the number of Triples(int)");
        int t = input.nextInt();
        System.out.println("Enter the number of Home Runs(int)");
        int hr = input.nextInt();
        System.out.println("Enter the number of Walks(int)");
        int bb = input.nextInt();
        System.out.println("Enter the total number of RBI(int)");
        int rbi = input.nextInt();

        String fullName = first + " " + last;
        Batter b = new Batter(fullName, ab, run, hit, d, t, hr, bb, rbi);
        return b;
    }

    // EFFECTS: saves batterList and pitcherList to file
    private void saveList() {
        saveBatterList();
        savePitcherList();
        System.out.println("\n");
    }

    // EFFECTS: saves the batterList to file
    private void saveBatterList() {
        try {
            ListOfBatters temp = jsonReaderBatter.read();
            if (temp.size() > 0 && batterList.containsBatter(temp.getBatterAtIndex(0))) {
                jsonWriterBatter.open();
                jsonWriterBatter.write(batterList);
                jsonWriterBatter.close();
            } else {
                for (int i = 0; i < batterList.size(); i++) {
                    temp.addBatter(batterList.getBatterAtIndex(i));
                }
                jsonWriterBatter.open();
                jsonWriterBatter.write(temp);
                jsonWriterBatter.close();
            }
            System.out.println("Saved batter data to " + JSON_STORE_BAT);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_BAT);
        } catch (IOException e) {
            System.out.println("There is an error");
        }
    }

    // EFFECTS: saves the pitcherList to file
    private void savePitcherList() {
        try {
            ListOfPitchers temp = jsonReaderPitcher.read();
            if (temp.size() > 0 && pitcherList.containsPitcher(temp.getPitcherAtIndex(0))) {
                jsonWriterPitcher.open();
                jsonWriterPitcher.write(pitcherList);
                jsonWriterPitcher.close();
            } else {
                for (int i = 0; i < pitcherList.size(); i++) {
                    temp.addPitcher(pitcherList.getPitcherAtIndex(i));
                }
                jsonWriterPitcher.open();
                jsonWriterPitcher.write(temp);
                jsonWriterPitcher.close();
            }
            System.out.println("Saved pitcher data to " + JSON_STORE_PIT);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_PIT);
        } catch (IOException e) {
            System.out.println("There is an error");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads batterList and pitcherList from file
    private void loadList() {
        loadBatterList();
        loadPitcherList();
        System.out.println("\n");
    }

    // MODIFIES: this
    // EFFECTS: loads batterList from file
    private void loadBatterList() {
        try {
            batterList = jsonReaderBatter.read();
            System.out.println("Loaded batter data from " + JSON_STORE_BAT);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_BAT);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads pitcherList from file
    private void loadPitcherList() {
        try {
            pitcherList = jsonReaderPitcher.read();
            System.out.println("Loaded pitcher data from " + JSON_STORE_PIT);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_PIT);
        }
    }

    public void printLog(EventLog e) {
        for (Event next : e) {
            System.out.println(next.toString() + "\n\n");
        }
    }



}
