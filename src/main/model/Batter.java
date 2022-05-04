package model;

import org.json.JSONObject;
import persistence.Writable;

// Representing a Batter having name(in String), and other stats #of at bat, runs, hits, walks(bb), rbi
// doubles, triples, and hr(in int). Also includes stats such as Avg, slg, opg, and ops(in Double).
public class Batter implements Writable {
    private String name;// name of the player
    private int atBat;// number of at bat
    private int runs;// number of runs by the player
    private int hits;// number of hits hit by the player
    private int bb;// number of walks
    private int rbi;// number of runs driven in by the player
    private int doubles;// number of doubles
    private int triples;// number of triples
    private int hr;// number of home runs

    private double avg;// batting average of the player
    private double slg;// on base percentage of the player
    private double obp;//slugging percentage of the player
    private double ops;// on base plus slugging percentage

    /*
     * REQUIRES: Name is not longer than 20 characters, atBat is not zero
     * EFFECTS: name of the player is set to name, and other fields are set to their respective values
     *          avg, obp, slg, ops is set by using parameters of the constructor.
     *
     */
    public Batter(String name, int atBat, int runs, int hits, int doubles, int triples, int hr, int bb, int rbi) {
        this.name = name;
        this.atBat = atBat;
        this.runs = runs;
        this.hits = hits;
        this.doubles = doubles;
        this.triples = triples;
        this.hr = hr;
        this.bb = bb;
        this.rbi = rbi;

        avg = calculateAvg(atBat, hits);
        obp = calculateOBP(hits, bb, atBat);
        slg = calculateSLG(hits, doubles, triples, hr, atBat);
        ops = slg + obp;

    }

    /*
     * REQUIRES: atBat is not zero
     * EFFECTS: Avg is calculated given atBat and hits
     *          Avg is returned in 3 decimal places
     *
     *
     */
    public double calculateAvg(int atBat, int hits) {
        double avg = ((double) hits / atBat);
        double rounded = Math.round(avg * 1000) / 1000.0;
        return rounded;
    }

    /*
     * REQUIRES: atBat is not zero
     * EFFECTS: OBP is calculated given atBat and hits and bb
     *          OBP is returned in 3 decimal places
     *
     *
     */

    public double calculateOBP(int hits, int bb, int atBat) {
        double obp = ((double) (hits + bb) / (atBat + bb));
        double rounded = Math.round(obp * 1000) / 1000.0;
        return rounded;
    }

    /*
     * REQUIRES: atBat is not zero
     * EFFECTS: slg is calculated given atBat and hits doubles, triples, hr.
     *          slg is returned in 3 decimal places
     *
     *
     */
    public double calculateSLG(int hits, int doubles, int triples, int hr, int atBat) {
        double denom = (hits - doubles - triples - hr) + 2 * doubles
                + 3 * triples + 4 * hr;
        double slg = denom / (double) atBat;
        double rounded = Math.round(slg * 1000) / 1000.0;
        return rounded;
    }

    public String getName() {
        return this.name;
    }

    public int getAtBat() {
        return this.atBat;
    }

    public int getRuns() {
        return this.runs;
    }

    public int getHits() {
        return this.hits;
    }

    public int getBb() {
        return this.bb;
    }

    public int getRbi() {
        return this.rbi;
    }

    public int getDoubles() {
        return this.doubles;
    }

    public int getTriples() {
        return this.triples;
    }

    public int getHr() {
        return this.hr;
    }

    public double getAvg() {
        return this.avg;
    }

    public double getSlg() {
        return this.slg;
    }

    public double getObp() {
        return this.obp;
    }

    public double getOps() {
        return ops;
    }

    /*
     * REQUIRES:
     * EFFECTS: Returns string form of Batter. Each batters' name and stat will be aligned when
     *          printed one above the other.
     *
     *
     */

    @SuppressWarnings({"methodLength", "checkstyle:SuppressWarnings"})
    public String toString() {
        String builder = "";
        for (int i = 0; i < 76; i++) {
            if (i == 0) {
                builder += this.name;
                i += this.name.length();
            } else if (i == 22) {
                builder += this.atBat;
                i += String.valueOf(this.atBat).length() - 1;
            } else if (i == 26) {
                builder += runs;
                i += String.valueOf(this.runs).length() - 1;
            } else if (i == 30) {
                builder += this.hits;
                i += String.valueOf(this.hits).length() - 1;
            } else if (i == 34) {
                builder += this.bb;
                i += String.valueOf(this.bb).length() - 1;
            } else if (i == 37) {
                builder += this.rbi;
                i += String.valueOf(this.rbi).length() - 1;
            } else if (i == 41) {
                builder += this.doubles;
                i += String.valueOf(this.doubles).length() - 1;
            } else if (i == 45) {
                builder += this.triples;
                i += String.valueOf(this.triples).length() - 1;
            } else if (i == 49) {
                builder += this.hr;
                i += String.valueOf(this.hr).length() - 1;
            } else if (i == 53) {
                String baa = String.format("%.3f", this.avg);
                builder += baa;
                i += String.valueOf(baa).length() - 1;
            } else if (i == 60) {
                String ob = String.format("%.3f", this.obp);
                builder += ob;
                i += String.valueOf(ob).length() - 1;
            } else if (i == 67) {
                String sl = String.format("%.3f", this.slg);
                builder += sl;
                i += String.valueOf(sl).length() - 1;
            } else if (i == 74) {
                String op = String.format("%.3f", this.ops);
                builder += op;
                i += String.valueOf(op).length() - 1;
            } else {
                builder += " ";
            }
        }
        return builder;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("at bat", atBat);
        json.put("runs", runs);
        json.put("hits", hits);
        json.put("doubles", doubles);
        json.put("triples", triples);
        json.put("hr", hr);
        json.put("bb", bb);
        json.put("rbi", rbi);


        return json;
    }
}
