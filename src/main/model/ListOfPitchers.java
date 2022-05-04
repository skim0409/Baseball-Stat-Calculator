package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents list of pitchers
public class ListOfPitchers implements Writable {
    private List<Pitcher> pitcherList;// array list of pitchers where pitchers are stored

    //EFFECTS: list is empty
    public ListOfPitchers() {
        pitcherList = new ArrayList<>();
    }

    // REQUIRES: pitcherList is not empty and int 'ind' < pitcherList size
    // EFFECTS: returns pitcher stored in 'idn' index of list
    public Pitcher getPitcherAtIndex(int ind) {
        return pitcherList.get(ind);
    }

    // EFFECTS: returns the length of the list
    public int size() {
        return pitcherList.size();
    }

    // MODIFIES: this
    // EFFECTS: adds a new pitcher p to the pitcherList
    public void addPitcher(Pitcher p) {
        EventLog.getInstance().logEvent(new Event("Added Pitcher: " + p.getName()));
        pitcherList.add(p);
    }

    // MODIFIES: this
    // EFFECTS: Sorts the pitcherList according to number of wins players have
    //          the player with the most win is store in the 0th index of pitcherList
    //          Returns the sorted pitcherList.

    // MODIFIES: this
    // REQUIRES: Pitcher p exists in the list and batterList.size() > 0
    // EFFECTS: Removes the pitcher p  from the list
    public List<Pitcher> removePitcher(Pitcher p) {
        EventLog.getInstance().logEvent(new Event("Removed Pitcher: " + p.getName()));
        pitcherList.remove(p);
        return pitcherList;
    }

    // MODIFIES: this
    // EFFECTS: Replaces a pitcher in the list that has the same name as pitcher 'pit'
    public List<Pitcher> editPitcher(Pitcher pit) {
        for (int i = 0; i < pitcherList.size(); i++) {
            if (pitcherList.get(i).getName().equals(pit.getName())) {
                EventLog.getInstance().logEvent(new Event("Edited Pitcher: " + pit.getName()));
                pitcherList.set(i, pit);
            }
        }
        return pitcherList;
    }

    // MODIFIES: this
    // EFFECTS: Sorts pitcherList from the player with the most wins to the least wins.
    public List<Pitcher> sortByWins() {
        if (pitcherList.size() <= 1) {
            return pitcherList;
        }
        for (int i = 0; i < pitcherList.size(); i++) {
            for (int j = i + 1; j < pitcherList.size(); j++) {
                if (pitcherList.get(i).getWins() < pitcherList.get(j).getWins()) {
                    Pitcher temp = pitcherList.get(i);
                    pitcherList.set(i, pitcherList.get(j));
                    pitcherList.set(j, temp);
                }
            }
        }
        return pitcherList;
    }

    // MODIFIES: this
    // EFFECTS: Sorts the pitcherList according to players era.
    //          The player with the lowest era is stored in the 0th index of pitcherList.
    //          Players are sorted with the player with the lowest era to the highest era.
    //          Returns the sorted pitcherList.
    public List<Pitcher> sortByEra() {
        if (pitcherList.size() <= 1) {
            return pitcherList;
        }
        for (int i = 0; i < pitcherList.size(); i++) {
            for (int j = i + 1; j < pitcherList.size(); j++) {
                if (pitcherList.get(i).getEra() > pitcherList.get(j).getEra()) {
                    Pitcher temp = pitcherList.get(i);
                    pitcherList.set(i, pitcherList.get(j));
                    pitcherList.set(j, temp);
                }
            }
        }
        return pitcherList;
    }

    // MODIFIES: this
    // EFFECTS: Sorts the players in pitcherList according to number of innings players have pitched.
    //          The player with the most inning is store in the 0th index of pitcherList and the list sorts
    //          to the most innings to the least innings.
    //          Returns the sorted pitcherList.
    public List<Pitcher> sortByInnings() {
        if (pitcherList.size() <= 1) {
            return pitcherList;
        }
        for (int i = 0; i < pitcherList.size(); i++) {
            for (int j = i + 1; j < pitcherList.size(); j++) {
                if (pitcherList.get(i).getInnings() < pitcherList.get(j).getInnings()) {
                    Pitcher temp = pitcherList.get(i);
                    pitcherList.set(i, pitcherList.get(j));
                    pitcherList.set(j, temp);
                }
            }
        }
        return pitcherList;
    }

    // MODIFIES: this
    // EFFECTS: Sorts the players in pitcherList according to whip of the player.
    //          The player with the lowest whip is store in the 0th index of pitcherList
    //          Players are sorted by lowest whip to the highest whip.
    //          Returns the sorted pitcherList.
    public List<Pitcher> sortByWhip() {
        if (pitcherList.size() <= 1) {
            return pitcherList;
        }
        for (int i = 0; i < pitcherList.size(); i++) {
            for (int j = i + 1; j < pitcherList.size(); j++) {
                if (pitcherList.get(i).getWhip() > pitcherList.get(j).getWhip()) {
                    Pitcher temp = pitcherList.get(i);
                    pitcherList.set(i, pitcherList.get(j));
                    pitcherList.set(j, temp);
                }
            }
        }
        return pitcherList;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("List Of Pitchers", pitcherListToJson());
        return json;
    }

    // EFFECTS: return pitchers in pitcherList as a JSON array
    private JSONArray pitcherListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Pitcher p : pitcherList) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: checks if there is pitcher p in pitcherlist. Returns true if there is
    //          false otherwise.
    public boolean containsPitcher(Pitcher p) {
        for (int i = 0; i < pitcherList.size(); i++) {
            if (pitcherList.get(i).getName().equals(p.getName())) {
                return true;
            }
        }
        return false;
    }
}
