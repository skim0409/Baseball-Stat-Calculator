package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents list of batters
public class ListOfBatters implements Writable {
    private List<Batter> battersList;// arraylist of batters where batters are stored

    //EFFECTS: batterList is empty
    public ListOfBatters() {
        battersList = new ArrayList<>();
    }

    //REQUIRES: int ind < batterList.size and batterList >= 1
    //EFFECTS: Returns the batter at 'ind' index of batterList
    public Batter getBatterAtIndex(int ind) {
        return battersList.get(ind);
    }

    //EFFECTS: Returns the length of the batterList
    public int size() {
        return battersList.size();
    }

    // MODIFIES: this
    // EFFECTS: adds a new Batter b to the batterList
    public void addBatter(Batter b) {
        EventLog.getInstance().logEvent(new Event("Added Batter: " + b.getName()));
        battersList.add(b);
    }


    // MODIFIES: this
    // REQUIRES: Batter b exists in the list and batterList.size() > 0
    // EFFECTS: Removes the batter b from the list
    public List<Batter> removeBatter(Batter b) {
        EventLog.getInstance().logEvent(new Event("Removed Batter: " + b.getName()));
        battersList.remove(b);
        return battersList;
    }

    // MODIFIES: this
    // EFFECTS: Replaces a better in the list that has the same name as batter 'bat'
    public List<Batter> editBatter(Batter bat) {
        for (int i = 0; i < battersList.size(); i++) {
            if (battersList.get(i).getName().equals(bat.getName())) {
                EventLog.getInstance().logEvent(new Event("Edited Batter: " + bat.getName()));
                battersList.set(i, bat);
            }
        }
        return battersList;
    }

    /*
     * MODIFIES: this
     * EFFECTS: Sorts batters in batterList according to number of hits each player has
     *          Batters with the most hits are sorted towards front of the list
     *          Returns the new sorted list.
     */
    public List<Batter> sortByHits() {
        if (battersList.size() <= 1) {
            return battersList;
        }
        for (int i = 0; i < battersList.size(); i++) {
            for (int j = i + 1; j < battersList.size(); j++) {
                if (battersList.get(i).getHits() < battersList.get(j).getHits()) {
                    Batter temp = battersList.get(i);
                    battersList.set(i, battersList.get(j));
                    battersList.set(j, temp);
                }
            }
        }
        return battersList;
    }

    /*
     * MODIFIES: this
     * EFFECTS: Sorts batters in batterList according to number of rbi each player has
     *          Batters with the most rbi are sorted towards front of the list
     *          Returns the new sorted list.
     */
    public List<Batter> sortByRbi() {
        if (battersList.size() <= 1) {
            return battersList;
        }
        for (int i = 0; i < battersList.size(); i++) {
            for (int j = i + 1; j < battersList.size(); j++) {
                if (battersList.get(i).getRbi() < battersList.get(j).getRbi()) {
                    Batter temp = battersList.get(i);
                    battersList.set(i, battersList.get(j));
                    battersList.set(j, temp);
                }
            }
        }
        return battersList;
    }

    /*
     * MODIFIES: this
     * EFFECTS: Sorts batters in batterList according to number of runs each player has
     *          Batters with the most runs are sorted towards front of the list
     *          Returns the new sorted list.
     */
    public List<Batter> sortByRuns() {
        if (battersList.size() <= 1) {
            return battersList;
        }
        for (int i = 0; i < battersList.size(); i++) {
            for (int j = i + 1; j < battersList.size(); j++) {
                if (battersList.get(i).getRuns() < battersList.get(j).getRuns()) {
                    Batter temp = battersList.get(i);
                    battersList.set(i, battersList.get(j));
                    battersList.set(j, temp);
                }
            }
        }
        return battersList;
    }

    /*
     * MODIFIES: this
     * EFFECTS: Sorts batters in batterList according to number of home runs each player has
     *          Batters with the most home runs are sorted towards front of the list
     *          Returns the new sorted list.
     */
    public List<Batter> sortByHr() {
        if (battersList.size() <= 1) {
            return battersList;
        }
        for (int i = 0; i < battersList.size(); i++) {
            for (int j = i + 1; j < battersList.size(); j++) {
                if (battersList.get(i).getHr() < battersList.get(j).getHr()) {
                    Batter temp = battersList.get(i);
                    battersList.set(i, battersList.get(j));
                    battersList.set(j, temp);
                }
            }
        }
        return battersList;
    }

    /*
     * MODIFIES: this
     * EFFECTS: Sorts batters in batterList according to batting average each player has.
     *          Batters with the highest avg are sorted towards front of the list
     *          Returns the new sorted list.
     */
    public List<Batter> sortByAvg() {
        if (battersList.size() <= 1) {
            return battersList;
        }
        for (int i = 0; i < battersList.size(); i++) {
            for (int j = i + 1; j < battersList.size(); j++) {
                if (battersList.get(i).getAvg() < battersList.get(j).getAvg()) {
                    Batter temp = battersList.get(i);
                    battersList.set(i, battersList.get(j));
                    battersList.set(j, temp);
                }
            }
        }
        return battersList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("List Of Batters", battersToJson());
        return json;
    }

    // EFFECTS: returns batters in batterList as a JSON array
    private JSONArray battersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Batter b : battersList) {
            jsonArray.put(b.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: checks if given batter is in the batterlist. Returns true if there is
    //          false otherwise.
    public boolean containsBatter(Batter b) {
        for (int i = 0; i < battersList.size(); i++) {
            if (battersList.get(i).getName().equals(b.getName())) {
                return true;
            }
        }
        return false;
    }

}
