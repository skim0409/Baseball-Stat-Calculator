package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a pitcher with name and stats such as wins, losses, er(earned run), hits(hits allowed)
// bb(walks allowed), and so(strike-outs) in int and innings in double. Era and whip is calculated with given
// parameters
public class Pitcher implements Writable {
    private String name;// name of the pitcher
    private int wins; // num of wins of the pitcher
    private int losses; // num of the losses
    private double innings; // total innings pitched
    private int er; // earned runs(runs allowed by the pitcher)
    private int hits;// total number of hits allowed
    private int bb; // total number of walks allowed
    private int so;// total number of strike-outs by the pitcher

    private double era;// earned run average(number of runs pitchers allows in 9 innings)
    private double whip;//walks plus hits per inning

    /*
     * REQUIRES: innings > 0.0 , max length for name is 20 characters
     * EFFECTS: player name is set to name, all stats are set to int values except for innings which is a
     *          double type
     */

    public Pitcher(String name, int wins, int losses, double innings, int error, int hits, int bb, int so) {
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.innings = innings;
        this.er = error;
        this.hits = hits;
        this.bb = bb;
        this.so = so;

        era = calculateEra(error, innings);
        whip = calculateWhip(hits, bb, innings);


    }
    /*
     * REQUIRES: innings > 0.0
     * EFFECTS: Era is calculated given earned runs(er) and innings
     *          Era is returned with 2 decimal places
     */

    public double calculateEra(int er, double innings) {
        String strIp = String.valueOf(innings);
        String dec = "";
        double era;
        for (int i = 0; i < strIp.length() - 1; i++) {
            if (strIp.substring(i, i + 1).equals(".")) {
                dec = strIp.substring(i + 1, i + 2);
            }
        }
        if (dec.equals("1")) {
            era = (double) (9 * er) / (innings + 0.33 - 0.1);
        } else if (dec.equals("2")) {
            era = (double) (9 * er) / (innings + 0.66 - 0.2);
        } else {
            era = (double) (9 * er) / innings;
        }
        return Math.round(era * 100.0) / 100.0;
    }

    /*
     * REQUIRES: innings > 0.0
     * EFFECTS: WHIP(walks and hits per inning) is calculated given hits walks(bb) and innings.
     *          Whip is returned with 3 decimal places
     */
    public double calculateWhip(int hits, int bb, double innings) {
        String strIp = String.valueOf(innings);
        String dec = "";
        double whip;
        for (int i = 0; i < strIp.length() - 1; i++) {
            if (strIp.substring(i, i + 1).equals(".")) {
                dec = strIp.substring(i + 1, i + 2);
            }
        }
        if (dec.equals("1")) {
            whip = (double) (hits + bb) / (innings + 0.33 - 0.1);
        } else if (dec.equals("2")) {
            whip = (double) (hits + bb) / (innings + 0.66 - 0.2);
        } else {
            whip = (double) (hits + bb) / innings;
        }
        return Math.round(whip * 1000) / 1000.0;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public double getInnings() {
        return innings;
    }

    public int getEr() {
        return er;
    }

    public int getHits() {
        return hits;
    }

    public int getBb() {
        return bb;
    }

    public int getSo() {
        return this.so;
    }

    public double getEra() {
        return era;
    }

    public double getWhip() {
        return whip;
    }

    /*
     * EFFECTS: Returns string form of Pitcher. Each players' stat and name will be aligned
     *          when printed one above the other
     */
    @SuppressWarnings({"methodLength", "checkstyle:SuppressWarnings"})
    public String toString() {
        String builder = "";
        for (int i = 0; i < 65; i++) {
            if (i == 0) {
                builder += this.name;
                i += this.name.length() - 1;
            } else if (i == 20) {
                builder += this.wins;
                i += String.valueOf(this.wins).length() - 1;
            } else if (i == 24) {
                builder += this.losses;
                i += String.valueOf(this.losses).length() - 1;
            } else if (i == 28) {
                String e = String.format("%.2f", this.era);
                builder += e;
                i += String.valueOf(e).length() - 1;
            } else if (i == 34) {
                String ip = String.format("%.1f", this.innings);
                builder += ip;
                i += String.valueOf(ip).length() - 1;
            } else if (i == 41) {
                builder += this.er;
                i += String.valueOf(this.er).length() - 1;
            } else if (i == 46) {
                builder += this.hits;
                i += String.valueOf(this.hits).length() - 1;
            } else if (i == 51) {
                builder += this.bb;
                i += String.valueOf(this.bb).length() - 1;
            } else if (i == 55) {
                builder += this.so;
                i += String.valueOf(this.so).length() - 1;
            } else if (i == 60) {
                String w = String.format("%.3f", this.whip);
                builder += this.whip;
                i += String.valueOf(w).length() - 1;
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
        json.put("wins", wins);
        json.put("losses", losses);
        json.put("innings", innings);
        json.put("er", er);
        json.put("hits", hits);
        json.put("bb", bb);
        json.put("so", so);
        return json;
    }
}
