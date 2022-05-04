package ui;

import model.*;
import model.Event;
import persistence.JsonReaderBatter;
import persistence.JsonReaderPitcher;
import persistence.JsonWriterBatter;
import persistence.JsonWriterPitcher;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Baseball stat calculating application UI
public class StatAppUI extends JFrame implements ActionListener {
    private final ImageIcon bicon =
            new ImageIcon("data/batter.jpeg");
    private final ImageIcon picon =
            new ImageIcon("data/pitcher.gif");

    private final ImageIcon glove =
            new ImageIcon("data/glove.jpeg");

    private final ImageIcon bat =
            new ImageIcon("data/bat.jpeg");
    private final ImageIcon baseball =
            new ImageIcon("data/baseball.jpeg");
    private final ImageIcon teemo =
            new ImageIcon("data/teemo.png");

    public static final String JSON_STORE_BAT = "./data/batterdata.json";
    public static final String JSON_STORE_PIT = "./data/pitcherdata.json";

    private JsonReaderBatter jsonReaderBatter;
    private JsonWriterBatter jsonWriterBatter;
    private JsonReaderPitcher jsonReaderPitcher;
    private JsonWriterPitcher jsonWriterPitcher;

    private JButton addp;
    private JButton addb;
    private JButton edit;
    private JButton remove;
    private JButton load;
    private JButton save;

    private JTextField bname;
    private JTextField ab;
    private JTextField runs;
    private JTextField hits;
    private JTextField doubles;
    private JTextField triples;
    private JTextField hr;
    private JTextField bb;
    private JTextField rbi;
    private Object[] bstats;
    private JTable btable;
    private JScrollPane bsp;
    private String[] battertitle;
    String[][] batterdata;

    private JTextField pname;
    private JTextField win;
    private JTextField loss;
    private JTextField ip;
    private JTextField er;
    private JTextField hit;
    private JTextField walk;
    private JTextField so;
    private Object[] pstats;
    private JTable ptable;
    private JScrollPane psp;
    private String[] pitchertitle;
    private String[][] pitcherdata;


    private ListOfPitchers pitcherList;
    private ListOfBatters batterList;

    private JFrame frame;

    // Runs Stat application UI
    public StatAppUI() {
        init();
        initializeGraphics();
        System.out.println(bicon);
    }

    // EFFECTS: Process user commands according to types button pressed
    // MODIFIES: this
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add Pitcher")) {
            addPitcherUI();
        } else if (e.getActionCommand().equals("Add Batter")) {
            addBatterUI();
        } else if (e.getActionCommand().equals("Load")) {
            loadPlayerUI();
        } else if (e.getActionCommand().equals("Save")) {
            saveListUI();
        } else if (e.getActionCommand().equals("Remove Player")) {
            removePlayerUI();
        } else if (e.getActionCommand().equals("Edit Player")) {
            editPlayerUI();
        }

    }

    // EFFECTS: Initialize fields
    public void init() {
        pitcherList = new ListOfPitchers();
        batterList = new ListOfBatters();
        buttonInit();
        jsonInit();
        batterUiInit();
        pitcherUiInit();
    }

    // EFFECTS: Initialize Buttons
    public void buttonInit() {
        addp = new JButton("Add Pitcher");
        addp.addActionListener(this);

        addb = new JButton("Add Batter");
        addb.addActionListener(this);

        edit = new JButton("Edit Player");
        edit.addActionListener(this);

        remove = new JButton("Remove Player");
        remove.addActionListener(this);

        load = new JButton("Load");
        load.addActionListener(this);

        save = new JButton("Save");
        save.addActionListener(this);
    }

    // EFFECTS: Initializes Json fields
    public void jsonInit() {
        jsonReaderBatter = new JsonReaderBatter(JSON_STORE_BAT);
        jsonWriterBatter = new JsonWriterBatter(JSON_STORE_BAT);
        jsonReaderPitcher = new JsonReaderPitcher(JSON_STORE_PIT);
        jsonWriterPitcher = new JsonWriterPitcher(JSON_STORE_PIT);
    }

    // EFFECTS: Initializes fields for Pitcher UI
    public void pitcherUiInit() {
        pname = new JTextField();
        win = new JTextField();
        loss = new JTextField();
        ip = new JTextField();
        er = new JTextField();
        hit = new JTextField();
        walk = new JTextField();
        so = new JTextField();

        pstats = new Object[] {
                "Player Name", pname,
                "Wins", win,
                "Losses", loss,
                "Innings Pitched", ip,
                "ER", er,
                "Hits", hit,
                "Walks", walk,
                "Strike Outs", so
        };
    }

    // EFFECTS: Initializes fields for Batter UI
    public void batterUiInit() {
        bname = new JTextField();
        ab = new JTextField();
        runs = new JTextField();
        hits = new JTextField();
        doubles = new JTextField();
        triples = new JTextField();
        hr = new JTextField();
        bb = new JTextField();
        rbi = new JTextField();

        bstats = new Object[] {
                "Player Name", bname,
                "At Bat", ab,
                "Runs", runs,
                "Hits", hits,
                "Doubles", doubles,
                "Triples", triples,
                "Home Run", hr,
                "Walks", bb,
                "RBI", rbi
        };
    }

    // MODIFIES: this
    // EFFECTS: Loads existing batters and pitchers to UI
    private void loadPlayerUI() {
        loadBatterList();
        loadPitcherList();
        frame.dispose();
        initializeGraphics();
        showMessageLoad();
    }

    // EFFECTS: Shows a message indicating loading was successful
    public void showMessageLoad() {
        JOptionPane.showMessageDialog(null, "Loaded Successfully", "Message", JOptionPane.INFORMATION_MESSAGE, teemo);
    }

    // MODIFIES: this
    // EFFECTS: Loads batter list from Json to batterList
    private void loadBatterList() {
        try {
            batterList = jsonReaderBatter.read();
            System.out.println("Loaded batter data from " + JSON_STORE_BAT);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_BAT);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads pitcher list from json to pitcherList
    private void loadPitcherList() {
        try {
            pitcherList = jsonReaderPitcher.read();
            System.out.println("Loaded pitcher data from " + JSON_STORE_PIT);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_PIT);
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates UI for adding pitcher and adds pitcher to the table
    public void addPitcherUI() {
        int res = JOptionPane.showConfirmDialog(null, pstats, "Enter Pitcher Stats",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, picon);
        String name = pname.getText();
        String w = win.getText();
        String l = loss.getText();
        String inning = ip.getText();
        String err = er.getText();
        String h = hit.getText();
        String bb = walk.getText();
        String k = so.getText();
        if (res == JOptionPane.OK_OPTION) {
            Pitcher p = new Pitcher(name, Integer.parseInt(w), Integer.parseInt(l), Double.parseDouble(inning),
                    Integer.parseInt(err), Integer.parseInt(h), Integer.parseInt(bb), Integer.parseInt(k));
            pitcherList.addPitcher(p);
            frame.dispose();
            initializeGraphics();
            showMessagePit();
            clearPitcherFields();
        } else {
            clearPitcherFields();
        }
    }

    // MODIFIES: this
    // EFFECTS: shows a message
    public void showMessagePit() {
        JOptionPane.showMessageDialog(null, null, "Added Successfully!",
                JOptionPane.INFORMATION_MESSAGE, glove);

    }


    // MODIFIES: this
    // EFFECTS: creates UI for adding batters and adds batter to the table
    public void addBatterUI() {
        int res = JOptionPane.showConfirmDialog(null, bstats, "Enter Batter Stats", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, bicon);
        String name = bname.getText();
        String abat = ab.getText();
        String r = runs.getText();
        String h = hits.getText();
        String d = doubles.getText();
        String t = triples.getText();
        String homer = hr.getText();
        String walk = bb.getText();
        String rb = rbi.getText();
        if (res == JOptionPane.OK_OPTION) {
            Batter b = new Batter(name, Integer.parseInt(abat), Integer.parseInt(r), Integer.parseInt(h),
                    Integer.parseInt(d), Integer.parseInt(t), Integer.parseInt(homer),
                    Integer.parseInt(walk), Integer.parseInt(rb));
            batterList.addBatter(b);
            frame.dispose();
            initializeGraphics();
            showMessageBat();
            clearBatterFields();
        } else {
            clearBatterFields();
        }
    }

    // EFFECTS: shows a message indicating action is performed
    public void showMessageBat() {
        JOptionPane.showMessageDialog(null, null, "Added Successfully", JOptionPane.INFORMATION_MESSAGE, bat);
    }

    // EFFECTS: clears text fields of pitcher UI
    public void clearPitcherFields() {
        pname.setText(null);
        win.setText(null);
        loss.setText(null);
        ip.setText(null);
        er.setText(null);
        hit.setText(null);
        walk.setText(null);
        so.setText(null);
    }

    // EFFECTS: clears text fields of batter UI
    public void clearBatterFields() {
        bname.setText(null);
        ab.setText(null);
        runs.setText(null);
        hits.setText(null);
        doubles.setText(null);
        triples.setText(null);
        hr.setText(null);
        bb.setText(null);
        rbi.setText(null);
    }

    // EFFECTS: Creates a panel that has tables for batters and pitchers
    public JPanel setTablePanels() {
        JPanel defaultPanel = new JPanel();
        defaultPanel.setPreferredSize(new Dimension(800, 400));
        defaultPanel.setBackground(Color.BLACK);

        JPanel ppanel = new JPanel();
        ppanel.setLayout(new BorderLayout());
        JLabel plabel = new JLabel("Pitcher Table");
        plabel.setForeground(Color.white);
        ppanel.add(plabel, BorderLayout.NORTH);
        ppanel.setPreferredSize(new Dimension(400, 200));
        ppanel.setBackground(new Color(15, 82, 126));
        ppanel.add(pscrollpane(), BorderLayout.CENTER);

        JPanel bpanel = new JPanel();
        bpanel.setLayout(new BorderLayout());
        JLabel blabel = new JLabel("Batter Table");
        blabel.setForeground(Color.white);
        bpanel.add(blabel,BorderLayout.NORTH);
        bpanel.setPreferredSize(new Dimension(380, 200));
        bpanel.setBackground(new Color(128, 0, 32));
        bpanel.add(bscrollpane(), BorderLayout.CENTER);

        defaultPanel.setLayout(new GridLayout(1, 2));
        defaultPanel.add(ppanel);
        defaultPanel.add(bpanel);
        return defaultPanel;
    }

    // EFFECTS: Creates a frame for UI
    private void initializeGraphics() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        closingWithLogMessage();
        frame.setMinimumSize(new Dimension(1300, 700));
        frame.setLayout(new BorderLayout());
        frame.setTitle("Baseball Stat Calculator");

        frame.add(setTablePanels(), BorderLayout.NORTH);
        JPanel btnpanel = new JPanel();
        btnpanel.setPreferredSize(new Dimension(800, 20));
        btnpanel.add(addb);
        btnpanel.add(addp);
        btnpanel.add(edit);
        btnpanel.add(remove);
        btnpanel.add(load);
        btnpanel.add(save);
        frame.add(btnpanel, BorderLayout.CENTER);
        JLabel j = new JLabel(baseball);
        frame.add(j, BorderLayout.SOUTH);


        frame.setVisible(true);
    }

    public void closingWithLogMessage() {
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                printLog(EventLog.getInstance());
                //THEN you can exit the program
                System.exit(0);
            }
        });
    }

    // EFFECTS: puts fields in pitcher instance in list of string
    public String[] pitcherToList(Pitcher p) {
        String[] res = new String[10];
        res[0] = p.getName();
        res[1] = String.valueOf(p.getWins());
        res[2] = String.valueOf(p.getLosses());
        res[3] = String.valueOf(p.getEra());
        res[4] = String.valueOf(p.getInnings());
        res[5] = String.valueOf(p.getEr());
        res[6] = String.valueOf(p.getHits());
        res[7] = String.valueOf(p.getBb());
        res[8] = String.valueOf(p.getSo());
        res[9] = String.valueOf(p.getWhip());
        return res;
    }

    // EFFECTS: puts batter fields to list of string
    public String[] batterToList(Batter b) {
        String[] res = new String[13];
        res[0] = b.getName();
        res[1] = String.valueOf(b.getAtBat());
        res[2] = String.valueOf(b.getRuns());
        res[3] = String.valueOf(b.getHits());
        res[4] = String.valueOf(b.getBb());
        res[5] = String.valueOf(b.getRbi());
        res[6] = String.valueOf(b.getDoubles());
        res[7] = String.valueOf(b.getTriples());
        res[8] = String.valueOf(b.getHr());
        res[9] = String.valueOf(b.getAvg());
        res[10] = String.valueOf(b.getObp());
        res[11] = String.valueOf(b.getSlg());
        res[12] = String.valueOf(b.getOps());
        return res;
    }


    // EFFECTS: creates a list of pitcherList in string representation of pitchers
    public String[][] listOfPitchersToList() {
        String[][] res = new String[pitcherList.size()][];
        for (int i = 0; i < pitcherList.size(); i++) {
            res[i] = pitcherToList(pitcherList.getPitcherAtIndex(i));
        }
        return res;
    }

    // EFFECTS: creates list representation of batterList in forms of String list
    public String[][] listOfBattersToList() {
        String[][] res = new String[batterList.size()][];
        for (int i = 0; i < batterList.size(); i++) {
            res[i] = batterToList(batterList.getBatterAtIndex(i));
        }
        return res;
    }

    // EFFECTS: creates a graphic table for pitchers
    public JScrollPane pscrollpane() {
        pitchertitle = new String[]{"Name", "W", "L", "ERA", "IP", "ER", "HIT", "BB", "SO", "WHIP"};

        pitcherdata = listOfPitchersToList();
        ptable = new JTable(pitcherdata, pitchertitle);
        psp = new JScrollPane(ptable);
        TableColumnModel columnModel = ptable.getColumnModel();
        columnModel.getColumn(4).setPreferredWidth(100);
        columnModel.getColumn(9).setPreferredWidth(100);
        columnModel.getColumn(0).setPreferredWidth(300);
        return psp;
    }

    // EFFECTS: Creates graphic table for batters
    public JScrollPane bscrollpane() {
        battertitle = new String[] {"Name", "AB", "RUN", "HIT", "BB", "RBI", "2B", "3B",
                "HR", "AVG", "OBP", "SLG", "OPS"};

        batterdata = listOfBattersToList();

        btable = new JTable(batterdata, battertitle);
        btable.setPreferredSize(new Dimension(400, 200));
        bsp = new JScrollPane(btable);
        TableColumnModel columnModel = btable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(500);
        columnModel.getColumn(9).setPreferredWidth(100);
        columnModel.getColumn(10).setPreferredWidth(100);
        columnModel.getColumn(11).setPreferredWidth(100);
        columnModel.getColumn(12).setPreferredWidth(130);
        return bsp;
    }

    // EFFECTS: saves batterList and pitcherList to file
    private void saveListUI() {
        saveBatterList();
        savePitcherList();
        showMessageSave();

    }

    // EFFECTS: shows a message indicating success
    public void showMessageSave() {
        JOptionPane.showMessageDialog(null, "Saved!", "Message", JOptionPane.INFORMATION_MESSAGE, teemo);
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
    // EFFECTS: removes a selected player from graphic table
    public void removePlayerUI() {
        int pindex = ptable.getSelectedRow();
        int bindex = btable.getSelectedRow();
        if (pindex >= 0) {
            pitcherList.removePitcher(pitcherList.getPitcherAtIndex(pindex));
        }
        if (bindex >= 0) {
            batterList.removeBatter(batterList.getBatterAtIndex(bindex));
        }
        frame.dispose();
        initializeGraphics();
        showMessageRemove();
    }

    // EFFECTS: shows a message indicating success
    public void showMessageRemove() {
        JOptionPane.showMessageDialog(null, "Removed Successfully!", "Message", JOptionPane.INFORMATION_MESSAGE, teemo);
    }

    // MODIFIES: this
    // EFFECTS: Edits fields of selected player and updates the table
    public void editPlayerUI() {
        editBatterUI();
        editPitcherUI();
        frame.dispose();
        initializeGraphics();
        showMessageEdit();
    }

    // EFFECTS: shows a message indicating success
    public void showMessageEdit() {
        JOptionPane.showMessageDialog(null, "Edited Successfully", "Message", JOptionPane.INFORMATION_MESSAGE, teemo);
    }

    // MODIFIES: this
    // EFFECTS: pops up a window for editing pitchers and edits the selected pitcher from table
    public void editPitcherUI() {
        int pindex = ptable.getSelectedRow();
        if (pindex >= 0) {
            int res = JOptionPane.showConfirmDialog(null, pstats, "Enter Pitcher Stats",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, picon);
            Pitcher p = pitcherList.getPitcherAtIndex(pindex);
            pname.setText(p.getName());
            String name = pname.getText();
            String w = win.getText();
            String l = loss.getText();
            String inning = ip.getText();
            String err = er.getText();
            String h = hit.getText();
            String bb = walk.getText();
            String k = so.getText();
            if (res == JOptionPane.OK_OPTION) {
                Pitcher p1 = new Pitcher(name, Integer.parseInt(w), Integer.parseInt(l), Double.parseDouble(inning),
                        Integer.parseInt(err), Integer.parseInt(h), Integer.parseInt(bb), Integer.parseInt(k));
                pitcherList.editPitcher(p1);
                clearPitcherFields();
            } else {
                clearPitcherFields();
            }
        }
    }

    // MODFIES: this
    // EFFECTS: edits batter from selected table and updates the table
    public void editBatterUI() {
        int bindex = btable.getSelectedRow();
        if (bindex >= 0) {
            int res = JOptionPane.showConfirmDialog(null, bstats, "Enter Batter Stats",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, bicon);
            Batter b = batterList.getBatterAtIndex(bindex);
            bname.setText(b.getName());
            String name = bname.getText();
            String abat = ab.getText();
            String r = runs.getText();
            String h = hits.getText();
            String d = doubles.getText();
            String t = triples.getText();
            String homer = hr.getText();
            String walk = bb.getText();
            String rb = rbi.getText();
            if (res == JOptionPane.OK_OPTION) {
                Batter b1 = new Batter(name, Integer.parseInt(abat), Integer.parseInt(r), Integer.parseInt(h),
                        Integer.parseInt(d),
                        Integer.parseInt(t), Integer.parseInt(homer), Integer.parseInt(walk), Integer.parseInt(rb));
                batterList.editBatter(b1);
            }
            clearBatterFields();
        }
    }

    public void printLog(EventLog e) {
        for (Event next : e) {
            System.out.println(next.toString() + "\n\n");
        }
    }





}
